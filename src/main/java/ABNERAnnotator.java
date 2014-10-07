import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import abner.Tagger;
public class ABNERAnnotator extends JCasAnnotator_ImplBase{
	private Tagger ABNERTagger = new Tagger();
	
	
	@Override
	public void process(JCas pJCas) throws AnalysisEngineProcessException {
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
		    		  if (segTag.contains("-DNA")||segTag.contains("-RNA")|| segTag.contains("-Protein")){
		    			  Gene gene = new Gene(pJCas);
		    			  gene.setSource(senTag.getSource());
		    			  gene.setContent(segContent);
		    			  gene.setConfidence(0.5);
		    			  gene.addToIndexes();
		    		  }
		    	  }
		      }
	    	
	    }catch (Exception e) {
	        e.printStackTrace();
	    } 
	}

}
