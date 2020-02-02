import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Item extends Entity{
	private boolean onGround;
	private double xSpeed;
	 private double ySpeed;
	 private double xCoordinate;
	 private double yCoordinate;
	 private boolean checkItem;
	private boolean isItemOpened;
	public Item(Image image,String name, double sizex, double sizey,double xCoordinate, double yCoordinate, double dx, double dy, double dr, boolean checkItem) {
		super(image,name, sizex, sizey,xCoordinate,yCoordinate, dx ,dy, dr);
		this.setImageCoordinate(xCoordinate, yCoordinate);
		this.onGround = false;
		this.xSpeed=2;
		this.ySpeed=2;
		this.xCoordinate=xCoordinate;
		this.yCoordinate=yCoordinate;
		this.checkItem =checkItem;
		this.isItemOpened =true;
	}

	
//check whether item is on the ground.	
	public boolean isItemOpened() {
		return isItemOpened;
	}
	public void setItemOpened(boolean isItemOpened) {
		this.isItemOpened = isItemOpened;
	}

	public boolean isCheckItems() {
		return checkItem;
	}
	public void setCheckItems(boolean checkItems) {
		this.checkItem = checkItems;
	}
	
	//item movement
	public void update() {
		this.image.setLayoutY(getImage().getLayoutY() + ySpeed);
			
		}
	public void move(double xMaxBoundary, double yMaxBoundary) {

//		double xMin = xCoordinate-30;
//		double xMax = xCoordinate +50;
//		if(xMin < 0) {
//			xMin = 0;
//		}
//		if(xMax > xMaxBoundary) {
//			xMax = (int) xMaxBoundary;
//		}
//
//		if(getImage().getLayoutX() <xMin || getImage().getLayoutX() > xMax ) {
//			changeXDirection();
//		}
//		isOnGround();
//		update();
//		
		
 }
}
