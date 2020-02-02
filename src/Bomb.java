import javafx.scene.image.Image;

public class Bomb extends  Entity{
	int hp;
	private double xSpeed;
	 private double ySpeed;
	// private double xCoordinate;
	// private double yCoordinate;
	 double turnRate;
	public Bomb(Image image,String name, double sizex, double sizey,double xCoordinate, double yCoordinate,double dx ,double dy,double dr, int hp, double xSpeed, double ySpeed) {
		super(image,name, sizex, sizey,xCoordinate,yCoordinate, dx , dy, dr);
		this.xSpeed=xSpeed;
		this.ySpeed=ySpeed;
		this.hp =hp;
		this.turnRate = 0.6;
	}
	
	public int getHp() {
		return hp;
	}
// bomb hp 
	public void setHp(int hp) {
		this.hp = hp;
	}
	//update image location on scene
	public void update() {
		this.getImage().relocate(this.xCoordinate, this.yCoordinate);
		this.getImage().setRotate(this.r);
	}
	
	
	
	//chasing target (player)
	public void move(Player player) {
		
		  //get distance between follower and target
		  double distanceX = player.xCoordinate - xCoordinate;
		  double distanceY = player.yCoordinate - yCoordinate;

		  //get total distance as one number
		  double distanceTotal = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
		  
		  //calculate how much to move
		  double moveDistanceX = this.turnRate * distanceX / distanceTotal;
		  double moveDistanceY = this.turnRate * distanceY / distanceTotal;

		  //increase current speed
		  dx += moveDistanceX;
		  dy += moveDistanceY;

		  //get total move distance
		  double totalmove = Math.sqrt(dx * dx + dy * dy);
		  
		  //apply easing
		  dx = this.xSpeed * dx/totalmove;
		  dy = this.ySpeed * dy/totalmove;
		  
		 
		 
		 //move follower
		 xCoordinate += dx;
		 yCoordinate+= dy;

		 //rotate follower toward target
		 double angle = Math.atan2(dy, dx);
		 double degrees = Math.toDegrees(angle) - 90;
		 
		 r = degrees;
	}
    public void checkRemovability() {

        if( Double.compare( this.yCoordinate, 600) < 0) {
            setRemovable(true);
        }
        
    }
}
