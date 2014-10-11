package AnalysisEngine;

import java.util.HashMap;
import java.util.HashSet;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import Type.ABNERPrediction;
import Type.Gene;
import Type.HMMPrediction;

/**
 * Combination phase
 * @author laceyliu
 *
 */
public class FinalAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		System.out.println("FinalAnnotator initialized!");
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		// initialize the ABNER Dictionary
		//System.out.println("FinalAnnotator started!");

		this.ABNERDictionary = HashABNERPredictions(aJCas);
		FSIterator<Annotation> iterator = aJCas.getAnnotationIndex(
				HMMPrediction.type).iterator();
		while (iterator.hasNext()) {
			HMMPrediction HMMPredict = (HMMPrediction) iterator.next();
			String SentenceID = HMMPredict.getSentenceID();
			String GeneTag = HMMPredict.getGeneTag();
			if (ABNERDictionary.containsKey(SentenceID)) {
				HashSet<String> geneSet = ABNERDictionary.get(SentenceID);
				if (geneSet.contains(GeneTag)) {
					System.out.println("Agree: " + SentenceID + "|" + GeneTag);
					Gene gene = new Gene(aJCas);
					gene.setSource(SentenceID);
					gene.setContent(GeneTag);
					gene.addToIndexes(aJCas);
				}

			} else {
				double confidence = HMMPredict.getScore();
				int start = HMMPredict.getBegin();
				int end = HMMPredict.getEnd();
				// double Confidence = Math.pow(2, Score);
				// System.out.println("Disagree: " + SentenceID + "|" + GeneTag
				// + "|" + confidence);
				if (confidence >= 0.6) {
					Gene gene = new Gene(aJCas);
					gene.setSource(SentenceID);
					gene.setContent(GeneTag);
					gene.setBegin(start);
					gene.setEnd(end);
					gene.addToIndexes(aJCas);
				}
				// System.out.println("Picked!");
				// } else {
				// System.out.println("Not picked!");
				// }Í
			}
		}
		System.out.println("FinalAnnotator finished! \n");
	}

	private HashMap<String, HashSet<String>> ABNERDictionary;

	private HashMap<String, HashSet<String>> HashABNERPredictions(JCas aJCas)
			throws AnalysisEngineProcessException {
		HashMap<String, HashSet<String>> TempDictionary = new HashMap<String, HashSet<String>>();
		FSIterator<Annotation> iterator;
		iterator = aJCas.getAnnotationIndex(ABNERPrediction.type).iterator();
		while (iterator.hasNext()) {
			ABNERPrediction prediction = (ABNERPrediction) iterator.next();
			String SentenceID = prediction.getSentenceID();
			HashSet<String> geneSet;
			if (!TempDictionary.containsKey(SentenceID)) {
				geneSet = new HashSet<String>();
				geneSet.add(prediction.getGeneTag());
				TempDictionary.put(SentenceID, geneSet);
			} else {
				geneSet = TempDictionary.get(SentenceID);
				geneSet.add(prediction.getGeneTag());
				TempDictionary.put(SentenceID, geneSet);
			}
		}

		return TempDictionary;

	}

}
