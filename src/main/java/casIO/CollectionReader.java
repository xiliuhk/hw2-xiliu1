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
	
	private boolean isFirst = true;
	public void initialize() {
	    inputfile = new File((String) getConfigParameterValue("InputFile"));
	 }
	
	/*
	 * load input file from the hw2.in
	 * */
	@Override
	public void getNext(CAS pCAS) throws IOException, CollectionException {
		// TODO Auto-generated method stub
		JCas jcas;
		try{
			jcas =pCAS.getJCas();
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
		if(isFirst){
			isFirst=false;
			return true;
		}
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
