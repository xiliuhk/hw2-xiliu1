package AnalysisEngine;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import Type.Sentence;

/**
 * break the file into sentences record the position and content of each
 * sentence Sentence: sentence ID (source)+ sentence content
 */
public class SentenceAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		System.out.println("SentenceAnnotator initialized!");
	}

	/**
	 * receive pJCas: the previous CAS Step 1. split the text input into
	 * sentences Step 2. split the sentence into sourceID and contents Step 3.
	 * convert string to CAS exception: AnalysisEngineProcessException
	 */
	public void process(JCas aJCas) {
		System.out.println("Sentence Annotator started! \n");
		
		try {
			String text = aJCas.getDocumentText();
			String[] sentences = text.split("\n");
			for (int i = 0; i < sentences.length; i++) {
				Sentence senTag = new Sentence(aJCas);
				String[] senContent = sentences[i].split(" ", 2);
				//System.out.println("SID: "+senContent[0]);
				//System.out.println("SC: "+senContent[1]);
				senTag.setSource(senContent[0]);
				senTag.setContent(senContent[1]);
				senTag.addToIndexes(aJCas);
				//System.out.println(senTag.getSource());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sentence Annotator finished! \n");
	}
}