import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Entity {
	ImageView image;
	 double xCoordinate;
	 double yCoordinate;
	 private double xImageSize;
	 private double yImageSize;
	 double dx;
	 double dy;
	 double dr;
	 
	 double r;
	 double w;
	 double h;
	 boolean removable = false;
	 double turnRate = 0.6;
	String name;
	boolean canMove = true;
	public Entity(Image image, String name, double xSize, double ySize, double xCoordinate, double yCoordinate, double dx, double dy, double dr) {
		//initialize variables
		this.name = name;
		this.image = new ImageView(image);
		this.image.relocate(xCoordinate, yCoordinate);
		this.setImageSize(xSize, ySize);
		this.setImageCoordinate(xCoordinate, yCoordinate);
		this.xCoordinate=xCoordinate;
		this.yCoordinate=yCoordinate;
		this.xImageSize=xSize;
		this.yImageSize=ySize;
		this.dr= dr;
		this.w= this.image.getBoundsInParent().getWidth();
		this.h = this.image.getBoundsInParent().getHeight();
		
	 
	}
	public ImageView getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = new ImageView(image);
	}
	public String getName() {
		return name;
	}
	private void setImageSize(double x , double y) {
		this.image.setFitWidth(x);
		this.image.setFitHeight(y);
	}

	public void setImageCoordinate(double x, double y) {
		this.image.setLayoutX(x);
		this.image.setLayoutY(y);
	}
	//get width and height of img boundary
    public double getWidth() {
        return w;
    }

    public double getHeight() {
    	return h;
    }
	//get center of img
  public double getCenterX() {
        return this.xCoordinate+ w * 0.5;
    }
    public double getCenterY() {
    	 return this.yCoordinate + h * 0.5;
    }
    //check if item hits on the ground
	  public boolean isOnGround() {
		  if( this.image.getLayoutY() > 600 -w) {
			  return true;
		  }
		  else
		   return false;
	  }
	  //change location of entities 
	 public void move() {

	        if( !canMove)
	            return;

	        xCoordinate += dx;
	        yCoordinate += dy;
	       // r += dr;
	    }
	 //
	 public void updateUI() {
		 	image.relocate(xCoordinate, yCoordinate);
		 	image.setRotate(r);
	    }
	 //get img boundary to check collision
    public Rectangle2D getBoundary() {
    
        return new Rectangle2D(this.getImage().getLayoutX(), 	this.getImage().getLayoutY(), this.xImageSize, this.yImageSize);
    }
//check collision with using img boundary
	  public boolean collidesWith(Entity otherEntity) {
		
	        return otherEntity.getBoundary().intersects(this.getBoundary());
	    }
	  //check whether img is removable 
	  public boolean isRemovable() {
	        return removable;
	    }
	    public void setRemovable(boolean removable) {
	        this.removable = removable;
	    }
	   public void remove() {
	        setRemovable(true);
	    }
	  public void checkRemovability() {
	}
	
}
