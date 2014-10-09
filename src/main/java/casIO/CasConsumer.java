package casIO;
   
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.collection.base_cpm.CasObjectProcessor;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.resource.metadata.ProcessingResourceMetaData;
import org.apache.uima.util.ProcessTrace;

import Type.Gene;
import Type.Sentence;

public class CasConsumer extends CasConsumer_ImplBase implements CasObjectProcessor {

	  private File outFile;
	  private FileWriter writer;
	  private HashMap<String, String>SentenceIDmap;
	/**
	   * Initialize output file.
	   */
	  @Override
	  public void initialize() throws ResourceInitializationException {
	    try {
	      String outfilePath = (String) getUimaContext().getConfigParameterValue("OutputFile");
	      outFile = new File(outfilePath.trim());
	      writer = new FileWriter(outFile);
	    } catch (Exception e) {
	      throw new ResourceInitializationException(e);
	    }
	    SentenceIDmap = new HashMap<String, String>();
	  }

	@Override
	public void processCas(CAS aCAS) throws ResourceProcessException {
		JCas pJCas;
	    try {
	      pJCas = aCAS.getJCas();
	    } catch (CASException e) {
	      throw new ResourceProcessException(e);
	    }
	    try {
	      // initialize a buffered writer
	      BufferedWriter bufferedWriter = new BufferedWriter(writer);
	      // initialize the map for sentence content
	      FSIterator<Annotation> sentenceIterator = pJCas.getAnnotationIndex(Sentence.type).iterator();
	      // hash the sentences by sourceID and put the content into map
	      while (sentenceIterator.hasNext()) {
	        Sentence SentenceTag = (Sentence) sentenceIterator.get();
	        SentenceIDmap.put(SentenceTag.getSource(), SentenceTag.getContent());
	        sentenceIterator.moveToNext();
	      }
	      FSIterator<Annotation> geneIterator = pJCas.getAnnotationIndex(Gene.type).iterator();
	      // iterate the gene tags and find their positions
	      while (geneIterator.hasNext()) {
	        Gene geneT = (Gene) geneIterator.next();
	        String sContent = SentenceIDmap.get(geneT.getSource());
	        // calculate proper positions in sentence for each gene term
	        String contentTemp = sContent.substring(0, geneT.getBegin());
	        int countInSentence = blankCount(contentTemp);
	        int countInContent = blankCount(sContent.substring(geneT.getBegin(), geneT.getEnd()));
	        int start = geneT.getBegin() - countInSentence;
	        int end = geneT.getEnd() - countInSentence - countInContent - 1;
	        bufferedWriter.write(geneT.getSource() + "|" + start + " " + end + "|" + geneT.getContent()
	                + "\n");
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	}
	private int blankCount(String s) {
		    int count = 0;
		    for (int i = 0; i < s.length(); i++) {
		      if (s.charAt(i) == ' ') {
		        count++;
		      }
		    }
		    return count;
	}

}
