package game.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import game.engine.weapons.WeaponRegistry;
import game.engine.weapons.factory.FactoryResponse;
import game.engine.weapons.factory.WeaponFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class view {
    private Battle b;
    Stage primaryStage;
    WeaponFactory weaponFactory;
    Label scoreLabel;
    Label turnLabel;
    Label phaseLabel;
    Label resourcesLabel;
    VBox lanesGrid;
    Button buy;
    Button buy1;
    Button buy2;
    Button buy3;
    Scene ss;
    
    static Map<Integer, VBox> laneMap;

    public view(Stage primaryStage, Scene s) throws IOException {
    	this.ss=s;
        this.primaryStage = primaryStage;
        this.weaponFactory = new WeaponFactory();
        this.laneMap = new HashMap<>();
    }

    public Text title() {
        Text name = new Text("Attack on Titan: Utopia");
        name.setStyle("-fx-font-family: 'Pixelify Sans'; -fx-font-size: 50px;");
        return name;
    }

    public Button startPage() {
        Button start = new Button("Start");
        start.setMaxSize(250, 250);
        start.setStyle("-fx-font-family: 'Pixelify Sans';-fx-font-size: 20px;");
        start.setTextFill(Color.FORESTGREEN);
        return start;
    }

    public Button easypage() {
        Button ez = new Button("Easy");
        ez.setTextFill(Color.BROWN);
        ez.setMaxSize(250, 250);
        ez.setStyle("-fx-font-family: 'Pixelify Sans';-fx-font-size: 20px;");
        return ez;
    }

    public Button hardpage() {
        Button hrd = new Button("Hard");
        hrd.setTextFill(Color.BROWN);
        hrd.setMaxSize(250, 250);
        hrd.setStyle("-fx-font-family: 'Pixelify Sans';-fx-font-size: 20px;");
        return hrd;
    }

    public Button gameInstructions() {
        Button inst = new Button("Game Instructions");
        inst.setMaxSize(250, 250);
        inst.setStyle("-fx-font-family: 'Pixelify Sans';-fx-font-size: 20px;");
        inst.setTextFill(Color.BROWN);
        return inst;
    }

    public Scene GettingValues(Battle battle, int numberOfLanes) {
        this.b = battle;
        scoreLabel = new Label("Current score: " + battle.getScore());
        turnLabel = new Label("Current Turn: " + battle.getNumberOfTurns());
        phaseLabel = new Label("Current Phase: " + battle.getBattlePhase());
        resourcesLabel = new Label("Current Resources: " + battle.getResourcesGathered());
        scoreLabel.setStyle("-fx-font-family: 'Pixelify Sans';-fx-text-fill: #294023;-fx-font-size: 14px;");
        turnLabel.setStyle("-fx-font-family: 'Pixelify Sans';-fx-text-fill: #294023;-fx-font-size: 14px;");
        phaseLabel.setStyle("-fx-font-family: 'Pixelify Sans';-fx-text-fill: #294023;-fx-font-size: 14px;");
        resourcesLabel.setStyle("-fx-font-family: 'Pixelify Sans';-fx-text-fill:#294023;-fx-font-size: 14px;");

        VBox root = new VBox();
        VBox buttons = new VBox();
        Button weapon = new Button("Weapon Shop");
        Button passTurnButton = new Button("Pass Turn");
        weapon.setTextFill(Color.BROWN);
        weapon.setStyle("-fx-font-family: 'Pixelify Sans';");
        buttons.getChildren().addAll(weapon, passTurnButton);
        buttons.setAlignment(Pos.TOP_RIGHT);
        root.getChildren().addAll(scoreLabel, turnLabel, phaseLabel, resourcesLabel, buttons);

        lanesGrid = createLanesGrid(battle, numberOfLanes);
        root.getChildren().add(lanesGrid);

        weapon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showWeaponShopPopup(battle, numberOfLanes);
            }
        });

        passTurnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passTurn1(battle);
            }
        });

        Scene scene = new Scene(root, 1000, 1000);
        return scene;
    }

    private VBox createLanesGrid(Battle battle, int numberOfLanes) {
        VBox lanesGrid = new VBox(); 
        lanesGrid.setSpacing(20); 

        for (int i = 0; i < numberOfLanes; i++) {
            Label laneLabel = new Label("Lane " + (i + 1));
            laneLabel.setStyle("-fx-font-family: 'Pixelify Sans';-fx-text-fill: #294023;-fx-font-size: 14px;");

            Rectangle wall = new Rectangle(100, 80); 
            wall.setFill(Color.DARKGRAY); 
          

          
            Lane lane = battle.getOriginalLanes().get(i);
            Label wallHealthLabel = new Label("Wall Health: " + lane.getLaneWall().getCurrentHealth());
            wallHealthLabel.setStyle("-fx-font-family: 'Pixelify Sans';-fx-text-fill: #294023;-fx-font-size: 14px;");
            wallHealthLabel.setId("wallHealthLabel" + (i + 1)); 

            
            Label dangerLevelLabel = new Label("Danger Level: " + lane.getDangerLevel());
            dangerLevelLabel.setStyle("-fx-font-family: 'Pixelify Sans';-fx-text-fill: #ff0000;-fx-font-size: 14px;");
            dangerLevelLabel.setId("dangerLevelLabel" + (i + 1)); 

            VBox laneBox = new VBox();
            laneBox.setStyle("-fx-border-color: #294023; -fx-padding: 10;");
            laneBox.getChildren().addAll(laneLabel, wall, wallHealthLabel, dangerLevelLabel); 
            laneMap.put(i + 1, laneBox); 
            lanesGrid.getChildren().add(laneBox); 
        }

        return lanesGrid;
    }


    private void updateLanes(Battle battle) {
        int remainingLanes = 0; 
        Iterator<Map.Entry<Integer, VBox>> iterator = laneMap.entrySet().iterator();
        
        while (iterator.hasNext()) {
            Map.Entry<Integer, VBox> entry = iterator.next();
            VBox laneBox = entry.getValue();
            Lane lane = battle.getOriginalLanes().get(entry.getKey() - 1); 
            
           
            if (lane.getLaneWall().getCurrentHealth() <= 0) {
                lanesGrid.getChildren().remove(laneBox);
                iterator.remove(); 
                continue;
            }
            
            remainingLanes++; 

            
            laneBox.getChildren().removeIf(node ->
                    (node instanceof Text && ((Text) node).getText().startsWith("Titan:")) ||
                    (node instanceof Label && (((Label) node).getText().startsWith("Wall Health:") || ((Label) node).getText().startsWith("Danger Level:")))
            );

            
            Label wallHealthLabel = new Label("Wall Health: " + lane.getLaneWall().getCurrentHealth());
            wallHealthLabel.setStyle("-fx-font-family: 'Pixelify Sans';-fx-text-fill: #294023;-fx-font-size: 14px;");
            wallHealthLabel.setId("wallHealthLabel" + entry.getKey()); 
            laneBox.getChildren().add(wallHealthLabel);

            
            Label dangerLevelLabel = new Label("Danger Level: " + lane.getDangerLevel());
            dangerLevelLabel.setStyle("-fx-font-family: 'Pixelify Sans';-fx-text-fill: #ff0000;-fx-font-size: 14px;");
            dangerLevelLabel.setId("dangerLevelLabel" + entry.getKey()); 
            laneBox.getChildren().add(dangerLevelLabel);

            
            HBox titansBox = new HBox();
            titansBox.setSpacing(10);
            titansBox.setAlignment(Pos.CENTER_RIGHT);

           
            Rectangle wall = (Rectangle) laneBox.getChildren().get(1);
            double wallPosition = laneBox.getWidth() - wall.getWidth();

        
            for (Titan titan : lane.getTitans()) {
                
                if (titan.getCurrentHealth() > 0) {
                    
                    StackPane titanPane = new StackPane();
                    
                   
                    Shape titanShape;
                    
                    
                    if (titan instanceof AbnormalTitan) {
                        titanShape = new Circle(20); 
                        titanShape.setFill(Color.RED);
                    } else if (titan instanceof ArmoredTitan) {
                        titanShape = new Circle(40);
                        titanShape.setFill(Color.BLUE);
                    } else if (titan instanceof ColossalTitan) {
                        titanShape = new Circle(30); 
                        titanShape.setFill(Color.PINK);
                    } else if (titan instanceof PureTitan) {
                        titanShape = new Circle(60); 
                        titanShape.setFill(Color.GREEN);
                    } else {
                        
                        titanShape = new Rectangle(30, 30); 
                        titanShape.setFill(Color.BLACK);
                    }
                    
                    
                    Text titanInfo = new Text("Titan: " + titan.getClass().getSimpleName() + " (Health: " + titan.getCurrentHealth() + ")");
                    titanInfo.setStyle("-fx-font-family: 'Pixelify Sans';-fx-text-fill: #294023;-fx-font-size: 14px;");
                    
                    
                    titanPane.getChildren().addAll(titanShape, titanInfo);
                    
                    
                    titansBox.getChildren().add(titanPane);
                }
            }


            

           
            laneBox.getChildren().removeIf(node -> node instanceof HBox && ((HBox) node).getAlignment() == Pos.CENTER_RIGHT);

           
            laneBox.getChildren().add(titansBox);
        }

      
        if (remainingLanes == 0) {
            gameOver();
        }
    }




    public static void addWeaponToLane(int laneNumber, WeaponRegistry weaponRegistry) {
      
        VBox laneBox = laneMap.get(laneNumber);
        if (laneBox != null) {
            Text weaponInfo = new Text("Weapon: " + weaponRegistry.getName() + " (Damage: " + weaponRegistry.getDamage() + ")");
            weaponInfo.setStyle("-fx-font-family: 'Pixelify Sans';-fx-text-fill: #294023;-fx-font-size: 14px;");
            laneBox.getChildren().add(weaponInfo);
        }
    }
    public static void showErrorMessage(String message) {
       
        Stage errorStage = new Stage();
        errorStage.initModality(Modality.APPLICATION_MODAL);
        errorStage.setTitle("Error");

        VBox errorContent = new VBox();
        errorContent.setSpacing(10);
        errorContent.setAlignment(Pos.CENTER);
        errorContent.setStyle("-fx-padding: 20; -fx-background-color: #ffffff; -fx-font-family: 'Pixelify Sans'; -fx-font-size: 14px; -fx-text-fill: #294023;");

        Text errorMessage = new Text(message);
        errorMessage.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorStage.close();
            }
        });

        errorContent.getChildren().addAll(errorMessage, closeButton);

        Scene errorScene = new Scene(errorContent, 400, 400);
        errorStage.setScene(errorScene);
        errorStage.showAndWait();
    }

    public void showWeaponShopPopup(Battle battle, int numberOfLanes) {
    	try {

    	Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Weapon Shop");

        VBox popupContent = new VBox();
        popupContent.setSpacing(10);
        popupContent.setAlignment(Pos.CENTER);
        popupContent.setStyle("-fx-padding: 20; -fx-background-color: #ffffff; -fx-font-family: 'Pixelify Sans'; -fx-font-size: 14px; -fx-text-fill: #294023;");

        Text title = new Text("Available Weapons:");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        popupContent.getChildren().add(title);
        Map<Integer, Integer> selectedWeapons = new HashMap<>();

        for (WeaponRegistry weaponRegistry : weaponFactory.getWeaponShop().values()) {
            VBox weaponBox = new VBox();
            weaponBox.setAlignment(Pos.CENTER_LEFT);
            weaponBox.setSpacing(5);
            weaponBox.setStyle("-fx-border-color: #294023; -fx-border-width: 1px; -fx-padding: 10;");

            Text weaponName = new Text("Name: " + weaponRegistry.getName());
            Text weaponPrice = new Text("Price: " + weaponRegistry.getPrice());
            Text weaponDamage = new Text("Damage: " + weaponRegistry.getDamage());
            Text weaponRange = new Text("Range: " + weaponRegistry.getMinRange() + " - " + weaponRegistry.getMaxRange());

            ComboBox<String> laneComboBox = new ComboBox<>();
            for (int i = 1; i <= numberOfLanes; i++) {
                laneComboBox.getItems().add("Lane " + i);
            }
            laneComboBox.setValue("Lane 1");

            Button buyButton = new Button("Buy");

            buyButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        
                        String selectedLane = laneComboBox.getValue();
                        int laneNumber = Integer.parseInt(selectedLane.split(" ")[1]);

                    
                        battle.purchaseWeapon(weaponRegistry.getCode(), battle.getOriginalLanes().get(laneNumber - 1));

                       
                        selectedWeapons.put(weaponRegistry.getCode(), laneNumber);

                       
                        Updating(battle.getScore(), battle.getResourcesGathered(), battle.getNumberOfTurns());
                        view.addWeaponToLane(laneNumber, weaponRegistry); 
                        updateLanes(battle);
                    } catch (InsufficientResourcesException e) {
                        view.showErrorMessage("Insufficient Resources to buy this weapon");
                    } catch (InvalidLaneException e) {
                    	 view.showErrorMessage("no more lane");						
                    	 e.printStackTrace();
					}
                }
            });

            weaponBox.getChildren().addAll(weaponName, weaponPrice, weaponDamage, weaponRange, laneComboBox, buyButton);
            popupContent.getChildren().add(weaponBox);
        }

        Scene popupScene = new Scene(popupContent, 750, 750);
        popupStage.setScene(popupScene);
        popupStage.show();
    	 } catch (Exception e) {
    	       
    	        view.showErrorMessage("An error occurred while opening the weapon shop: " + e.getMessage());
    	    }
    	}
    


    public void Updating(int score, int resources, int turn) {
        scoreLabel.setText("Current score: " + score);
        resourcesLabel.setText("Current Resources: " + resources);
        turnLabel.setText("Current Turn: " + turn);
    }

    public void Updating2(Battle battle) {
        phaseLabel.setText("Current Phase: " + battle.getBattlePhase());
    }
 
    private void passTurn1(Battle battle) {
        try {
            battle.passTurn();
            updateLanes(battle);
            Updating(battle.getScore(), battle.getResourcesGathered(), battle.getNumberOfTurns()); 
            Updating2(battle); 
            
           
            if (battle.isGameOver()) {
                gameOver(); 
            }
        } catch (Exception e) {
            
            view.showErrorMessage("An error occurred during the turn: " + e.getMessage());
        }
    }

    




    
    public void gameOver() {
        Stage gameOverStage = new Stage();
        gameOverStage.initModality(Modality.APPLICATION_MODAL);
        gameOverStage.setTitle("Game Over");

        VBox gameOverContent = new VBox();
        gameOverContent.setSpacing(10);
        gameOverContent.setAlignment(Pos.CENTER);
        gameOverContent.setStyle("-fx-padding: 20; -fx-background-color: #d95c43; -fx-font-size: 18px; -fx-text-fill: #294023;");

        Text gameOverMessage = new Text("Game Over! All walls have been destroyed.");
        gameOverMessage.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");


        Text text = new Text("Final Score: " + b.getScore());
        text.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: black;");

        


        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            gameOverStage.close();
            primaryStage.setScene(ss); 
        });

        Button startButton = new Button("Start Window");
        startButton.setOnAction(event -> {
            try {
                VBox root = new VBox();
                root.setSpacing(20);
                Button B = easypage();
                Button lo = hardpage();
                root.getChildren().addAll(B, lo);
                root.setAlignment(Pos.CENTER);

                StackPane root2 = new StackPane();
                root2.getChildren().addAll(root);
                StackPane.setAlignment(root, Pos.TOP_CENTER);
                ss = new Scene(root2, 600, 600);
                primaryStage.setScene(ss);

                B.setOnAction(e -> {
                    try {
                        b = new Battle(1, 0, 25, 3, 250);
                        Scene valuesScene = GettingValues(b, 3);
                        primaryStage.setScene(valuesScene);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                lo.setOnAction(e -> {
                    try {
                        b = new Battle(1, 0, 25, 5, 125);
                        Scene valuesScene = GettingValues(b, 5);
                        primaryStage.setScene(valuesScene);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Updating(b.getScore(), b.getResourcesGathered(), b.getNumberOfTurns()); 
        gameOverContent.getChildren().addAll(text,gameOverMessage, closeButton, startButton);

        Scene gameOverScene = new Scene(gameOverContent, 400, 250);
        gameOverStage.setScene(gameOverScene);
        gameOverStage.showAndWait();
    }
}
