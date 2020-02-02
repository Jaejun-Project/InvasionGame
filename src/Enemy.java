import javafx.scene.image.Image;

public class Enemy extends Entity{
	private double xSpeed;
	 private double ySpeed;
	 private double xCoordinate;
	 private double yCoordinate;
	// private boolean collision;
	public Enemy(Image image,String name,double sizex, double sizey,double xCoordinate, double yCoordinate,double dx ,double dy,double dr, double xSpeed, double ySpeed) {
		super(image,name, sizex, sizey,xCoordinate,yCoordinate, dx ,dy,dr );

	//	this.setImageCoordinate(xCoordinate, yCoordinate);
		this.xSpeed=xSpeed;
		this.ySpeed=ySpeed;
		this.xCoordinate=xCoordinate;
		this.yCoordinate=yCoordinate;
		//this.collision = false;
	}

	public void changeXDirection() {
		xSpeed*=-1;
	}
	
// change the location of img
	public void update() {
		this.getImage().setLayoutX(this.getImage().getLayoutX()+xSpeed);
		this.getImage().setLayoutY(this.getImage().getLayoutY() + ySpeed);
	}
//movement of img
	public void move(double xMaxBoundary, double yMaxBoundary) {

		double xMin = xCoordinate-30;
		double xMax = xCoordinate +50;
			if(xMin < 0) {
				xMin = 0;
			}
			if(xMax > xMaxBoundary) {
				xMax = (int) xMaxBoundary;
			}

			if(getImage().getLayoutX() <xMin || getImage().getLayoutX() > xMax ) {
				changeXDirection();
			}
			update();

	}
	
	 @Override
    public void checkRemovability() {

        if( Double.compare( this.yCoordinate, 600) < 0) {
            setRemovable(true);
        }
        
    }

}
