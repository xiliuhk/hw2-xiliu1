package AnalysisEngine;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

//ßimport edu.cmu.deiis.analysisEngine.Abner;
import Type.ABNERPrediction;
import Type.Sentence;
import abner.Tagger;
public class ABNERAnnotator extends JCasAnnotator_ImplBase{
	private Tagger ABNERTagger;
	
	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
	    super.initialize(aContext);
	    ABNERTagger = new Tagger();
	    System.out.println("ABNERTagger initialized!");
	  }
	@Override
	public void process(JCas pJCas) throws AnalysisEngineProcessException {
		System.out.println("ABNERAnnotator started!");
		FSIterator<Annotation> iterator = pJCas.getAnnotationIndex(Sentence.type).iterator();
	    try{
	    	while (iterator.hasNext()) {
		    	  Sentence senTag=(Sentence) iterator.next();
		    	  String content = senTag.getContent();
		    	  String taggedContent = ABNERTagger.tagABNER(content);
		    	  String[] taggedSegs = taggedContent.split("	");
		    	  for (String seg:taggedSegs){
		    		  String segContent = seg.split("|")[0];
		    		  String segTag = seg.split("|")[1];
		    		  if (Pattern.matches("[0-9a-zA-Z-\\s]+", segTag)&&(segTag.contains("-DNA")||segTag.contains("-RNA")|| segTag.contains("-Protein"))){
		    			  ABNERPrediction gene = new ABNERPrediction(pJCas);
		    			  gene.setSentenceID(senTag.getSource());
		    			  gene.setGeneTag(segContent);
		    			  gene.setScore(-1);
		    			  gene.addToIndexes();
		    		  }
		    	  }
		      }
	    	
	    }catch (Exception e) {
	        e.printStackTrace();
	    } 
	    System.out.println("ABNERAnnotator finished! \n");
	}

}
