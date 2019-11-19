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
import java.util.ArrayList;
import java.util.Random;
/**
 * This class holds questions with same topic
 * @author JOE
 *
 */
public class Topic {
	//Name of topic
	private String topicName;	
	//Hash table to store each question
	private HashTable<Integer,QuestionNode> hashOfQuestion;
	//Number of questions in topic
	private int numQuestion;
	//Question's id that will be assigned
	private int questionID;

	/**
	 * This constructor will initiate each instance fields
	 * @param topicName name of topic
	 */
	public Topic(String topicName) 
	{
		//Assing each instance field's value
		this.topicName=topicName;
		hashOfQuestion=new HashTable<Integer,QuestionNode>();
		this.numQuestion=0;
		this.questionID=0;
		
	}
	/**
	 * give back the name of topic
	 * @return the name of topic
	 */
	public String getName()
	{
		//Return the name of topic
		return topicName;
	}
	/**
	 * Add individual questionNode to the topic
	 * @param addQ individual questionnode
	 */
	public void addQuestion(QuestionNode addQ)
	{
		//Try part when exception thrown
		try 
		{
			//Set id of question node
			addQ.setQuestionId(this.questionID);
			//Increment the id by 1
			questionID++;
			//Insert to the hash
			hashOfQuestion.insert(addQ.getQuestionID(),addQ);
		}
		//When exception raised
		catch(Exception e)
		{
			System.out.print("Impossible");
		}
		//Increment the number of question
		numQuestion++;
		
	}
	/**
	 * Generate  randomly chosen questions
	 * @param num number of questions to generate
	 * @return return randomly generated questions
	 */
	public ArrayList<QuestionNode> getRondomQuizzes(int num)
	{
		//arrayList to return
		ArrayList<QuestionNode> returnNode=new ArrayList<QuestionNode>();
		//For the case when exception happen
		try
		{
			//Generate random class object
			Random rand = new Random();
			//While the chosen question reach the num asked
			while(returnNode.size()<num)
			{
				//Hash table to store selected questionNode's id
				HashTable<Integer,Integer> selectedQ = new HashTable<Integer,Integer>();
				//Get Randomly chosen id
				int key =  rand.nextInt(numQuestion);
				//When it is not selected before
				if(!selectedQ.contain(key))
				{
					//GEt question node from hash
					QuestionNode currNode = hashOfQuestion.get(key);
					//Insert the key to hash
					selectedQ.insert(key,key);
					//Insert questionnode to arrayList that will be returned
					returnNode.add(currNode);
				}
			}
		}
		//When exception happen
		catch(Exception e)
		{
			System.out.print("Impossible");
		}
		//Return the generated node
		return returnNode;
	}
	/**
	 * return all question in the current topic
	 * @return all questions
	 */
	public ArrayList<QuestionNode> getAll()
	{
		//Array list that will be returned
		ArrayList<QuestionNode> all = new ArrayList<QuestionNode>();
		//Try block for exception
		try
		{
			//For loop to iterate all questions
			for(int i=0;i<questionID;i++)
			{
				//Get question node
				QuestionNode curr=hashOfQuestion.get(i);
				//Add the question node
				all.add(curr);
			}
		}
		//When exception happen
		catch(Exception e)
		{
			System.out.print("possible When remove added");
		}
		//Return all
		return all;
		
	}
	/**
	 * the total number of question
	 * @return the total number of question
	 */
	public int getNum()
	{
		//The number of questions
		return numQuestion;
	}
}
