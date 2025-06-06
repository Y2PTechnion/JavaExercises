package my_game;

import java.awt.Color;

import base.Game;
import base.GameCanvas;
import base.Intersectable;
import base.ShapeListener;
import shapes.Circle;
import shapes.Image;
import shapes.Shape;
import ui_elements.ScreenPoint;
import base.AudioPlayer.MusicStatus;

/**
 * MyCharacter class
 * 
 * @implNote This class implements a new character that implements also ShapeListener and
 *              Intersectable interfaces to handle drag and mouse events, and to detect the
 *              'collision' with another objets.
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class MyCharacter implements ShapeListener, Intersectable {
	
	private ScreenPoint location;
    private int         circleAroundSuperMarioRadius;
	private String      imageID;
    private String      circleID;
    private int         fillColorInCircle;

	//  New character properties
    public enum ModificationType {
        RELATIVE_SIZE,
        RELATIVE_POSITION,
        RELATIVE_ROTATION,
        RELATIVE_DIRECTION,
        RELATIVE_SPEED,
        COLOR;
    }

    public enum CharacterRelative {
        NORMAL(1, 0),    
        DOUBLE(2, 4),    
        HALF(0.5, 8);

        private double factorToMultiplyFor;
        private int     baseIndex;
        private CharacterRelative(double factorToMultiplyFor, int baseIndex) {
            this.factorToMultiplyFor    = factorToMultiplyFor;
            this.baseIndex              = baseIndex;
        }

        public double factorToMultiplyFor() {
            return this.factorToMultiplyFor;
        }
    }

  public enum SuperMarioType {
        SUPER_MARIO_JUMPING(0),    
        SUPER_MARIO_RUNNING_LEFT(1),    
        SUPER_MARIO_IN_CIRCLE(2),
        SUPER_MARIO_RUNNING_RIGHT(3);

        private int     relativeIndex;
        private SuperMarioType(int relativeIndex) {
            this.relativeIndex  = relativeIndex;
        }

        public int relativeIndex() {
            return this.relativeIndex;
        }
    }
	
    private SuperMarioType      currentSuperMarioType               = SuperMarioType.SUPER_MARIO_JUMPING;
    private CharacterRelative   currentSuperMarioSize               = CharacterRelative.NORMAL;
	private int                 currentSuperMarioIndex              = 0;
	private final String[] images = {"resources/Mario1.jpg", "resources/Mario2.jpg", "resources/Mario3.jpg", "resources/Mario4.jpg",
                "resources/Mario5.jpg", "resources/Mario6.jpg", "resources/Mario7.jpg", "resources/Mario8.jpg",
                "resources/Mario9.jpg", "resources/Mario10.jpg", "resources/Mario11.jpg", "resources/Mario12.jpg"};

	/**
	 *  The following two arrays hold the widths and heights of the different images (only for the first normal sizes)
     *  The others are calculated according to the current image relative size.
	 */
	private final int[] imageWidth      = {200, 200, 200, 200};
	private final int[] imageHeight     = {199, 213, 239, 250};

	private int rotation                = 0;	// In degrees
    private int speed                   = 1;    // In pixels
	
    /**
        * MyCharacter constructor method
        * 
        * @implNote Constructor method to construct one MyCharacter object with
        *               the initial position.
        *
        * @param (ScreenPoint myCharacterLocation) (myCharacter first location into the canvas)
        * @return (MyCharacter)
        */
	public MyCharacter(ScreenPoint myCharacterLocation) {
		imageID             = "SuperMario";
        circleID            = "circleAroundSuperMario";
        fillColorInCircle   = 0;
		setLocation(new ScreenPoint(myCharacterLocation.x, myCharacterLocation.y));
	}	

	public ScreenPoint getLocation() {
		return this.location;
	}
	
	public void setLocation(ScreenPoint location) {
		this.location = location;
	}

	public void setImageID(String id) {
		this.imageID = id;
	}
	
	public String getImageID() {
		return this.imageID;
	}

	public void setCircleID(String id) {
		this.circleID = id;
	}
	
	public String getCircleID() {
		return this.circleID;
	}

    /**
        * moveLocation method
        * 
        * @implNote Moves MyCharacter right/left and/or down/up according to the dx, dy parameters
        *
        * @param (int dx) (MyCharacter moves x pixels to the right (if positive) or the left (if negative))
        * @param (int dy) (MyCharacter moves y pixels down (if positive) or up (if negative))
        * @return (No return value)
        */
	public void moveLocation(int dx, int dy) {
		this.location.x += dx;
		this.location.y += dy;
	}
	
	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
		Image i = (Image) (Game.UI().canvas().getShape(imageID));
		i.setRotation(rotation);
        this.reDrawSuperMarioCharacter(false);
	}

	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
        if (speed >= 0 && speed <= 100) {       
            this.speed  = speed;
        }
	}

    /**
        * switchSuperMarioCircleFillColor method
        * 
        * @implNote Switchs between the 6 possible fill colors
        *
        * @param () (No parameters)
        * @return (No return value)
        */
	public void switchSuperMarioCircleFillColor() {
        Color[] colour    = { Color.CYAN, Color.DARK_GRAY, Color.ORANGE, Color.LIGHT_GRAY, Color.WHITE, Color.YELLOW };  
        
        Shape shapeAroundSuperMario     = Game.UI().canvas().getShape(circleID);
        Circle circleAroundSuperMario   = (Circle) shapeAroundSuperMario;
        circleAroundSuperMario.setFillColor(colour[fillColorInCircle % colour.length]);
        fillColorInCircle++;
        this.reDrawCircleAroundSuperMario();
	}

    /**
        * switchSuperMarioSize method
        * 
        * @implNote Switchs between the 3 possible MyCharacter sizes
        *
        * @param () (No parameters)
        * @return (No return value)
        */
	public void switchSuperMarioSize() {
        final CharacterRelative currentSuperMarioSize       = this.currentSuperMarioSize;
        CharacterRelative       superMarioSizeToUpdateTo    = this.currentSuperMarioSize;

        if (CharacterRelative.NORMAL == currentSuperMarioSize) {
            superMarioSizeToUpdateTo   = CharacterRelative.DOUBLE;
        } 
        else if (CharacterRelative.DOUBLE == currentSuperMarioSize) {
            superMarioSizeToUpdateTo   = CharacterRelative.HALF;
        } 
        else if (CharacterRelative.HALF == currentSuperMarioSize){
            superMarioSizeToUpdateTo   = CharacterRelative.NORMAL;
        }
        else {
            System.out.println("Error: Super Mario size is not valid");
            return;
        }
  
        setImage(null, superMarioSizeToUpdateTo);
	}

    /**
        * moveSuperMarioCharacter method
        * 
        * @implNote moves Super Mario character by x and y pixels
        *
        * @param (int x) (x pixels to the right (if positive), or to the left (if positive))
        * @param (int y) (y pixels down (if positive), or up (if negative))
        * @return (No return value)
        */
	public void moveSuperMarioCharacter(int x, int y) {
        this.moveLocation(x, y);
        this.reDrawCircleAroundSuperMario();
        this.reDrawSuperMarioCharacter(true);
	}

    private double characterRelativeSize() {
        return this.currentSuperMarioSize.factorToMultiplyFor;
    }

    public void reDrawCircleAroundSuperMario() {
        int currentImageWidth   = (int) Math.round(characterRelativeSize() * getNormalImageWidth());
        int currentImageHeigth  = (int) Math.round(characterRelativeSize() * getNormalImageHeight());

        if (currentImageWidth > currentImageHeigth) {
            this.circleAroundSuperMarioRadius = (int) (currentImageWidth / 2.0);
        }
        else {
            this.circleAroundSuperMarioRadius = (int) (currentImageHeigth / 2.0);
        }       

        Shape shapeAroundSuperMario     = Game.UI().canvas().getShape(circleID);
        Circle circleAroundSuperMario   = (Circle) shapeAroundSuperMario;
        circleAroundSuperMario.setRadius(this.circleAroundSuperMarioRadius);
        circleAroundSuperMario.moveToLocation(location.x + this.circleAroundSuperMarioRadius,
                    location.y + this.circleAroundSuperMarioRadius);        
    }

    public void reDrawSuperMarioCharacter(boolean onlyMoveShape) {
        int currentImageWidth   = (int) Math.round(characterRelativeSize() * getNormalImageWidth());
        int currentImageHeigth  = (int) Math.round(characterRelativeSize() * getNormalImageHeight());

        Game.UI().canvas().moveShapeToLocation(imageID, location.x, location.y);

        if (false == onlyMoveShape) {
            Game.UI().canvas().changeImage(imageID, getImageName(), currentImageWidth, currentImageHeigth);
        }
    }

	public void setImage(SuperMarioType superMarioType, CharacterRelative characterRelative) {
        if  (null == superMarioType) {
            superMarioType          = this.currentSuperMarioType;
        }       

        if  (null == characterRelative) {
            characterRelative       = this.currentSuperMarioSize;
        }   

        this.currentSuperMarioType  = superMarioType;
        this.currentSuperMarioSize  = characterRelative;
        this.currentSuperMarioIndex = (this.currentSuperMarioSize.baseIndex + this.currentSuperMarioType.relativeIndex);
        this.reDrawCircleAroundSuperMario();
        this.reDrawSuperMarioCharacter(false);
	}

    /**
        * startMusic method
        * 
        * @implNote Starts a music clip
        *
        * @param (String fileToStream) (Name of file to be streamed)
        * @return (No return value)
        */
    public void startMusic(String fileToStream) {
        if (Game.audioPlayer().getStatus() != MusicStatus.STOPPED) {
            Game.audioPlayer().stop();
        }
        
        Game.audioPlayer().play("resources/audio/" + fileToStream, 1);
    }

    /**
        * addToCanvas method
        * 
        * @implNote Adds MyCharacter to the canvas
        *
        * @param () (No parameters)
        * @return (No return value)
        */
	public void addToCanvas() {
		GameCanvas canvas   = Game.UI().canvas();
		//  Create the character's graphical elements and add them to the canvas
        int currentImageWidth   = (int) Math.round(characterRelativeSize() * getNormalImageWidth());
        int currentImageHeigth  = (int) Math.round(characterRelativeSize() * getNormalImageHeight());
        int currentCircleRadius = (int) (currentImageWidth / 2.0);

        if (currentImageHeigth > currentImageWidth) {
            currentCircleRadius = (int) (currentImageHeigth / 2.0);
        }

		Image image         = new Image(getImageID(), getImageName(), currentImageWidth, currentImageHeigth,
                                    location.x, location.y);
		image.setShapeListener(this);
		image.setzOrder(3);
		canvas.addShape(image);

		Circle circleAroundSuperMario   = new Circle(getCircleID(), location.x + currentCircleRadius, 
                    location.y + currentCircleRadius, 
                    currentCircleRadius);
        circleAroundSuperMario.setColor(Color.BLUE);
        circleAroundSuperMario.setFillColor(Color.YELLOW);
        circleAroundSuperMario.setIsFilled(true);
        circleAroundSuperMario.setWeight(10);
		circleAroundSuperMario.setDraggable(false);
		canvas.addShape(circleAroundSuperMario);
	}
	
	//  Add setters, getters and other methods that you need for your character

    /**
        * getImageName method
        * 
        * @implNote Image's name getter
        *
        * @param () (No parameters)
        * @return (String) (Image's name)
        */
    public String getImageName() {
		return images[currentSuperMarioIndex];
	}

    /**
        * getNormalImageWidth method
        * 
        * @implNote Image's width getter (only for the normal size)
        *
        * @param () (No parameters)
        * @return (int) (Image's width in pixels)
        */
	private int getNormalImageWidth() {
		return imageWidth[currentSuperMarioIndex % 4];
	}
	
    /**
        * getNormalImageHeight method
        * 
        * @implNote Image's height getter (only for the normal size)
        *
        * @param () (No parameters)
        * @return (int) (Image's height in pixels)
        */
	private int getNormalImageHeight() {
		return imageHeight[currentSuperMarioIndex % 4];
	}

    /**
        * getCircleRadius method
        * 
        * @implNote Circle around Super Mario radius
        *
        * @param () (No parameters)
        * @return (int) (Radius in pixels)
        */
	public int getCircleRadius() {
		return circleAroundSuperMarioRadius;
	}

    /**
        * getSuperMarioType method
        * 
        * @implNote Gets current Super Mario type
        *
        * @param () (No parameters)
        * @return (SuperMarioType) (Super Mario current type)
        */
    public SuperMarioType getSuperMarioType() {
            return currentSuperMarioType;
        }     

    //  ShapeListener base class methods to be implemented
    //  ShapeListener base class methods to be implemented
    //  ShapeListener base class methods to be implemented

    /**
        * shapeMoved method
        * 
        * @implNote ShapeListener's shapeMoved method
        *
        * @param (String shapeID) (shape's ID)
        * @param (int dx) (dx pixels to move to right (if positive), or to the left (if negative))
        * @param (int dy) (dy pixels to move down (if positive), or up (if negative))
        * @return (No return value)
        */
	@Override
	public void shapeMoved(String shapeID, int dx, int dy) {
		moveLocation(dx, dy);
	}

    /**
        * shapeStartDrag method
        * 
        * @implNote ShapeListener's shapeStartDrag method (when MyCharacter has been started dragging)
        *
        * @param (String shapeID) (shape's ID)
        * @return (No return value)
        */
	@Override
	public void shapeStartDrag(String shapeID) {
		// Auto-generated method stub
	}

    /**
        * shapeEndDrag method
        * 
        * @implNote ShapeListener's shapeEndDrag method (when MyCharacter has been stopped dragging)
        *
        * @param (String shapeID) (shape's ID)
        * @return (No return value)
        */
	@Override
	public void shapeEndDrag(String shapeID) {
		// Auto-generated method stub
	}

    /**
        * shapeClicked method
        * 
        * @implNote ShapeListener's shapeClicked method
        *
        * @param (String shapeID) (shape's ID)
        * @param (int x) (x position)
        * @param (int y) (y position)
        * @return (No return value)
        */
	@Override
	public void shapeClicked(String shapeID, int x, int y) {
	}

    /**
        * shapeRightClicked method
        * 
        * @implNote ShapeListener's shapeRightClicked method when the user right clicked over MyCharacter
        *
        * @param (String shapeID) (shape's ID)
        * @param (int x) (x position)
        * @param (int y) (y position)
        * @return (No return value)
        */
	@Override
	public void shapeRightClicked(String shapeID, int x, int y) {
        //  Switch between the 3 possible MyCharacter sizes
        switchSuperMarioSize();
	}

    /**
        * mouseEnterShape method
        * 
        * @implNote ShapeListener's mouseEnterShape method when the user is over MyCharacter
        *
        * @param (String shapeID) (shape's ID)
        * @param (int x) (x position)
        * @param (int y) (y position)
        * @return (No return value)
        */
	@Override
	public void mouseEnterShape(String shapeID, int x, int y) {
	}

	@Override
	public void mouseExitShape(String shapeID, int x, int y) {
	//  Not doing anything in the meantime
	}

    //  Intersectable base class method to be implemented
    //  Intersectable base class method to be implemented
    //  Intersectable base class method to be implemented
    @Override
    public ScreenPoint[] getIntersectionVertices() {
        int intersectionWidth   = this.getNormalImageWidth();
        int intersectionHeight  = this.getNormalImageHeight();

        int leftX = this.location.x;
        int topY = this.location.y;

        // ScreenPoint[] vertices = {
        //         new ScreenPoint(centerX - intersectionWidth / 2, centerY - intersectionHeight / 2),
        //         new ScreenPoint(centerX + intersectionWidth / 2, centerY - intersectionHeight / 2),
        //         new ScreenPoint(centerX + intersectionWidth / 2, centerY + intersectionHeight / 2),
        //         new ScreenPoint(centerX - intersectionWidth / 2, centerY + intersectionHeight / 2)
        // };
        ScreenPoint[] vertices = {
			new ScreenPoint(leftX, topY),
		    new ScreenPoint(leftX + intersectionWidth, topY),
		    new ScreenPoint(leftX + intersectionWidth, topY + intersectionHeight),
		    new ScreenPoint(leftX, topY + intersectionHeight)
        //new ScreenPoint(leftX, topY),
        //new ScreenPoint(leftX, topY),
        //new ScreenPoint(leftX, topY)
	};
        return vertices;
    }
}	

