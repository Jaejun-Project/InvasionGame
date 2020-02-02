import javafx.scene.image.Image;

public class Player extends Entity{


	//private String name ;
	private int hp;
	private int score;
	private int invasionCount;
	private boolean isItemOn;
    Input input;
    private double speed;
    private double playerXMinBound;
    private double playerXMaxBound;
    private double playerYMinBound;
    private double playerYMaxBound;
    private  int bulletCharedTime; // the cannon can fire every n frames 
	int bulletcounter; // initially the cannon is charged
    private boolean fireOn = false;
	public Player(Image image,String name, double sizex, double sizey,double xCoordinate, double yCoordinate,double dx ,double dy,double dr, Input input ){
		super(image,name, 80, 80,xCoordinate,yCoordinate, dx ,dy, dr);
		this.setItemOn(false);
		this.hp=100;
		this.score=0;
		this.invasionCount =50;
		this.speed = 4;
		this.input = input;
		this.setScore(0);
		this.playerXMinBound = 0 - this.image.getBoundsInParent().getWidth() / 2.0;
		this.playerXMaxBound = 600 -this.image.getBoundsInParent().getWidth()/ 2.0;
		this.playerYMinBound = 0 -this.image.getBoundsInParent().getHeight() / 2.0;
		this.playerYMaxBound = 600 -this.image.getBoundsInParent().getHeight() / 2.0;

	}
	
	public int getInvasionCount() {
		return invasionCount;
	}

	public void setInvasionCount(int invasionCount) {
		this.invasionCount = invasionCount;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isItemOn() {
		return isItemOn;
	}
	public void setItemOn(boolean isItemOn) {
		this.isItemOn = isItemOn;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}	

    public void processInput() {
        // horizontal direction
    		this.fireOn =false;
        if( input.isMoveLeft()) {
       // 	System.out.println("left");
            dx = -speed;
        } else if( input.isMoveRight()) {
            dx = speed;
        } else {
            dx = 0d;
        }
        if(input.isFirePrimaryWeapon()) {
        	this.fireOn = true;
        }
    }
    
    private void checkBounds() {

        if( Double.compare( xCoordinate, this.playerXMinBound) < 0) {
            xCoordinate = this.playerXMinBound;
        } else if( Double.compare(xCoordinate, this.playerXMaxBound) > 0) {
        	xCoordinate = this.playerXMaxBound;
        }

    }
    public boolean isAlive() {
    		if(this.hp ==0) {
    			return false;
    		}
    		else
    			return true;
    }
    @Override
    public void move() {

        super.move();
        checkBounds();


    }
	
}
