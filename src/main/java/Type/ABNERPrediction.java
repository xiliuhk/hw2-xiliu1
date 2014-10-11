package Type;


/* First created by JCasGen Fri Oct 10 10:47:25 EDT 2014 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Oct 10 22:57:02 EDT 2014
 * XML source: /Users/laceyliu/Documents/workspace/hw2-xiliu1/src/main/resources/descriptors/typeSystemDescriptor.xml
 * @generated */
public class ABNERPrediction extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ABNERPrediction.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ABNERPrediction() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ABNERPrediction(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ABNERPrediction(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ABNERPrediction(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: SentenceID

  /** getter for SentenceID - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSentenceID() {
    if (ABNERPrediction_Type.featOkTst && ((ABNERPrediction_Type)jcasType).casFeat_SentenceID == null)
      jcasType.jcas.throwFeatMissing("SentenceID", "Type.ABNERPrediction");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ABNERPrediction_Type)jcasType).casFeatCode_SentenceID);}
    
  /** setter for SentenceID - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceID(String v) {
    if (ABNERPrediction_Type.featOkTst && ((ABNERPrediction_Type)jcasType).casFeat_SentenceID == null)
      jcasType.jcas.throwFeatMissing("SentenceID", "Type.ABNERPrediction");
    jcasType.ll_cas.ll_setStringValue(addr, ((ABNERPrediction_Type)jcasType).casFeatCode_SentenceID, v);}    
   
    
  //*--------------*
  //* Feature: GeneTag

  /** getter for GeneTag - gets 
   * @generated
   * @return value of the feature 
   */
  public String getGeneTag() {
    if (ABNERPrediction_Type.featOkTst && ((ABNERPrediction_Type)jcasType).casFeat_GeneTag == null)
      jcasType.jcas.throwFeatMissing("GeneTag", "Type.ABNERPrediction");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ABNERPrediction_Type)jcasType).casFeatCode_GeneTag);}
    
  /** setter for GeneTag - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGeneTag(String v) {
    if (ABNERPrediction_Type.featOkTst && ((ABNERPrediction_Type)jcasType).casFeat_GeneTag == null)
      jcasType.jcas.throwFeatMissing("GeneTag", "Type.ABNERPrediction");
    jcasType.ll_cas.ll_setStringValue(addr, ((ABNERPrediction_Type)jcasType).casFeatCode_GeneTag, v);}    
   
    
  //*--------------*
  //* Feature: Score

  /** getter for Score - gets 
   * @generated
   * @return value of the feature 
   */
  public double getScore() {
    if (ABNERPrediction_Type.featOkTst && ((ABNERPrediction_Type)jcasType).casFeat_Score == null)
      jcasType.jcas.throwFeatMissing("Score", "Type.ABNERPrediction");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((ABNERPrediction_Type)jcasType).casFeatCode_Score);}
    
  /** setter for Score - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setScore(double v) {
    if (ABNERPrediction_Type.featOkTst && ((ABNERPrediction_Type)jcasType).casFeat_Score == null)
      jcasType.jcas.throwFeatMissing("Score", "Type.ABNERPrediction");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((ABNERPrediction_Type)jcasType).casFeatCode_Score, v);}    
  }

    