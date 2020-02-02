import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class GameView {
		private BorderPane rootPane;
		private AnimationTimer timer;
		private double xMaxBoundary;
		private double yMaxBoundary;
		private AudioClip note;
		List<Item> items = new ArrayList<>();
		List<Enemy> enemies = new ArrayList<>();
		List<Bomb> bombs = new ArrayList<>();
		List<Bullet> bullets = new ArrayList<>();
		Main main;
		
		private Media media;
		private MediaPlayer mediaPlayer;
		private Scene scene; 
		private Player player;
		private int enemySpawnFrequency;
		private int bombSpawnFrequency;
		private int itemSpawnFrequency;
		private  int bombHP;
		private double enemyXSpeed;
		private double enemyYSpeed;
		private double bombXSpeed;
		private double bombYSpeed;
		private int playerHP;
		private int invasionCount;
		private int score;
		private boolean playerDamaged;
		private HBox topBox;
		private Label upgradedBullets;
		private Label hpLabel;
		private Label scoreLabel;
		private Label invasionLabel;
		private int upgradedBulletsCount;
		private boolean upgradeWeaponOn;
		private boolean aidKitOn;
		private boolean isGameOver;
		public GameView(Main main, String level) {
			
			if(level.equals("Easy")){
				this.enemySpawnFrequency = 200;
				this.bombSpawnFrequency =500;
				this.itemSpawnFrequency = 800;
				this.bombHP = 50;
				this.enemyXSpeed =1;
				this.enemyYSpeed =1;
				this.bombXSpeed =2;
				this.bombYSpeed =2;
				this.invasionCount = 5;
			}
			if(level.equals("Hard")) {
				this.enemySpawnFrequency = 50;
				this.bombSpawnFrequency =300;
				this.itemSpawnFrequency = 1300;
				this.bombHP = 150;
				this.enemyXSpeed =2;
				this.enemyYSpeed =2;
				this.bombXSpeed =4;
				this.bombYSpeed =4;
				this.invasionCount = 5;
				
			}
			this.isGameOver = false;
			this.playerDamaged =false;
			this.aidKitOn =false;
			this.upgradeWeaponOn =false;
			
			this.score =0;
			this.playerHP = 100;
			this.upgradedBulletsCount = 0 ;
			this.main = main;
			URL resource = getClass().getResource("background.mp3");
			media = new Media(resource.toString());
		    mediaPlayer = new MediaPlayer(media);
			setScene();
			
		}
		
	private void setScene() {
	
		this.rootPane= new BorderPane();
		Pane topPane = gameStatus();
		this.rootPane.setTop(topPane);
		topPane.setPrefHeight(50);
		scene = new Scene(this.rootPane, 600, 600);
		this.rootPane.setStyle("-fx-background-image: url(\"./image/background.jpg\"); " +
            "-fx-background-size: cover;"
        );
		createPlayer();
		bulletMove();
		moveAnimation();
		play_backGround() ;
	}
	public Scene getScene() {
		return scene;
	}
	
	private HBox gameStatus() {
		this.topBox = new HBox(20);
		hpLabel = new Label ("HP:" + this.playerHP);
		hpLabel.setFont(Font.font ("Arial", FontWeight.BOLD, 14));
		invasionLabel = new Label ("Invasion:" + this.invasionCount);
		invasionLabel.setFont(Font.font ("Arial", FontWeight.BOLD, 14));
		scoreLabel = new Label ("Score:" + this.score );
		scoreLabel.setFont(Font.font ("Arial", FontWeight.BOLD, 14));
		upgradedBullets = new Label ("Upgraded Bullet Left: " + this.upgradedBulletsCount);
		upgradedBullets.setFont(Font.font ("Arial", FontWeight.BOLD, 14));
		upgradedBullets.setPadding(new Insets(0, 10, 0, 0));
		upgradedBullets.setFont(Font.font ("Arial", FontWeight.BOLD, 14));
		topBox.getChildren().addAll(hpLabel, invasionLabel, scoreLabel, upgradedBullets );
		topBox.setAlignment(Pos.CENTER_RIGHT);
		//topBox.setPadding(new Insets(0,0,0,10));
		return topBox;
	}


//create player 
	private void createPlayer() {
		Input input = new Input(scene);
		input.addListeners();
		Image image = new Image ("./image/player.png");
		ImageView imgImg = new ImageView(image);
		imgImg.setFitHeight(80);
		imgImg.setFitWidth(80);
		double ymax = 600 - imgImg.getBoundsInParent().getHeight();
		this.player = new Player(image,"player",60,60,0,ymax,0, 0,0d, input );
		this.rootPane.getChildren().add(this.player.getImage());
	}

	//threads for all actions
	private void moveAnimation() {
			timer = new AnimationTimer() {
				@Override
				public void handle(long currentTime) {
					player.processInput();
					player.move();
					player.updateUI();
					spawnBombs(true);
					spawnEnemies(true);
					spawnItem(true);
					enemies.forEach(em->{
						em.move(xMaxBoundary,yMaxBoundary);
						if(em.isOnGround())
						{
							em.setRemovable(true);
							invasionCount--;
							updateStat(invasionLabel,"Invasion count", invasionCount);
							System.out.println(invasionCount);
							if(invasionCount ==0) {
								System.out.println("gameover");
								isGameOver = true;
							}
							
						}
					});
					
					if(!bullets.isEmpty()) {
					bullets.forEach(bullet -> {
						bullet.moveBullet();
						bullet.removeBullet();
					});
					}
					bombs.forEach(bomb->bomb.move(player));
					bombs.forEach(bomb->bomb.updateUI());
					items.forEach(item ->{
						if(item.isOnGround()) {
							if(item.isCheckItems() && item.isItemOpened()) {
							item.getImage().setImage(new Image("./image/aidKit.png"));
							item.setItemOpened(false);
							}else if(!item.isCheckItems() && item.isItemOpened()){
								item.getImage().setImage(new Image("./image/gun.png"));
							}
						}else {
							item.update();
						}
					});
					checkCollisions();
					if(playerDamaged) {
						playerHP -= 10;
						if(playerHP == 0) {
							System.out.println("GameOver");
							isGameOver = true;
						}
						playerDamaged = false;
					}
					if(aidKitOn) {
						playerHP += 10;
						if( playerHP >= 100) {
							playerHP = 100;
						}
						aidKitOn =false;
					}
					if(isGameOver) {
						mediaPlayer.stop();
						stop();
						main.loadEndScene(score);
						
					}
					updateStat(hpLabel,"HP",playerHP );
					updateStat(upgradedBullets,"Upgraded Bullet left", upgradedBulletsCount);
					updateStat(scoreLabel, "Score", score);
					removeEntities(bombs);
					removeEntities(items);
					removeEntities(enemies);
					removeEntities(bullets);
					
				}
		
			};
			timer.start();
	}
	
	//random bombs
private void spawnBombs(boolean random) {
		Random rnd =new Random();
        if( random && rnd.nextInt(this.bombSpawnFrequency) != 0) {
            return;
        }
        Image image = new Image("./image/bomb.png");
        Random rand =new Random();
        int xCoord = rand.nextInt(500);
        Bomb bomb = new Bomb(image, "bomb", 60, 60, xCoord ,0, 0, 0,0d, this.bombHP, this.bombXSpeed, this.bombYSpeed);
        this.rootPane.getChildren().add(bomb.getImage());
        bombs.add( bomb);
        
    }
//generate random enemy
	private void spawnEnemies( boolean random) {
		Random rnd =new Random();
		  if( random && rnd.nextInt(this.enemySpawnFrequency) != 0) {
	            return;
	        }
        Image image = new Image("./image/enemy.png");
        Random rand =new Random();
        int xCoord = rand.nextInt(500);
        Enemy em = new Enemy(image, "emeny", 60, 60, xCoord ,0, 0, 0,0d,this.enemyXSpeed,this.enemyYSpeed);
         xMaxBoundary =  rootPane.getWidth()- em.getImage().getLayoutBounds().getWidth();
		 yMaxBoundary = rootPane.getHeight() -em.getImage().getLayoutBounds().getHeight();
        this.rootPane.getChildren().add(em.getImage());
        // manage sprite
        enemies.add( em);
    }
	//generate items 
	private void spawnItem(boolean random) {
		Random rnd =new Random();
		  if( random && rnd.nextInt(this.itemSpawnFrequency) != 0) {
	            return;
	        }
        Image image = new Image("./image/item.png");
        Random rand =new Random();
        int xCoord = rand.nextInt(500);
        Random randItem = new Random();
        boolean rndItem = randItem.nextBoolean();
        Item item = new Item(image, "item", 60, 60, xCoord ,0,0,0,0d, rndItem);
        this.rootPane.getChildren().add(item.getImage());
        items.add(item);
    }
	
	//when player press spacebar shoot bullet
	private void bulletMove() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			  @Override
			  public void handle(KeyEvent event){
			    if (event.getCode() == KeyCode.SPACE  ) {
			    	if(upgradedBulletsCount >0) {
			    	 	upgradedBulletsCount--;
			    	}
			    	if(upgradedBulletsCount ==0) {
			    	 upgradeWeaponOn =false;
			    	}
			    	spawnBullet();
			    	play_shooting();
			  }}
			  });
	}
	
	//generate bullet whenever player try to shoot 
	private void spawnBullet() {
			    	Image image = new Image("./image/bullet.png");
				double bulletY = player.getImage().getLayoutY();
				double bulletX = player.getImage().getLayoutX()+50;
			
				if(this.upgradeWeaponOn) {
					Bullet bullet = new Bullet(image, "bullet", 5,5,bulletX, bulletY, 0, 0,0d,1);
					bullets.add(bullet); 		
					Bullet b1 = new Bullet(image, "bullet", 5,5,bulletX, bulletY, 0, 0,0d,2);
					Bullet b2 = new Bullet(image, "bullet", 5,5,bulletX, bulletY, 0, 0,0d,3);
					bullets.add(b1);
					bullets.add(b1);
					bullets.add(b2);
					this.rootPane.getChildren().addAll(bullet.getImage(), b1.getImage(),b2.getImage());
				}else {
					Bullet normalBullet = new Bullet(image, "bullet", 5,5,bulletX, bulletY, 0, 0,0d,3);
					bullets.add(normalBullet);
					this.rootPane.getChildren().addAll(normalBullet.getImage());
				}
	}
	
	
	private void updateStat(Label label, String stat, int count) {
		StringProperty statLabel = new SimpleStringProperty();
		String tempString = stat + ": " +count;
		statLabel.setValue(tempString);
		label.textProperty().bind(statLabel);
	}
	private void checkCollisions() {
            for( Item item: items) {
//            	if(item.isWeapon()) {
//            		this.upgradeWeaponOn = true;
//            	}else {
//            		this.aidKitOn = true;
//            	}
            if(player.collidesWith(item)) {
            		if(item.isCheckItems()) {
            			System.out.print("true)");
            			this.aidKitOn = true;
            		}else if(!item.isCheckItems()){
            			this.upgradeWeaponOn = true;
            			this.upgradedBulletsCount += 20;
            		}
            		item.setRemovable(true);
            }
                
            }
            for( Bullet b : bullets) {
            	for(Enemy e : enemies) {
            		if(b.collidesWith(e)) {
            			e.setRemovable(true);
            			b.setRemovable(true);
            			this.score +=50;
            		}
            	 }
            }
            for(Bomb bomb : bombs) {
            		if(player.collidesWith(bomb)) {
            			bomb.setRemovable(true);
            			player.setHp(player.getHp()-10);
            		}
            }
            for(Bomb bomb : bombs) {
            	for(Bullet b : bullets) {
            		if(b.collidesWith(bomb)) {
            			b.setRemovable(true);
            			System.out.println(bomb.getHp());
            			if(bomb.getHp()==0) {
            				this.score += 100;
            			 bomb.setRemovable(true);
            			}else {
            				bomb.setHp(bomb.getHp()-10);
            			}
            		}
            	}
            }
            for(Bomb bomb : bombs) {
            		if(player.collidesWith(bomb)) {
            			bomb.setRemovable(true);
            			this.playerDamaged = true;
            		}
            }
            
        }
	//remove entities from screen
	   private void removeEntities(  List<? extends Entity> entitly) {
	        Iterator<? extends Entity> iter = entitly.iterator();
	        while( iter.hasNext()) {
	        	Entity et = iter.next();

	            if( et.isRemovable()) {
	                // remove from layer
	            		this.rootPane.getChildren().remove(et.getImage());
	                // remove from list
	                iter.remove();
	            }
	        }
	    }
	   //sound for player shoots
	   private void play_shooting() {
		  note = new AudioClip(this.getClass().getResource("shooting1.wav").toString());
		   note.play();
	   }
	   private void play_backGround() {
		  //  mediaPlayer.setAutoPlay(!this.isGameOver);
		   mediaPlayer.play();
		    mediaPlayer.setOnEndOfMedia(new Runnable() {    
		        public void run() {
		        	mediaPlayer.seek(Duration.ZERO); 
		    
		       }
		         });  
	   }
	   

}
