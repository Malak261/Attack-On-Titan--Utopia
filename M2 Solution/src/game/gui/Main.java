package game.gui;
import java.io.IOException;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.weapons.factory.FactoryResponse;
import game.engine.weapons.factory.WeaponFactory;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.IOException;
import java.util.Map;


public class Main extends Application {
	private Battle b; 
	private view v;
	private Scene s ;
	private Button easyB;
	private Button hardB;
	Stage primaryStage;
    WeaponFactory weaponFactory;
    Label scoreLabel;
    Label turnLabel;
    Label phaseLabel;
    Label resourcesLabel;
    GridPane lanesGrid; 
    Button Weapon1;
    Button Weapon2;
    Button Weapon3;
    Button Weapon4;
    Map<Integer, VBox> laneMap;

	@Override
public void start(Stage primaryStage) throws Exception {
		
		
		v = new view(primaryStage,s);
		Button start = v.startPage();
		Button instruc= v.gameInstructions();
		VBox first_root=new VBox();
		Text title = v.title();	
		Font.loadFont(getClass().getResourceAsStream("C:/Users/malak/Downloads/Pixelify_Sans.ttf"), 50);
		first_root.setSpacing(20);
		first_root.getChildren().addAll(start,instruc);
		first_root.setAlignment(Pos.BOTTOM_CENTER);
		Image img=new Image("gamee.png");
		ImageView view=new ImageView(img);
		StackPane root = new StackPane();
        root.getChildren().addAll(title, first_root);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
		s = new Scene(root,600,600);
		 primaryStage.setScene(s);
	     primaryStage.show();
		//don't forget handling el game instructions
	     instruc.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                VBox root = new VBox();
	                VBox backb = new VBox();
	                Button back = new Button("Back");
	                back.setTextFill(Color.BROWN);
	                back.setMaxSize(100, 100);
	                backb.getChildren().addAll(back);
	                backb.setStyle("-fx-font-family: 'Pixelify Sans';-fx-font-size: 14px;");
	                backb.setAlignment(Pos.TOP_RIGHT);
	                root.setSpacing(20);
	                root.setAlignment(Pos.CENTER);
	                Text instructionsText = new Text("kill the titans, don't die 3");
	                Text instructions = new Text("The weapons are in the weapon shop, with their Name, Price, Damage Point, and Type.");
	                Text inst = new Text("Don't forget to select your desired Weapon to start attacking;)");
	                root.getChildren().addAll(backb, instructions, inst, instructionsText);
	                root.setStyle("-fx-font-family: 'Pixelify Sans';-fx-font-size: 14px;");

	                Scene instructionsScene = new Scene(root, 600, 600);
	                root.setStyle("-fx-background-color: #b8783d;-fx-font-family: 'Pixelify Sans';-fx-font-size: 14px;");
	     
	                primaryStage.setScene(instructionsScene);

	                
	                back.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent event) {
	                        primaryStage.setScene(s);
	                    }
	                });
	            }
	        });
	      
	  start.setOnAction(new EventHandler<ActionEvent>() {
				
		@Override
		public void handle(ActionEvent event) {
			VBox root = new VBox();
			//StackPane root= new StackPane()
			root.setSpacing(20);
			Image wall=new Image("wall.jpg");
			ImageView view2=new ImageView(wall);
			easyB = v.easypage();
			hardB  = v.hardpage();
			root.getChildren().addAll(easyB, hardB);

		    root.setAlignment(Pos.CENTER);
		    StackPane root2 = new StackPane();
		    root2.getChildren().addAll( root);

		    StackPane.setAlignment(root, Pos.TOP_CENTER);

		    s = new Scene(root2, 600, 600);

		    primaryStage.setScene(s);
			 easyB.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                
                 public void handle(ActionEvent event) {
                     try {
 						b= new Battle(1,0 ,25 , 3, 250);
 						Scene valuesScene = v.GettingValues(b,3);
                         primaryStage.setScene(valuesScene);
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
                      
                  }
 			 });
                  hardB.setOnAction(new EventHandler<ActionEvent>() {
                 	 @Override
                 	 public void handle(ActionEvent event) {
                          try { 
      						b= new Battle(1,0 ,25 , 5 , 125);
      						Scene valuesScene = v.GettingValues(b,5);
                             primaryStage.setScene(valuesScene);
      					} catch (IOException e) {
      						e.printStackTrace();
      					}
                           
                       }
             });
			
			
	}

	  });
	  
	  
	}

		
		
	
			
	public static void main(String[] args) {
		launch(args);
	}
	

	

}