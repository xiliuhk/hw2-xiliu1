package casIO;
import java.io.File;
import java.io.IOException;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;

public class CollectionReader extends CollectionReader_ImplBase {
	private File inputfile;
	@Override
	/*
	 * Load input file from hw2.in
	 * */
	public void initialize() {
	    inputfile = new File((String) getConfigParameterValue("InputFile"));
	 }
	
	/*
	 * S1. convert input into a string
	 * S3. store string as cas document
	 * */
	@Override
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		// TODO Auto-generated method stub
		JCas jcas;
		try{
			jcas =aCAS.getJCas();
		}catch (Exception e){
			throw new CollectionException(e);
		}
		String text = "";
		text = FileUtils.file2String(inputfile);
		jcas.setDocumentText(text);
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Progress[] getProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
