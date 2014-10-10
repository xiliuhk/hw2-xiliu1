package AnalysisEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import Type.HMMPrediction;
import Type.Sentence;
//import abner.Tagger;

import com.aliasi.util.AbstractExternalizable;
import com.aliasi.chunk.*;

/**
 * break the sentence into word tokens record the position and content of each
 * token record the gene mention and its position
 */

public class HMMAnnotator extends JCasAnnotator_ImplBase {
	private ConfidenceChunker HMMchunker;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		String modelPath = "/data/HMM.HmmChunker";
		try {
			HMMchunker = (ConfidenceChunker) AbstractExternalizable
					.readResourceObject(modelPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("HMMAnnotator initialized!");
	}

	/**
	 * use LingPipe pack receive pJCas: previous CAS Step 1. feed HMM a sentence
	 * to determine if it contains a gene tag Step 2. record the gene tags into
	 * words Step 3. convert the gene tags objects to CAS exception:
	 * AnalysisEngineProcessException
	 */

	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		System.out.println("HMMAnnotator started! \n");
		// String modelPath = "/data/HMM.HmmChunker";
		// HMM chunker to identify gene mentions
		// Chunker HMMchunker;

		try {
			// HMMchunker = (Chunker)
			// AbstractExternalizable.readResourceObject(modelPath);

			FSIterator<Annotation> iterator = aJCas.getAnnotationIndex(
					Sentence.type).iterator();

			while (iterator.hasNext()) {
				Sentence senTag = (Sentence) iterator.next();
				String content = senTag.getContent();
				String sentenceID = senTag.getSource();
				/*
				 * Retrieve the confidence from nBest Model
				 */
				List<String> geneTags = new ArrayList<String>();
				List<Double> confidences = new ArrayList<Double>();

				Iterator<Chunk> nBestIterator = this.HMMchunker.nBestChunks(
						content.toCharArray(), 0, content.toCharArray().length,
						5);

				while (nBestIterator.hasNext()) {
					Chunk temp = (Chunk) nBestIterator.next();
					String geneTag;
					double confidence = Math.pow(2.0, temp.score());
					// if (confidence>=0.6){
					geneTag = content.substring(temp.start(), temp.end());
					geneTags.add(geneTag);
					confidences.add(confidence);
					// }
				}
				for (int i = 0; i < geneTags.size(); i++) {
					HMMPrediction gene = new HMMPrediction(aJCas);
					gene.setSentenceID(sentenceID);
					gene.setGeneTag(geneTags.get(i));
					gene.setScore(confidences.get(i));
					gene.addToIndexes();
					// System.out.println(gene.getSentenceID()+"|"+gene.getGeneTag()+"|"+gene.getScore());
				}

				/*
				 * Set<Chunk> chunkSet = chunking.chunkSet(); for (Chunk chunk :
				 * chunkSet) { if (chunk != null) { HMMPrediction gene = new
				 * HMMPrediction(pJCas); gene.setSentenceID(senTag.getSource());
				 * gene.setBegin(chunk.start()); gene.setEnd(chunk.end());
				 * gene.setGeneTag(content.substring(chunk.start(),
				 * chunk.end())); gene.setScore(chunk.score());
				 * gene.addToIndexes();
				 * //System.out.println("HMM Gene: "+gene.getGeneTag()); } }
				 */
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("HMMAnnotator finished! \n");

	}
}
