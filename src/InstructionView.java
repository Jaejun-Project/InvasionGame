
import javafx.geometry.Insets;
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

public class InstructionView {
	private Scene scene;
	private BorderPane rootPane;
	private Main main;
	//private Label msg;
	public InstructionView(Main main) {
		this.main=main;
		setScene();
	}
	private void setScene() {
		this.rootPane = new BorderPane();
		Pane topPane = new Pane();
		topPane =createTopPane();
		topPane.setPrefHeight(100);
		this.rootPane.setTop(topPane);
		Pane centerPane = new Pane();
		centerPane = createCenterPane();
		//centerPane.setPrefHeight(3000);
		this.rootPane.setCenter(centerPane);
		Pane bottomPane = new Pane();
		bottomPane = createBottomPane();
		bottomPane.setPrefHeight(100);
		this.rootPane.setBottom(bottomPane);
		this.rootPane.setStyle("-fx-background-color:#99ccff");
		//MovingCircle circle = new MovingCircle( 40, 40, 10, Color.BLUE , 3, 3);
		//this.rootPane.getChildren().add(circle);
		this.scene = new Scene(this.rootPane, 600,600);
	}
		public Scene getScene() {
				return this.scene;
			}
		private HBox createTopPane() {
			HBox hbox= new HBox();
			Label title = new Label("How to Play");
			title.setFont(Font.font ("Arial", FontWeight.BOLD, 35));
			title.setTextAlignment(TextAlignment.CENTER);
			hbox.getChildren().addAll(title);
			hbox.setAlignment(Pos.CENTER);
			return hbox;
		}
		private VBox createCenterPane() {
			VBox vbox = new VBox();
			VBox box = new VBox();
			Label inst = new Label();
			inst.setText("Player is going to protect his territory from enemies’ invasions.");
			inst.setFont(Font.font ("Arial", FontWeight.BOLD, 17));
			Label inst1 = new Label("There will be two kinds of enemies dropping from the sky.");
			inst1.setFont(Font.font ("Arial", FontWeight.BOLD, 17));
			Label inst2 = new Label ("Player need to kill enemies.");
			inst2.setFont(Font.font ("Arial", FontWeight.BOLD, 17));
			box.getChildren().addAll(inst, inst1, inst2);
			box.setAlignment(Pos.TOP_CENTER);
			Label  key1 = new Label("Press <- key to move left.");
			key1.setFont(Font.font ("Arial", FontWeight.BOLD, 17));
			key1.setPadding(new Insets(40,0,0,0));
			Label key2 = new Label("Press ->  key to move right");
			key2.setFont(Font.font ("Arial", FontWeight.BOLD, 17));
			key2.setPadding(new Insets(30,0,0,0));
			Label key3 = new Label ("Press Spacebar to shoot enemies");
			key3.setFont(Font.font ("Arial", FontWeight.BOLD, 17));
			key3.setPadding(new Insets(30,0,0,0));
			vbox.getChildren().addAll(box, key1,key2,key3);
			vbox.setAlignment(Pos.CENTER);

			return vbox;
			
		}
		private HBox createBottomPane() {
			HBox hbox =new HBox();
			Button mainMenu = new Button("Go back to Main Menu");
			mainMenu.setFont(Font.font("Arial",FontWeight.BOLD, 15));
			mainMenu.setStyle("-fx-background-color:white;");
			mainMenu.setPrefSize(200, 40);
			mainMenu.setOnAction(e->{
				this.main.loadStartScene();
			});
			hbox.getChildren().addAll(mainMenu);
			hbox.setAlignment(Pos.CENTER);
			return hbox;
			
		}
		
}
