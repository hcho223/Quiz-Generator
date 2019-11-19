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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * This controller control general environment of quiz generator
 * 
 *
 */
public class QuizControl 
{
	private ArrayList<Topic> topicBank;
	private int totalNum;
	/**
	 * This constructor generate instance fields data
	 */
	public QuizControl()
	{
		topicBank=new ArrayList<Topic>();
		totalNum=0;
		
	}
	/**
	 * This method generate QuestionNode instance and then check whether the topic is already exist.
	 * If the topic does exist, find the topic in the topic bank then add the question node to the topic and then 
	 * increment the totalNum instance field by 1. 
	 * If the topic does not exist, Create a new topic instance with topicname, and add the question node 
	 * then also add to the topicBank instance
	 * @param meta meta data 
	 * @param topic topic of individual question
	 * @param questionText question explanation
	 * @param image image file's name if has
	 * @param choices array of string with choices
	 * @param answers array of boolean true correct choice false otherwise
	 */
	public void saveIndiQuiz(String meta, String topic, String questionText,String image,
								String[] choices, Boolean[] answers)
	{
		//Initiate questionNode
		QuestionNode curr = new QuestionNode(meta,topic,questionText,image,choices,answers);
		//Set decision to check whether topic is exist
		boolean decision = false;
		//integer to store the exisiting topic's index
		int targetIndex=-1;
		//Set for loop to iterate all topics
		for(int i=0;i<topicBank.size();i++)
		{
			//if topic name is same
			if(topicBank.get(i).getName().equals(topic))
			{
				decision=true;
				targetIndex=i;
			}
		}
		//When topic is exist
		if(decision)
		{
			//Get topic and add the quesiton node
			topicBank.get(targetIndex).addQuestion(curr);
			
		}
		//Otherwise
		else
		{
			//Initiate topic
			Topic currTopic = new Topic(topic);
			//Add the question node 
			currTopic.addQuestion(curr);
			//Add the topic to the banke
			this.topicBank.add(currTopic);
		}
		//Increment the total num
		totalNum++;
	}
	/**
	 * This loadQuizzes method would load the file with json format 
	 * @param fileName to load
	 * 
	 */
	public void loadQuizzes(String fileName) 
	{
		//Set try part for exception while loading
		try
		{
			//Get the Parser of file
			Object obj = new JSONParser().parse(new FileReader(fileName));
			//Cast to the json object 
			JSONObject jo = (JSONObject) obj;
			//Get the questions of jsonArray
			JSONArray questionArray = (JSONArray)jo.get("questionArray");
			//SEt for loop to iterate all jsonArray element
			for(int i0=0;i0<questionArray.size();i0++)
			{
				//Get json array of indi question
				JSONObject indiQuestion = (JSONObject)questionArray.get(i0);
				//Get each elemnets in string
				String metaJ = (String) indiQuestion.get("meta-data");
				String qTextJ = (String) indiQuestion.get("questionText");
				String topicJ = (String)indiQuestion.get("topic");
				String imageJ = (String)indiQuestion.get("image");
				//When image is none change to empty string
				if(imageJ.equals("none"))
				{
					imageJ="";
				}
				//Get choices of json array
				JSONArray choicesJ = (JSONArray) indiQuestion.get("choiceArray");
				//Initiate boolean array to store answers 
				Boolean[] answers = new Boolean[choicesJ.size()];
				//Initiate Stirng array to store choices
				String[] choices = new String[choicesJ.size()];
				//Set for loop all json array of choices
				for(int i=0;i<choicesJ.size();i++)
				{
					//Initiate json package with choices
					JSONObject jsonPackage=(JSONObject) choicesJ.get(i);
					//Get the isCorrect part for assign solution
					String decisionString=(String) jsonPackage.get("isCorrect");
					//Set the decision null
					Boolean decision=null;
					//When decisionString is "T"
					if(decisionString.equals("T"))
					{
						//Set the decision true
						decision=true;
					}
					//When decisionString is "F"
					if(decisionString.equals("F"))
					{
						//Set the decision false
						decision=false;
					}
					//Put in the answer array
					answers[i]=decision;
					//Get the individual choice
					String indiChoice = (String) jsonPackage.get("choice");
					//Put in the answer array
					choices[i]=indiChoice;
				}
				//Use save IndiQuiz method
				this.saveIndiQuiz(metaJ,topicJ,qTextJ,imageJ,choices,answers);
			}
		}
		//When the exception raised
		catch(Exception e)
		{
			System.out.print("Parser");
		}
	}
	
	
	/**
	 * This method save all current questions to json file format
	 * @param fileName of a file that will be hold all questions
	 */
	public void saveQuizzes(String fileName)
	{
		//Array list of question nodes that will hold all questions
		ArrayList<QuestionNode> all = new ArrayList<QuestionNode>();
		//for-loop to iterate all topics
		for(int i=0;i<topicBank.size();i++)
		{
			//Get all questions from the topic
			ArrayList<QuestionNode> curr = topicBank.get(i).getAll();
			//add all to the all
			all.addAll(curr);
		}
		//Json object 
		JSONObject obj = new JSONObject();
		//Json array that will hold all questions
		JSONArray questions = new JSONArray();
		//for loop to save individual question to question json array
		for(int i2=0;i2<all.size();i2++)
		{
			//Json object that will hold individual qustion
			JSONObject indiQuestion = new JSONObject();
			//Get a questionnode
			QuestionNode curQ = all.get(i2);
			//Get a meta data
			String metaData = curQ.getMetaData();
			//When meta data is empty
			if(metaData.isEmpty())
			{
				//Change to unused
				metaData="unused";
			}
			//Get quiz text topic and image name
			String questionText = curQ.getQuestionText();
			String topic = curQ.getTopic();
			String image = curQ.getImageFileName();
			//When image name is empty
			if(image.isEmpty())
			{
				image="none";
			}
			//put each questions element to indiQuestion object
			indiQuestion.put("meta-data",metaData);
			indiQuestion.put("questionText",questionText);
			indiQuestion.put("topic",topic);
			indiQuestion.put("image",image);
			
			//String array to put choices
			String[] choices = curQ.getChoiceArray();
			//Boolean array to put solution
			Boolean[] solution = curQ.getSolutionArray();
			//Json array to put choice and solution set
			JSONArray choiceArray = new JSONArray();
			for(int i3=0;i3<choices.length;i3++)
			{
				//Json object to put individual object
				JSONObject indiChoice = new JSONObject();
				//When choice is correct one
				if(solution[i3])
				{
					indiChoice.put("isCorrect","T");
				}
				//When choice is wrong one
				else
				{
					indiChoice.put("isCorrect","F");
				}
				//put choice text
				indiChoice.put("choice",choices[i3]);
				//Add individual choice to array
				choiceArray.add(indiChoice);
			}
			//Add choice array to individual question
			indiQuestion.put("choiceArray",choiceArray);
			//Add individual question to question array
			questions.add(indiQuestion);
		}
		//put question array to object
		obj.put("questionArray",questions);
		//Try clause to catch exception while saving
		try
		{
			//make file instance with filename
			File file = new File(fileName);
			//Create filewriter instance
			FileWriter fileWriter = new FileWriter(file);
			//Write the object to the file and close
			fileWriter.write(obj.toJSONString());
			fileWriter.flush(); 
			fileWriter.close();
			
		}
		catch(Exception e)
		{
			System.out.print("Finish");
		}
	}
	/**
	 * generate random question within topic list 
	 * @param topicList list of topic that generated questions will be included
	 * @param num number of question that this method make
	 * @return arrayList of question node
	 */
	public ArrayList<QuestionNode> generateRandomQuizzes(ArrayList<String> topicList, int num)
	{
		//arraylist of question node that will be returned
		ArrayList<QuestionNode> returnQuestionNodes = new ArrayList<QuestionNode>();
		//Make arrayList of topic that will hold topics
		ArrayList<Topic> currTopics = new ArrayList<Topic>();
		//The total number of questions in the selected topics
		int selectedTotalNum=0;
		//for loop to convert list of topicname to topic list 
		for(int i=0;i<topicList.size();i++)
		{
			//Get topic name
			String topicName = topicList.get(i);
			//Generate topic instance to store topic
			Topic currTopic=null;
			//For loop to find the topic that have same name
			for(int i2=0;i2<topicBank.size();i2++)
			{
				//When topic name is same 
				if(topicName.equals(topicBank.get(i2).getName()))
				{
					//Get the topic
					currTopic=topicBank.get(i2);
					//Add the topic to the topic list
					currTopics.add(currTopic);
					//Increment the total number of questions
					selectedTotalNum+=currTopic.getNum();
				}
			}
		}
		//When the asked number of questions is less than the total 
		//number of question
		if(num<selectedTotalNum)
		{
			//Get the number of question for each topic
			Integer[] indiNum = new Integer[currTopics.size()];
			for(int i2=0;i2<currTopics.size();i2++)
			{
				indiNum[i2]=currTopics.get(i2).getNum();
			}
//			int maxNumIndex=-1;
//			for(int i3=0;i3<indiNum.length;i3++)
//			{
//				if(maxNumIndex==-1)
//				{
//					maxNumIndex=0;
//				}
//				else
//				{
//					if(indiNum[i3]>=indiNum[maxNumIndex])
//					{
//						maxNumIndex=i3;
//					}
//				}
//			}
			//Get the density of each topic
			Double[] indiDensity = new Double[currTopics.size()];
			//For loop to iterate all topics
			for(int i4=0;i4<currTopics.size();i4++)
			{
				//get the number of questions of current topic
				Double curr = new Double(indiNum[i4]);
				//Get density of each topic
				indiDensity[i4]=curr/selectedTotalNum;
			}
			//Get the spread of each topics
			Integer[] numSpread = new Integer[currTopics.size()];
			//Integer to store spread's total
			int spreadTotal=0;
			//For-loop to assign each topic's number of questions
			//That will be generated
			for(int i5=0;i5<currTopics.size();i5++)
			{
				//Get the density of each topic
				Double curr = num*indiDensity[i5];
				//Get indi topic's spread
				int indiSpread = curr.intValue();
				//assign the spread 
				numSpread[i5]=indiSpread;
				//Increment the total spread
				spreadTotal+=indiSpread;
			}
			//index of spread
			int index=0;
			//While loop to sync the spread and number of question asked
			while(spreadTotal!=num)
			{
				//Increment spread element by 1
				numSpread[index]=numSpread[index]+1;
				//Increment total spread
				spreadTotal++;
				//Incread the index
				index++;
			}
			//for loop to generate random question
			for(int i6=0;i6<currTopics.size();i6++)
			{
				//Add all questions
				returnQuestionNodes.addAll(currTopics.get(i6).getRondomQuizzes(numSpread[i6]));
			}
		}
		//When the asked num is too hish
		else
		{
			//For-loop to generate all questions
			for(int i7=0;i7<currTopics.size();i7++)
			{
				returnQuestionNodes.addAll(currTopics.get(i7).getAll());
			}
		}
		//Return the generated questions
		return returnQuestionNodes;
	}
	/**
	 * return topicbank's topic name in arraylist
	 * @return the name of all topics
	 */
	public ArrayList<String> getNameList()
	{
		//ArrayList of string to store all topic name
		ArrayList<String> nameList=new ArrayList<String>();
		//for loop to iterate all topic list
		for(int i=0;i<topicBank.size();i++)
		{
			//Add each name
			nameList.add(topicBank.get(i).getName());
		}
		//Return the all name list
		return nameList;
	}
	/**
	 * Return the number of topic in topicBank
	 * @return the number of topic
	 */
	public int getNumOfTopic()
	{
		//Return the number of topic
		return topicBank.size();
	}
	/**
	 * return the number of question in topicBank
	 * @return the number of all question 
	 */
	public int getSize()
	{
		//Return the number of questions
		return totalNum;
	}
}
