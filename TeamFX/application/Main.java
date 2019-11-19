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
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 * This GUI provide several method for user to solve,edit,load and save quizzes
 * 
 *
 */
public class Main extends Application
{
	//
	private QuizControl quiz;			//Main quizcontrol class
	private BorderPane root;			//instance of border pane
	private ArrayList<String> store;	//ArrayList to store question info
	private ArrayList<Boolean> answers;	//ArrayList to check correct answer
	private ArrayList<QuestionNode> quizList;	//ArrayList of Quiznodes
	private int numSolved;
	
	/**
	 * main method for gui
	 * @param args Sting array parameter
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
	/**
	 * This method show the starting page of gui
	 */
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		try {
			//Set the stage titile
			primaryStage.setTitle("Quiz Generator");
			//Initiate each instant field
			//this. quiz=new QuizControl();
			this.quiz = new QuizControl();
			this.root=new BorderPane();
			this.store= new ArrayList<String>();
			this.answers= new ArrayList<Boolean>();
			this.numSolved=0;
			//Generate a Flowpane for Top
			FlowPane flowPaneTop = new FlowPane(200,20);
			//Set the vertical gap between children
			flowPaneTop.setVgap(4);
			//Initate the hbox to put title
			HBox titleBox = new HBox(100);
			//Create a Text to welcoming the user
			Text sceneTitle = new Text("Main Window                                                  ");
			//Add title to hbox
			titleBox.getChildren().add(sceneTitle);
			//Highlit it with yellow color
			titleBox.setStyle("-fx-background-color:yellow;");
			//Set empty text for shape
			Text empty=new Text("");
			//Set the font and size of the text
			sceneTitle.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
			//Initate description
			Text description = new Text("This Quiz Generator let you solve"
					+ " \nrandomly selected question with topic you select\n");
		
	
			//Set the font and size of the text
			description.setFont(Font.font("Tahoma",FontWeight.NORMAL,16));
			
			//Initiate description about add button
			Text addDescription = new Text("Each mode's function is following\nAdd:Can add an customized quiz ");
			//Set the font and size 
			addDescription.setFont(Font.font("Tahoma",FontWeight.NORMAL,16));
			//Initiate description about load button
			Text loadDescription = new Text("Load:Upload the JSON quiz file to the program ");
			//Set the font and size 
			loadDescription.setFont(Font.font("Tahoma",FontWeight.NORMAL,16));
			
			//Initiate description about save button
			Text saveDescription = new Text("Save:Store current quiz");
			//Set the font and size 
			saveDescription.setFont(Font.font("Tahoma",FontWeight.NORMAL,16));
			//Make text expliaing topic
			Text topicList = new Text("Topics: Codes, Graphs, Lectures\n");
			topicList.setFont(Font.font("Tahoma",FontWeight.NORMAL,16));
			Text empty2 = new Text("  ");
			Text empty3 = new Text("  ");
			
			//Add the text to the FlowPane instance
			flowPaneTop.getChildren().addAll(titleBox,empty,description,topicList,addDescription,
											loadDescription,saveDescription,empty2,empty3);
			//Add it to Top
			root.setTop(flowPaneTop);
			
			//Make habox to put buttons
			HBox hboxButtons = new HBox();
			//Set padding and spacing
			hboxButtons.setPadding(new Insets(10,10,10,10));
			hboxButtons.setSpacing(10);
		
			//Create Add button
			Button buttonAdd = new Button("Add");
			//Add event handler to the add button
			buttonAdd.setOnAction(new EventHandler<ActionEvent>()
			{
				/**
				 * This method hendle when the add button is clicked
				 * @param e The event when the button is clicked
				 */
				@Override
				public void handle(ActionEvent e)
				{
					//Draw add Window
					drawAddWindow();
				}

			
			});
		
			//Create the load button
			Button buttonLoad = new Button("Load");
			//Set event handler to loas button
			buttonLoad.setOnAction(new EventHandler<ActionEvent>()
			{
				/**
				 * This method handle when the button is clicked
				 * @param e The event when the button is clicked
				 */
				@Override
				public void handle (ActionEvent e)
				{
					//Draw load window
					drawLoadWindow();
				}
			});
			//Create the Save button
			Button buttonSave = new Button("Save");
			//Set event handler to save button
			buttonSave.setOnAction(new EventHandler<ActionEvent>()
			{
				/**
				 * This method handle when the button is clicked
				 * @param e the event when the button is clicked
				 */
				@Override
				public void handle (ActionEvent e)
				{
					//Draw Save window
					drawSaveWindow();
				}
			});
			//Add all buttons to hbox
			hboxButtons.getChildren().addAll(buttonAdd,buttonLoad,
												buttonSave);
			//Set position of hbox
			hboxButtons.setAlignment(Pos.TOP_LEFT);
			//Add hboxbutton into borderpane instance's center
			root.setCenter(hboxButtons);
			//Get the current number of quizzes
			int currentNum = quiz.getSize();
			
			//Set the number of remain card in text
			Text remainQuizzes = new Text("Current Quizzes: " + currentNum);
			//Set the font and size of the text
			remainQuizzes.setFont(Font.font("Tahoma",FontWeight.NORMAL,16));
			//Text to alert
			Text alert = new Text("");
			//Create the run button
			Button buttonRun = new Button("Run");
			//Set event handler for run button
			buttonRun.setOnAction(new EventHandler<ActionEvent>() 
			{
				/**
				 * This method handle when the button is clicked
				 * @param e the event when the button is clicked
			 	*/
				@Override
				public void handle(ActionEvent e)
				{
					if(currentNum==0)
					{
						alert.setText("Should load any question");
						alert.setFill(Color.RED);
					}
					else
					{
						//Draw Run window
						drawRunWindow();
					}
				}
			});
			//Create hbox for bottom of border pane
			HBox bottomBox = new HBox();
			//bottomBox.getChildren
			bottomBox.getChildren().addAll(remainQuizzes,buttonRun,alert);
			//Give space between children
			bottomBox.setSpacing(50);
			//Set to the border pane
			root.setBottom(bottomBox);

			//Generate the Scene instance with BorderPane instance
			Scene scene =new Scene(root,400,400);
			//Set scene into Stage instance
			primaryStage.setScene(scene);
			//Show the output
			primaryStage.show();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * This method draw add window to get a quiz information
	 */
	public void drawAddWindow()
	{
		
		//Generate a Flowpane for Top
		FlowPane flowPaneTop = new FlowPane(200,20);
		HBox titleBox = new HBox();
		//Create a Text to welcoming the user
		Text sceneTitle = new Text("ADD Window                                                  ");
		//Set the font and size
		sceneTitle.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
		//Add the title to the hbox
		titleBox.getChildren().add(sceneTitle);
		//Set the color of hbox
		titleBox.setStyle("-fx-background-color:yellow;");
		//Add box to the flowPanetop
		flowPaneTop.getChildren().add(titleBox);
		//Set to the top of the borderpane
		root.setTop(flowPaneTop);
		//Create flowPane instance to put in the center
		FlowPane flowPaneCenter = new FlowPane();
		//Label instance for meta-data
		Label metaDataLabel = new Label("Meta-data: ");
		//Textfiled for meta-data
		TextField metaDTextField = new TextField();
		//Hbox for meta data
		HBox metaDataHbox = new HBox();
		//Add all instances to hbox
		metaDataHbox.getChildren().addAll(metaDataLabel,metaDTextField);
		//Add meta-data hbox to flowPaneCenter
		flowPaneCenter.getChildren().add(metaDataHbox);
		
		//Make label for topic
		Label topicLabel = new Label("Topic:         ");
		//Make textField for topic
		TextField topicTextField = new TextField();
		//Make hbox for topic
		HBox topicHbox = new HBox();
		//Add label and textField to hbox
		topicHbox.getChildren().addAll(topicLabel,topicTextField);
		//Add the hbox to flowPaneCenter
		flowPaneCenter.getChildren().add(topicHbox);
		
		//Make label for image
		Label imageLabel = new Label("Image:         ");
		//Make textfiled for image
		TextField imageTextField= new TextField();
		//Make hbox for image
		HBox imageHbox = new HBox();
		//Add label and textField to hbox
		imageHbox.getChildren().addAll(imageLabel,imageTextField);
		//Add the hbox to flowPaneCenter
		flowPaneCenter.getChildren().add(imageHbox);
		
		//Make label for question
		Label questionLabel = new Label("Question:    ");
		//Make textField for question
		TextField questionTextField = new TextField();
		//Make hbox for question
		HBox questionHbox = new HBox();
		//ADd label and textField to hbox
		questionHbox.getChildren().addAll(questionLabel,questionTextField);
		//Add the hbox to flowPaneCenter 
		flowPaneCenter.getChildren().add(questionHbox);
		
		//Make label for number of quesiton
		Label numOfChoiceLabel = new Label("Number of Choice");
		//Make textField for num of question
		TextField numOfchoiceTextField= new TextField();
		//Make hbox for num of quesiton
		HBox numChoiceBox = new HBox();
		//add label and textfield to hbox
		numChoiceBox.getChildren().addAll(numOfChoiceLabel,numOfchoiceTextField);
		//Add the hbox to flowPaneCenter
		flowPaneCenter.getChildren().add(numChoiceBox);
		
		//Set flowPaneCenter in center
		root.setCenter(flowPaneCenter);
		
		//Make vbox to set bottom
		VBox vboxBottom = new VBox();
		

		//Add all buttons to the scene
		HBox hboxButtons = new HBox();
		//Give Padding
		hboxButtons.setPadding(new Insets(10,10,10,10));
		//Give space between children
		hboxButtons.setSpacing(10);
		//Initiate alert for input check
		Text alert = new Text("");
		//Make submit button
		Button submitButton = new Button("Submit");
		//Give event for submit button 
		submitButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			/**
			 * Store the value and go to choices 
			 * @param when button is clicked
			 */
			@Override
			public void handle (ActionEvent e)
			{
				//Get textFileds' input
				String metaData=metaDTextField.getText();
				String topic = topicTextField.getText();
				String question= questionTextField.getText();
				String image = imageTextField.getText();
				String numOfChoice=numOfchoiceTextField.getText();
				
				//Check whether the input is null
				if(metaData==null||topic==null||question==null
						||numOfChoice==null||image==null)
				{
					//Set the alert message and color
					alert.setText(" Should put string to field");
					alert.setFill(Color.RED);
				}
				//Otherwise
				else
				{
					//Check whether each textfield is empty
					if(topic.isEmpty()||question.isEmpty()||numOfChoice.isEmpty())
					{
						//Set the alert message and color
						alert.setText(" Should put string to field");
						alert.setFill(Color.RED);
						
					}
					//Otherwise
					else 
					{
						//Check whether imagefile is exist
						boolean imageFileExistence = true;
						//If the image file is not empty
						if(!image.isEmpty())
						{
							//Get the file instance
							File imageFile = new File(image);
							//Check it is exist
							if(!imageFile.exists())
							{
								imageFileExistence=false;
							}
						}
						//When image file is exist or the image filename is not provided
						if(imageFileExistence)
						{
							//Store each value and go to another window for choices
							store.add(metaData);
							store.add(topic);
							store.add(question);
							store.add(image);
							store.add(numOfChoice);
							drawChoiceAskingWindow();
						}
						//Otherwise
						else
						{
							//Set the alert message and color
							alert.setText(" Should put exist image file");
							alert.setFill(Color.RED);
						}
					}
					
				}
			}
		});
		//Add submit button and alert text to hbox
		hboxButtons.getChildren().addAll(submitButton,alert);
		//Add hbox to vbox
		vboxBottom.getChildren().addAll(hboxButtons);
		//Set this vbox to bottom of border
		root.setBottom(vboxBottom);
	}
	/**
	 * This method draw askingwindow 
	 */
	public void drawChoiceAskingWindow()
	{
		//Get number of choices the user nominate
		int num = Integer.parseInt(store.get(4));
		
		//Create Hbox for the title
		HBox titleBox= new HBox();
		//Set title for this window
		Text sceneTitle = new Text("Choice Selecting Window                               ");
		//Set font
		sceneTitle.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
		//Add text to title box
		titleBox.getChildren().add(sceneTitle);
		//Color the box
		titleBox.setStyle("-fx-background-color:yellow;");
		
		//Locate to the top
		root.setTop(titleBox);
		//Create Vbox to CEnter
		VBox vboxCenter= new VBox();
		//Create Array of Label, TextField, HBox and CheckBox
		Label[] labels = new Label[num];
		TextField[] fields = new TextField[num];
		HBox[] boxes= new HBox[num];
		CheckBox[] cBoxes = new CheckBox[num];
		//Initiate textFileds with each checkbox
		for(int i=0;i<num;i++)
		{
			int indexPlusOne=i+1;
			labels[i]=new Label("Choices"+indexPlusOne+": ");
			fields[i]=new TextField();
			cBoxes[i]= new CheckBox();
	
			boxes[i]=new HBox();
			boxes[i].getChildren().addAll(cBoxes[i],labels[i],fields[i]);
			vboxCenter.getChildren().add(boxes[i]);
		}
		//Add it to root's center
		root.setCenter(vboxCenter);
		
		//Create Hbox to put root's bottom
		HBox hboxBottom = new HBox();
		//Make submit button
		Button submitButton= new Button("Submit");
		//Initiate alert text
		Text alert = new Text("");
		
		//When submite button is clicked
		submitButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			/**
			 * Store the value and go to choices 
			 * @param when button is clicked
			 */
			@Override
			public void handle (ActionEvent e)
			{
				//To check whether each text field is empty or null
				boolean decison = true;
				for(int i2= 0;i2<num;i2++)
				{
					if(fields[i2].getText()==null||fields[i2].getText().isEmpty())
					{
						decison= false;
					}
				}
				//If decision is false which mean input has problem
				if(!decison)
				{
					//LEt the user know
					alert.setText(" Should not null nor empty");
					alert.setFill(Color.RED);
					
				}
				//Ohterwise
				else
				{
					//Store each input to store
					for(int i3=0;i3<num;i3++)
					{
						store.add(fields[i3].getText());
						boolean isSelected = cBoxes[i3].isSelected();
						answers.add(isSelected);
					}
					//Get Questions's information
					String metaData = store.get(0);
					String topic = store.get(1);
					String questionText = store.get(2);
					String imageName= store.get(3);
					//String array to store choices
					String[] choices = new String[Integer.parseInt(store.get(4))];
					//Boolean array to store solution
					Boolean[] answersArray = new Boolean[Integer.parseInt(store.get(4))];
					//For loop to iterate all choices and answers
					for(int i=5;i<store.size();i++)
					{
						choices[i-5]=store.get(i);
						answersArray[i-5]=answers.get(i-5);
						
					}
					//Save individual question
					quiz.saveIndiQuiz(metaData,topic,questionText,imageName,choices,answersArray);
						//Create the question class instance 
						//Clear all store and answer
						store=new ArrayList<String>();
						answers=new ArrayList<Boolean>();
						
						//Go back to mainwindow
						drawMainPage();
					
				}
			}
		
		});
		//Add all submitbutton and alert to the hbox
		hboxBottom.getChildren().addAll(submitButton,alert);
		//Put the hbox to root's bottom
		root.setBottom(hboxBottom);
	}
	/**
	 * This method show load window
	 */
	public void drawLoadWindow() {
		
		//HBox to be put in root's top
		HBox topMenu = new HBox();
		//Create text to put
		Text sceneTitle = new Text("Load Window                                               ");
		//Set font and size
		sceneTitle.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
		//Add text to hbox
		topMenu.getChildren().add(sceneTitle);
		//Colot the hbox yellow
		topMenu.setStyle("-fx-background-color:yellow;");
		//Put the hbox to root's top
		root.setTop(topMenu);
		
		//flowPane instance to put at root's center
		FlowPane flowPaneCenter = new FlowPane();
		//Label to ask filename
		Label fileNameLabel = new Label("File Name: ");
		//TextField to get filename
		TextField fileNameTextField = new TextField();
		//Hbox to put label and textField
		HBox fileNameHbox = new HBox();
		fileNameHbox.getChildren().addAll(fileNameLabel, fileNameTextField);

		//Add the hbox to flowPane
		flowPaneCenter.getChildren().add(fileNameHbox);
		//Give position
		flowPaneCenter.setAlignment(Pos.CENTER);
		//Set to the root's center
		root.setCenter(flowPaneCenter);
		
		//hbox that will be put in root's bottom
		HBox bottom = new HBox();
		//Make load button
		Button loadButton = new Button("Load");
		//Create alert
		Text alert = new Text("");
		//Add event handler for click
		loadButton.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * Store the value and go to choices
			 * 
			 * @param when button is clicked
			 */
			@Override
			public void handle(ActionEvent e) {
				//Get filename in string
				String fileNameString = fileNameTextField.getText();
				
				//Try to load file
				File temp = new File(fileNameString);
				//Check whetehr it is exist
				boolean exist = temp.exists();
				//When filename is nuull or empty or does not exist
				if (fileNameTextField.getText() == null || fileNameTextField.getText().isEmpty()||!(exist)) 
				{
					//Set the alert text and color
					alert.setText("Filename is not valid!!");
					alert.setFill(Color.RED);
				}
				//Otherwise
				else 
				{
					quiz.loadQuizzes(fileNameString);
					//Go back to main page
					drawMainPage();
				}
			}
		});
		//Add all alert and load button to the hbox
		bottom.getChildren().addAll(loadButton,alert);
		//Set the position
		bottom.setAlignment(Pos.BOTTOM_LEFT);
		//Set to the bottom
		root.setBottom(bottom);
	}
	/**
	 * This method draw main page
	 */
	public void drawMainPage()
	{
		//Generate a Flowpane for Top
		FlowPane flowPaneTop = new FlowPane(200,20);
		flowPaneTop.setVgap(4);
		HBox titleBox = new HBox(100);
		//Create a Text for title
		Text sceneTitle = new Text("Main Window                                                  ");
		//Add the title to the box
		titleBox.getChildren().add(sceneTitle);
		//Color the title
		titleBox.setStyle("-fx-background-color:yellow;");
		//Empty text for positioning
		Text empty=new Text("");
		//Set the font and size of the text
		sceneTitle.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
		
		//Initate description
		Text description = new Text("This Quiz Generator let you solve"
						+ " \nrandomly selected question with topic you select\n");
				
			
		//Set the font and size of the text
		description.setFont(Font.font("Tahoma",FontWeight.NORMAL,16));
				
		//Initiate description about add button
		Text addDescription = new Text("Each mode's function is following\nAdd:Can add an customized quiz ");
		addDescription.setFont(Font.font("Tahoma",FontWeight.NORMAL,16));
		
		//Description for load
		Text loadDescription = new Text("Load:Upload the JSON quiz file to the program ");
		loadDescription.setFont(Font.font("Tahoma",FontWeight.NORMAL,16));
		
		//Description for save
		Text saveDescription = new Text("Save:Store current quiz");
		saveDescription.setFont(Font.font("Tahoma",FontWeight.NORMAL,16));
		
		//In the final submission we will get list of topic from controller
		Text topicList = new Text("Topics: Codes, Graphs, Lectures\n");
		topicList.setFont(Font.font("Tahoma",FontWeight.NORMAL,16));
		
		//Empty text for positioning
		Text empty2 = new Text("  ");
		Text empty3 = new Text("  ");
		
		//Add the text to the FlowPane instance
		flowPaneTop.getChildren().addAll(titleBox,empty,description,topicList,addDescription,
													loadDescription,saveDescription,empty2,empty3);
		
		//Add it to Top
		root.setTop(flowPaneTop);
		
		//Hbox for buttons
		HBox hboxButtons = new HBox();
		//Gibe padding and spacing
		hboxButtons.setPadding(new Insets(10,10,10,10));
		hboxButtons.setSpacing(10);
		
		//Create  add button and give action for click event
		Button buttonAdd = new Button("Add");
		buttonAdd.setOnAction(new EventHandler<ActionEvent>()
		{
			/**
			 * This method hendle when the add button is clicked
			 * @param e The event when the button is clicked
			 */
			@Override
			public void handle(ActionEvent e)
			{
				drawAddWindow();
			}

			
		});
		//Create the load button and give action for click event
		Button buttonLoad = new Button("Load");
		buttonLoad.setOnAction(new EventHandler<ActionEvent>()
		{
			/**
			 * This method handle when the button is clicked
			 * @param e The event when the button is clicked
			 */
			@Override
			public void handle (ActionEvent e)
			{
				//Draw load window
				drawLoadWindow();
			}
		});
		
		//Create the Save button and give aciton for click event
		Button buttonSave = new Button("Save");
		buttonSave.setOnAction(new EventHandler<ActionEvent>()
		{
			/**
			 * This method handle when the button is clicked
			 * @param e the event when the button is clicked
			 */
			@Override
			public void handle (ActionEvent e)
			{
				//Draw Save window
				drawSaveWindow();
			}
		});
		//Add all buttons to the box
		hboxButtons.getChildren().addAll(buttonAdd,buttonLoad,
											buttonSave);
		//Give positiom to the box
		hboxButtons.setAlignment(Pos.TOP_LEFT);
		//Add hboxbutton into root's center
		root.setCenter(hboxButtons);
		//Get the current number of quizzes
		//int currentNum = quiz.getNum();
		int currentNum=quiz.getSize();
		//Set the number of remain card in text
		Text remainQuizzes = new Text("Current Quizzes: " + currentNum);
		//Set the font and size of the text
		remainQuizzes.setFont(Font.font("Tahoma",FontWeight.NORMAL,16));
		//Create run button and add function
		Button buttonRun = new Button("Run");
		//Set alert text
		Text alert = new Text("");
		buttonRun.setOnAction(new EventHandler<ActionEvent>() 
		{
			/**
			 * This method handle when the button is clicked
			 * @param e the event when the button is clicked
			 */
			@Override
			public void handle(ActionEvent e)
			{
				//When currentNum is 0
				if(currentNum==0)
				{
					//SEt alert text
					alert.setText("Should load any question");
					alert.setFill(Color.RED);
				}
				else
				{
					//Draw Run window
					drawRunWindow();
				}
			}
		});
		//Create box to put root's bottom
		HBox bottomBox = new HBox();
		//Add current quizzes and run button
		bottomBox.getChildren().addAll(remainQuizzes,buttonRun,alert);
		//Give space btw children
		bottomBox.setSpacing(50);
		root.setBottom(bottomBox);
		
	}
	/**
	 * This method draw save window
	 */
	public void drawSaveWindow() 
	{
		// Generate a Flowpane for Top
		FlowPane flowPaneTop = new FlowPane(200, 20);
		HBox titleBox1 = new HBox(100);
		// Create a Text to welcoming the user
		Text sceneTitle = new Text("Save Window                                                  ");
		//Set font and size
		sceneTitle.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
		//Add the text to the box
		titleBox1.getChildren().add(sceneTitle);
		//Color the box with yellow
		titleBox1.setStyle("-fx-background-color:yellow;");
		flowPaneTop.getChildren().addAll(titleBox1);
		//Add the flowpane to the top of root
		root.setTop(flowPaneTop);

		//FlowPane instance for center
		FlowPane flowPaneCenter = new FlowPane();
		//Label and textField for file name
		Label fileNameLabel = new Label("File Name: ");
		TextField fileNameField = new TextField();
		//Make hbox to put label and textField
		HBox fileNameHbox = new HBox();
		fileNameHbox.getChildren().addAll(fileNameLabel, fileNameField);
		//Add hbox to the flow pane
		flowPaneCenter.getChildren().add(fileNameHbox);
		flowPaneCenter.setAlignment(Pos.CENTER);
		//Add the flowpane to the root's center
		root.setCenter(flowPaneCenter);
		//Create alert text
		Text alert = new Text("");
		//Create save button and give action
		Button buttonSAVE = new Button("SAVE");
		buttonSAVE.setOnAction(new EventHandler<ActionEvent>() {
				/**
				 * This method handle when the button is clicked
				 * 
				 * @param e the event when the button is clicked
				 */
				@Override
				public void handle(ActionEvent e) {
					//Get filename string that user put
					String fileNameString = fileNameField.getText();
					//If filename is null or empty
					if(fileNameString==null||fileNameString.isEmpty())
					{
						//Set the alert text
						alert.setText("Should put file Name");
						alert.setFill(Color.RED);
					}
					else
					{
						quiz.saveQuizzes(fileNameString);
						// Draw main window
						drawMainPage();
					}
				}
		});
		
		
		//Make Box to put button and alert
		HBox bottomBox = new HBox();
		bottomBox.getChildren().addAll(buttonSAVE,alert);
		//Give spacing for apperance
		bottomBox.setSpacing(170);
		//Set this box to root's bottom
		root.setBottom(bottomBox);
	}
	/**
	 * Draw window that ask topic and number of question
	 */
	public void drawRunWindow() 
	{
		//Generate a Flowpane for Top
		FlowPane flowPaneTop = new FlowPane();
		HBox titleBox = new HBox(100);
		//Create a Text for Window mode
		Text sceneTitle = new Text("Quiz Setting                                                ");
		//Set font and size
		sceneTitle.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
		//Add text to the box
		titleBox.getChildren().add(sceneTitle);
		//Set the color yellow
		titleBox.setStyle("-fx-background-color:yellow;");
		//Add the box to the flow pane
		flowPaneTop.getChildren().add(titleBox);
		//Set the flowpane to the root's top
		root.setTop(flowPaneTop);
		
		//Make flowPane for the center
		FlowPane flowPaneCenter = new FlowPane();
		//Label for topic list
		Label topicListLabel = new Label("Topic List: Check the topic you want to solve ");  
		//Vbox to put all checkbox and instruction
		VBox topicListVBox = new VBox();
		//Add the instruction
		topicListVBox.getChildren().add(topicListLabel);

		//get the number of questions
		int numOfTopics=quiz.getNumOfTopic();
		//Arary of string that store topic
		String[] option = new String[numOfTopics];
		//Get list of topic
		ArrayList<String> topicList=quiz.getNameList();
		//for loop to iterate the topicList
		for (int i = 0; i <numOfTopics; ++i) 
		{
			option[i] = topicList.get(i);
		}
		//ArrayOf CheckBox 
		CheckBox[] checkBoxes=new CheckBox[numOfTopics];
		//for loop to make checkbox and add it to vbox
		for (int i = 0; i < option.length; ++i) {
			checkBoxes[i] = new CheckBox(option[i]);
			topicListVBox.getChildren().add(checkBoxes[i]);
			checkBoxes[i].setIndeterminate(true);
		}
		//Give padding and spacing
		topicListVBox.setPadding(new Insets(10,10,10,10));
		topicListVBox.setSpacing(10);
		
		//Make label and textField to ask the number of Question
		Label solveNumQuestionLabel = new Label("Number of Questions: ");
		TextField numQuestionTextField = new TextField();
		//Create hbox to put label and textField
		HBox numQuestionHBox = new HBox();
		//Add all to the hbox
		numQuestionHBox.getChildren().addAll(solveNumQuestionLabel, numQuestionTextField);
		//empty text for appearance
		Text empty = new Text("");
		//Add this to the flowpane
		topicListVBox.getChildren().addAll(empty,numQuestionHBox);
		
		//Add the flowpane to the center of root
		root.setCenter(topicListVBox);

		//Hbox to put button
		HBox buttonBox = new HBox();
		//Alert for invalid input
		Text alert = new Text();
		//Make start button and give function
		Button startButton = new Button("Start");
		startButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			/**
			 * Store the value and go to choices 
			 * @param when button is clicked
			 */
			@Override
			public void handle (ActionEvent e)
			{
				//Get the input 
				String numOfQuestion =numQuestionTextField.getText();
				//When the input is invalid
				if(numOfQuestion==null||numOfQuestion.isEmpty())
				{
					//Set the alert
					alert.setText("Should put somting in the TextField");
					alert.setFill(Color.RED);
				}
				//Otherwise
				else
				{
					
					//for loop to search which box is selected
					for(int i=0;i<numOfTopics;i++)
					{
						//When the box selected, add topic to store field
						Boolean selection = checkBoxes[i].isSelected();
						if(selection)
						{
							store.add(topicList.get(i));
						}
					}
					//Get the number user insert
					String numString=numQuestionTextField.getText();
					
					int numOfQues=-1;
					//For the case when the exception happen while parsing
					try
					{
						Integer.parseInt(numString);
					}
					//Let the user know should put the number
					catch(Exception error)
					{
						alert.setText("Should put number");
						alert.setFill(Color.RED);
					}
					//Convert to the integer
					numOfQues=Integer.parseInt(numString);

					//Get the list of questionNodes by using QuizController
					quizList=quiz.generateRandomQuizzes(store,numOfQues);
					//Clear the store arrayList
					store = new ArrayList<String>();
					//When topic is selected
					if(quizList.size()!=0)
					{
						//Draw the next question
						drawStartWindow();
					}
					//Otherwise
					else
					{
						//Set alert
						alert.setText("Should pick topic");
						alert.setFill(Color.RED);
					}
					
				}

			}
		});
		//Add alert and button
		buttonBox.getChildren().addAll(startButton,alert);
		//Add to the root's bottom
		root.setBottom(buttonBox);
	}
	/**
	 * This method get the list of quesiton that randomly selected based 
	 * on the user select topic and number of question
	 * @param topicList list of topic the user selected
	 * @param numQuestion number of question the user will solve
	 */
	public void drawStartWindow()
	{
		
		//Make the flowpane for top
		FlowPane flowPaneTop = new FlowPane(200,20);
		HBox titleBox = new HBox();
		//Create a Text to welcoming the user
		Text sceneTitle = new Text("Quiz                                                        ");
		//Set font and size
		sceneTitle.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));	
		//Add the text to the hbox
		titleBox.getChildren().add(sceneTitle);
		//Set the color
		titleBox.setStyle("-fx-background-color:yellow;");
		//Add it to the flowpane
		flowPaneTop.getChildren().add(titleBox);
		//Get the question node to show up to the window
		QuestionNode currentQ=quizList.get(numSolved);
		//Get metaData
		String meta = currentQ.getMetaData();
		if(!meta.equals("unused"))
		{
			if(!meta.isEmpty())
			{
				Text metaText = new Text("Meta data:"+meta);
				flowPaneTop.getChildren().add(metaText);
			}
		}
		//Get the image file name
		String imageName = currentQ.getImageFileName();
		
		//Check whether image file name is empty or null
		if(imageName!=null&&!imageName.isEmpty()&&!imageName.equals("none"))
		{
			//Get the image  
			Image image = new Image(imageName);
			//Make the image view
			ImageView centerImage = new ImageView();
			//Edit the image 
			centerImage.setImage(image);
			centerImage.setFitWidth(100);
			centerImage.setFitHeight(100);
			//Add to the top
			flowPaneTop.getChildren().add(centerImage);
		}
		//Set the flowpane to the top of root
		root.setTop(flowPaneTop);
		//Get the question Text 
		String questionText = currentQ.getQuestionText();
		//Get the string of choices
		String[] choiceArray = currentQ.getChoiceArray();
		//Get the solution of boolean variable
		Boolean[] solutionArray=currentQ.getSolutionArray();
		//get the current number
		int currentNum = this.numSolved+1;
		//Initiate the vBox
		VBox vboxCenter = new VBox();
		//Initiate the text for question text
		Text qText= new Text("Q."+currentNum+" "+questionText);
		//Add the text to the vbox
		vboxCenter.getChildren().add(qText);
		//Make the array of hbox with choice length
		HBox[] boxes = new HBox[choiceArray.length];
		//Make the array of label with choice length
		Label[] labels = new Label[choiceArray.length];
		//Make the array of checkbox with choice length
		CheckBox[] cBoxes = new CheckBox[choiceArray.length];
		
		//Make for loop to initiate the label, checkBox, and hbox with label and 
		//Check box
		for(int i=0;i<choiceArray.length;i++)
		{
			//Initiate the integer 
			int choiceNum=i+1;
			//Initiate the String with current number of questions
			String choiceNumString=String.valueOf(choiceNum);
			//Initiate the label with text with number tag
			labels[i]=new Label(choiceNumString+" : "+choiceArray[i]);
			//Initiate the check box
			cBoxes[i]= new CheckBox();
			//Initiate the Hbox 
			boxes[i]=new HBox();
			//put all label and checkbox to box
			boxes[i].getChildren().addAll(cBoxes[i],labels[i]);
			//put the made hbox to vboxCenter
			vboxCenter.getChildren().add(boxes[i]);
		}
		//Set the vboxCenter to the root's center
		root.setCenter(vboxCenter);
		//Make hbox to put buttons
		HBox buttons = new HBox();
		//Initiate the submit buttons
		Button submitButton = new Button("Submit");
		//Make arrayList to show the wrong answer
		ArrayList<Integer> choicesOfFalse= new ArrayList<Integer>();
		//add the event reaction for submit button
		submitButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			/**
			 * Store the value and go to choices 
			 * @param when button is clicked
			 */
			@Override
			public void handle (ActionEvent e)
			{
				//Make the button invisible 
				submitButton.setVisible(false);
				//boolean expression to check whether the user pick correct answer
				boolean correct = true;
				//for loop to traverse all choices
				for(int i2=0;i2<choiceArray.length;i2++)
				{
					//Check whether the user pick the choice
					boolean userSelect=cBoxes[i2].isSelected();
					//If the user select choice is not same with solution
					if(userSelect!=solutionArray[i2])
					{
						//when the picked choice is not correct one
						if(!solutionArray[i2])
						{
							//Change the correct to false
							correct=false;
							//Add the index of picked choice
							choicesOfFalse.add(i2);
						}
						//Otherwise
						else
						{
							//change to false
							correct=false;
						}
					}
				}
				//If the uer pick correct answer
				if(correct)
				{
					//Add true to the answers
					answers.add(correct);
				}
				//Otherwise
				else
				{
					//Add false to the answer
					answers.add(correct);
					//for loop to iterate all choices
					for(int i3=0;i3<choicesOfFalse.size();i3++)
					{
						//Let the user know the answer is wrong
						Text wrong = new Text("Wrong Answer");
						wrong.setFill(Color.RED);
						boxes[choicesOfFalse.get(i3)].getChildren().add(wrong);
					}
				}
				
				//make the next button and give actions for next button
				Button nextButton = new Button("Next");
				nextButton.setOnAction(new EventHandler<ActionEvent>()
				{
					/**
					 * When the next button is clicked, it check the number of
					 * Question solved, when doen go to result page
					 */
					@Override
					public void handle(ActionEvent e)
					{
						//Increment the number solved
						numSolved++;
						//When the user solve all questions 
						if(quizList.size()==numSolved)
						{
							//Set the numsolved field 0 for the future work
							numSolved=0;
							//Draw result page
							drawResultPage();
						}
						//Otherwise
						else
						{
							//Show the next question
							drawStartWindow();
						}
					}
				});
				//Add the next button
				buttons.getChildren().add(nextButton);
			}
		});
		//Add the submit button
		buttons.getChildren().add(submitButton);
		//Give padding and spacing
		buttons.setPadding(new Insets(10,10,10,10));
		buttons.setSpacing(100);
		//Set the buttons to the root's bottom
		root.setBottom(buttons);
		
	}
	/**
	 * This method draw result of the page
	 */
	public void drawResultPage()
	{
		//Make the flowpane for top
		FlowPane flowPaneTop = new FlowPane(200,20);
		HBox titleBox = new HBox();
		//Create a Text to welcoming the user
		Text sceneTitle = new Text("Result                                                        ");
		//Set font and size
		sceneTitle.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));	
		//Add the text to the hbox
		titleBox.getChildren().add(sceneTitle);
		//Set the color
		titleBox.setStyle("-fx-background-color:yellow;");
		//Add it to the flowpane
		flowPaneTop.getChildren().add(titleBox);
		//Add to the root's top
		root.setTop(flowPaneTop);
		//String to show correct
		String correct = "O";
		//String to show the wrong
		String wrong = "X";
		//To check the number of correct answer
		int correctNum =0;
		//Set vbox to put on the center
		VBox vboxCenter = new VBox();
		//Give padding and spacing
		vboxCenter.setPadding(new Insets(10,10,10,10));
		vboxCenter.setSpacing(10);
		//Initiate message to show the result
		Text message;
		//For loop to iterate the answers
		for(int i=0;i<answers.size();i++)
		{
			//Integer to show the current question
			int currNum=i+1;
			//Convert the current number to Stirng
			String currNumStirng=String.valueOf(currNum);
			//If the answer was correct
			if(answers.get(i))
			{
				//Show the answer was correct
				message = new Text(currNumStirng+" : "+correct+"\n");
				//Increment the correct num
				correctNum++;
			}
			//Otherwise
			else
			{
				//Show the answer was wrong
				message = new Text(currNumStirng+" : "+wrong+"\n");
			}
			//Put the message to the center Vbox
			vboxCenter.getChildren().add(message);
		}
		//Set the root center with result message
		root.setCenter(vboxCenter);
		//Make hbox for bottom
		HBox hboxBottom = new HBox();
		//Give padding and space
		hboxBottom.setPadding(new Insets(10,10,10,10));
		hboxBottom.setSpacing(100);
		//Get the number of correct answered question in string
		String correctNumString = String.valueOf(correctNum);
		//Get the number of total questions in string
		String totalNum = String.valueOf(answers.size());
		//Result text for total
		Text resultInShort = new Text(correctNumString+" / "+totalNum);
		//Add the result String to bottom
		hboxBottom.getChildren().add(resultInShort);
		//Set button to go back to main
		Button goMain = new Button("Back to Main");
		//give action to the gomain button
		goMain.setOnAction(new EventHandler<ActionEvent>() 
		{
			/**
			 * Clear all instance fields and go back to main
			 * @param e when the button is clicked
			 */
			@Override
			public void handle(ActionEvent e)
			{
				//Clear all instance fields
				store=new ArrayList<String>();
				answers=new ArrayList<Boolean>();
				quizList=new ArrayList<QuestionNode>();
				numSolved=0;
				//Go back to main
				drawMainPage();
			}
		});
		//add button to the hbox
		hboxBottom.getChildren().add(goMain);
		//Set the hbox to the root's bottom
		root.setBottom(hboxBottom);
	}
}

	


