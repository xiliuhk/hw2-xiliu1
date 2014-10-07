

/* First created by JCasGen Sat Oct 04 17:21:21 EDT 2014 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Oct 06 23:27:10 EDT 2014
 * XML source: /Users/laceyliu/Documents/workspace/hw2-xiliu1/src/main/resources/descriptors/typeSystemDescriptor.xml
 * @generated */
public class Gene extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Gene.class);
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
  protected Gene() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Gene(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Gene(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Gene(JCas jcas, int begin, int end) {
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
  //* Feature: Source

  /** getter for Source - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSource() {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_Source == null)
      jcasType.jcas.throwFeatMissing("Source", "Gene");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Gene_Type)jcasType).casFeatCode_Source);}
    
  /** setter for Source - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSource(String v) {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_Source == null)
      jcasType.jcas.throwFeatMissing("Source", "Gene");
    jcasType.ll_cas.ll_setStringValue(addr, ((Gene_Type)jcasType).casFeatCode_Source, v);}    
   
    
  //*--------------*
  //* Feature: Content

  /** getter for Content - gets 
   * @generated
   * @return value of the feature 
   */
  public String getContent() {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_Content == null)
      jcasType.jcas.throwFeatMissing("Content", "Gene");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Gene_Type)jcasType).casFeatCode_Content);}
    
  /** setter for Content - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setContent(String v) {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_Content == null)
      jcasType.jcas.throwFeatMissing("Content", "Gene");
    jcasType.ll_cas.ll_setStringValue(addr, ((Gene_Type)jcasType).casFeatCode_Content, v);}    
   
    
  //*--------------*
  //* Feature: Confidence

  /** getter for Confidence - gets 
   * @generated
   * @return value of the feature 
   */
  public double getConfidence() {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_Confidence == null)
      jcasType.jcas.throwFeatMissing("Confidence", "Gene");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Gene_Type)jcasType).casFeatCode_Confidence);}
    
  /** setter for Confidence - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setConfidence(double v) {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_Confidence == null)
      jcasType.jcas.throwFeatMissing("Confidence", "Gene");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Gene_Type)jcasType).casFeatCode_Confidence, v);}    
  }

    