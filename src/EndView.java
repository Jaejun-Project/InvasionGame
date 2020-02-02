import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class EndView {
	
	private Scene scene;
	private BorderPane rootPane;
	private Main main;
	private Label msg;
	private int score;
	public EndView(Main main, int score) {
		this.main=main;
		this.score = score;
		setScene();
	}
	
//set scene for gamever view
	private void setScene() {
		this.rootPane = new BorderPane();
		Pane centerPane = new Pane();
		centerPane = createCenterPane();
		centerPane.setPrefHeight(150);
		this.rootPane.setCenter(centerPane);
		Pane bottomPane = new Pane();
		bottomPane = createBottomPane();
		bottomPane.setPrefHeight(100);
		this.rootPane.setBottom(bottomPane);
		this.rootPane.setStyle("-fx-background-color:#99ccff");
		this.scene = new Scene(this.rootPane, 500,500);
		
		
		
	}
	public Scene getScene() {
		
		return this.scene;
	}
	
	// game over msg and score display on scene 
	private VBox createCenterPane() {
		VBox vbox= new VBox();
		this.msg = new Label("Game Over!");
		this.msg.setFont(Font.font ("Arial", FontWeight.BOLD, 40));
		this.msg.setTextAlignment(TextAlignment.CENTER);
		Label scoreLabel= new Label("Score: " + this.score);
		scoreLabel.setFont(Font.font ("Arial", FontWeight.BOLD, 25));
		scoreLabel.setTextAlignment(TextAlignment.CENTER);
		vbox.getChildren().addAll(this.msg,scoreLabel);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
		
	}
	// button to go back to main menu
	private HBox createBottomPane() {
		HBox hbox =new HBox();
		Button playAgain = new Button("Play Again");
		playAgain.setFont(Font.font("Arial",FontWeight.BOLD, 15));
		playAgain.setStyle("-fx-background-color:white;");
		playAgain.setPrefSize(100, 40);
		playAgain.setOnAction(e->{
			this.main.loadStartScene();
		});
		hbox.getChildren().addAll(playAgain);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
		
	}
	
	
}
