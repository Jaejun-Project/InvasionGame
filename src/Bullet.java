import javafx.scene.image.Image;

public class Bullet extends Entity{
	
	
	 private double yCoordinate;
	 Input input;
	 int upgraded;
	public Bullet(Image image,String name,double sizex, double sizey,double xCoordinate, double yCoordinate,double dx ,double dy,double dr, int upgraded) {
		super(image,name, sizex, sizey,xCoordinate,yCoordinate, dx ,dy, dr);
		
		this.xCoordinate=xCoordinate;
		this.yCoordinate=yCoordinate;
		this.upgraded =upgraded;
	}

	//single bullet. Without item
	public void normalBullet() {
		//this.getImage().setLayoutX(this.getImage().getLayoutX()+xSpeed);
		this.getImage().setLayoutY(this.getImage().getLayoutY() + -3);
	}
	//extra bullets. with item
	public void upgradedBulletRight() {
			this.getImage().setLayoutX(this.getImage().getLayoutX()+0.5);
			this.getImage().setLayoutY(this.getImage().getLayoutY() + -1.5);
	}
	public void upgradedBulletLeft() {
		this.getImage().setLayoutX(this.getImage().getLayoutX()-1);
		this.getImage().setLayoutY(this.getImage().getLayoutY() + -3);
	}	
	
	//bullet movements
	public void moveBullet() {
		if(this.upgraded ==1) {
			upgradedBulletLeft();
		}else if(this.upgraded ==2) {
			upgradedBulletRight();
		}
		else {
			normalBullet();
		}
	}
	
	//when bullet is above the scene remove it
	public void removeBullet() {
		if(this.getImage().getLayoutY() <0) {
			setRemovable(true);
		}
	}
	 @Override
	    public void checkRemovability() {

	        if( Double.compare( this.yCoordinate, 0) < 0) {
	            setRemovable(true);
	        }
	      
	    }
}
	