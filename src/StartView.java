import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class StartView {
	
	private Main main;
	private BorderPane root;
	private Scene scene;
	private ComboBox<String> difficultySelector;
	
	public StartView(Main main){
		this.main = main;
		setScene();
	}
	
	private void setScene() {
		this.root= new BorderPane();
		Pane topPane = createTopPane();
		topPane.setPrefHeight(180);
		this.root.setTop(topPane);
		
		Pane centerPane = createCenterPane();
		centerPane.setPrefHeight(100);
		
		this.root.setCenter(centerPane);
		this.root.setStyle("-fx-background-color: #99ccff;");
		scene = new Scene(this.root,500,500);
	}
	public Scene getScene() {
		return scene;
	}
	
	private HBox createTopPane() {
		
		HBox hbox= new HBox();
		Label title = new Label("The Invasion");
		title.setFont(Font.font ("Arial", FontWeight.BOLD, 35));
		//title.setPadding(new Insets(0,0,0,10));
		title.setTextAlignment(TextAlignment.CENTER);
		hbox.getChildren().addAll(title);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
		
	}
	private VBox createCenterPane() {
		
		HBox hbox = new HBox();
		difficultySelector = new ComboBox<String>();
		Label title = new Label("Choose Version of Difficulty ");
		title.setFont(Font.font ("Arial", FontWeight.BOLD, 17));
		difficultySelector.getItems().addAll("Easy", "Hard");
		difficultySelector.setValue("Easy");
		title.setTextAlignment(TextAlignment.CENTER);
		hbox.getChildren().addAll(title, difficultySelector);
		hbox.setAlignment(Pos.TOP_CENTER);
		
		VBox vbox = new VBox(30);
		Button play = new Button("Play");
		play.setPrefSize(100, 40);
		play.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		play.setStyle("-fx-background-color: white;");
		
		play.setOnAction(e->{
			String choice = difficultySelector.getSelectionModel().getSelectedItem();
			this.main.loadGameScene(choice);
		});
		
		Button instruction = new Button("Instruction");
		instruction.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		instruction.setPrefSize(100, 40);
		instruction.setStyle("-fx-background-color: white;");
		
		instruction.setOnAction(e->{
			this.main.loadInstructioinScene();
		});
		Button settings = new Button("Settings");
		settings.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		settings.setPrefSize(100, 40);
		settings.setStyle("-fx-background-color: white;");
		
		vbox.getChildren().addAll(hbox, play, instruction, settings);
		vbox.setAlignment(Pos.TOP_CENTER);
	
		
		
		return vbox;
		
		
	}

	
	
	
	

}
