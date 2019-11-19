//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (Quiz Generator)
// Files:           (DataStructureADT.java,DuplicateKeyException.java,HashTable.java
//					HashTableADT.java,IllegalNullKeyException.java,KeyNotFoundException.java,
//					Main.java,QuestionNode.java,QuizControl.java)
// Course:          (CS400-S19-Lec001)
//
// Author:          (Hyung Rae Cho(X60), Gerrrard Kim(X54),Jeong Heo(X34),SangHyung Lee(X05))
// Email:           (hcho223@wisc.edu,hkim624@wisc.edu,jheo8@wisc.edu,lee866@wisc.edu)
// Lecturer's Name: (Debra Deppeler)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    ()
// Partner Email:   ()
// Partner Lecturer's Name: ()
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;
/**
 * This store question's information and provide 
 * question's Information
 * @author JOE
 *
 */
public class QuestionNode 
{
	//Each question's data
    private String metaData;
	private String questionText;
	private String topic;
	private String imageFileName;
	private String[] choiceArray;
	private Boolean[] solutionArray;
	private int QUESTIONID; 
	/**
	 * This constructor initiate the question node with given data
	 * @param metaData additional data for question
	 * @param topic topic of question
	 * @param questionText question's instruction
	 * @param imageFileName image file's name
	 * @param choiceArray choices of question
	 * @param solutionArray solution of question
	 */
	public QuestionNode(String metaData ,String topic, String questionText, String imageFileName,
			String[] choiceArray, Boolean[] solutionArray)
	{
		//Initiate each instance fields
		this.metaData = metaData;
		this.questionText=questionText;
		this.topic=topic;
		this.imageFileName = imageFileName;
		this.choiceArray=choiceArray;
		this.solutionArray=solutionArray;
		this.QUESTIONID=-1;
		
	}
	/**
	 * Getter for question text
	 * @return question text
	 */
	public String getQuestionText()
	{
		return this.questionText;
	}
	/**
	 * getter for topic
	 * @return topic
	 */
	public String getTopic()
	{
		return topic;
	}
	/**
	 * getter for image file name
	 * @return image filename
	 */
	public String getImageFileName()
	{
		return imageFileName;
	}
	/**
	 * getter for choice array
	 * @return choice array
	 */
	public String[] getChoiceArray()
	{
		return choiceArray;
	}
	/**
	 * getter for solution array
	 * @return solution array
	 */
	public Boolean[] getSolutionArray()
	{
		return solutionArray;
	}
	/**
	 * getter for question id
	 * @return ID
	 */
	public int getQuestionID()
	{
		return QUESTIONID;
	}
	/**
	 * Setter for question text
	 * @param questionText Question explanation
	 */
	public void setQuestionText(String questionText)
	{
		this.questionText=questionText;
	}
	/**
	 * Setter for topic
	 * @param topic
	 */
	public void setTopic(String topic)
	{
		this.topic=topic;
	}
	/**
	 * setter for image Name
	 * @param imageFileName image's file name
	 */
	public void setImageFileName(String imageFileName)
	{
		this.imageFileName=imageFileName;
	}
	/**
	 * Setter for choice array
	 * @param choiceArray choice array
	 */
	public void setChoiceArray(String[] choiceArray)
	{
		this.choiceArray=choiceArray;
	}
	/**
	 * setter for solution array
	 * @param solutionArray solutions
	 */
	public void setSolutionArray(Boolean[] solutionArray)
	{
		this.solutionArray=solutionArray;
	}
	/**
	 * setter for question id
	 * @param questionID ID of question
	 */
	public void setQuestionId(int questionID)
	{
		this.QUESTIONID=questionID;
	}
	/**
	 * Getter for metaData
	 * @return meta data
	 */
	public String getMetaData()
	{
		return this.metaData;
	}
	/**
	 * Setter for metaData
	 * @param meta metaData
	 */
	public void setMetaData(String meta)
	{
		this.metaData=meta;
	}

}
