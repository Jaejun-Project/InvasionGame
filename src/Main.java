import java.net.URL;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{
	private Stage primaryStage;
	private GameView game;
	private StartView start;
	private EndView end;
	private InstructionView instruction;
	
	public static void main(String[] args) {
		
		launch(args);
		}
	
	public void start(Stage primaryStage) {
	//	play_backGround();
	this.primaryStage=primaryStage;
	start= new StartView(this);
	//end = new EndView(this);
	primaryStage.setScene(start.getScene());
	loadStartScene();
	primaryStage.show();
	}
	//load start scene (first view)
	public void loadStartScene() {
		primaryStage.setScene(start.getScene());
	}
	//game scnce
	public void loadGameScene(String version) {
		game = new GameView(this,version);
		primaryStage.setScene(game.getScene());
	}
	//instruction scene
	public void loadInstructioinScene() {
		instruction = new InstructionView(this);
		primaryStage.setScene(instruction.getScene());
	}
	// end scene
	public void loadEndScene(int score) {
		end = new EndView(this,score);
		primaryStage.setScene(end.getScene());
	}

}
