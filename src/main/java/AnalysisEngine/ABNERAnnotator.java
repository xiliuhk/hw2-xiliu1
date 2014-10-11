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

import Type.ABNERPrediction;
import Type.Sentence;
import abner.Tagger;
public class ABNERAnnotator extends JCasAnnotator_ImplBase{
	private Tagger ABNERTagger;

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
	    super.initialize(aContext);
	    ABNERTagger = new Tagger(1);
	    System.out.println("ABNERTagger initialized!");
	  }

	public void process(JCas pJCas) throws AnalysisEngineProcessException {
		//System.out.println("ABNERAnnotator started!");
	
		FSIterator<Annotation> iterator = pJCas.getAnnotationIndex(Sentence.type).iterator();
	    try{
	    	while (iterator.hasNext()) {
		    	  Sentence curSentence=(Sentence) iterator.next();
		    	  String content = curSentence.getContent();
		    	  String[][] taggedContent = ABNERTagger.getEntities(content);
		    	 // String[] taggedSegs = taggedContent.split("  ");
		    	  for (String segTag:taggedContent[0]){
		    		  String tagSource = curSentence.getSource();
		    		  //String segContent = segTag;
		    		 // System.out.println("Gene: "+segTag);
		    		  //String segTag = seg.split("|")[1];
		    		  //System.out.println("Type: "+segTag);
		    		  if (Pattern.matches("[0-9a-zA-Z-\\s]+", segTag)&&(segTag.contains("-DNA")||segTag.contains("-RNA")|| segTag.contains("-Protein"))){
		    			  ABNERPrediction gene = new ABNERPrediction(pJCas);
		    			  gene.setSentenceID(tagSource);
		    			  gene.setGeneTag(segTag);
		    			  gene.setScore(-1);
		    			  gene.addToIndexes(pJCas);
		    		  }
		    	  }
		      }
	    	
	    }catch (Exception e) {
	        e.printStackTrace();
	    } 
	    
	    System.out.println("ABNERAnnotator finished! \n");
	}

}
