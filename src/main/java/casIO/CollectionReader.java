package casIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;


/**
 * 
 * This class is mainly used to read row data into UIMA
 * @author laceyliu
 *
 */
public class CollectionReader extends CollectionReader_ImplBase {
	private File inputfile;
	private  boolean isFirst;
	@Override
	/*
	 * Load input file from hw2.in
	 * */
	public void initialize() {
		inputfile = new File(((String) getConfigParameterValue("InputFile")).trim());
		System.out.println("CollectionReader initialized!");
		System.out.println("-___________________-");
		isFirst = true;
	 }
	
	/*
	 * S1. convert input into a string
	 * S3. store string as cas document
	 * */
	@Override
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		// TODO Auto-generated method stub
		System.out.println("CollectionReader started!");
		JCas jcas = null;
		try{
			jcas =aCAS.getJCas();
		}catch (Exception e){
			e.getStackTrace();
		}
		String text = ""; 
		text = FileUtils.file2String(inputfile);
		jcas.setDocumentText(text);
		System.out.println("CollectionReader finished!");
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		// TODO Auto-generated method stub
		if(isFirst){
			isFirst = false;
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
