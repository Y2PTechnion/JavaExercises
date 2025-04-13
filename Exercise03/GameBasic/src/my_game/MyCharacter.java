package my_game;

import base.Game;
import base.GameCanvas;
import base.Intersectable;
import base.ShapeListener;
import shapes.Image;
import ui_elements.ScreenPoint;

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
	private String imageID;

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
	
	//  New character properties
    public enum Direction {
        NORTH (0, -10),		
        NORTH_EAST (10, -10),
        EAST (10, 0),
        SOUTH_EAST (10, 10),
        SOUTH (0, 10),
        SOUTH_WEST(-10, 10),
        WEST (-10, 0),
        NORTH_WEST (-10, -10);
    
        private final int xVec, yVec;
        private Direction(int xVec, int yVec) {
            this.xVec = xVec;
            this.yVec = yVec;
        }

        public int xVec() {
            return xVec;
        }
        public int yVec() {
            return yVec;
        }
    }

	private Direction directionPolicy                   = Direction.EAST;
	private Direction direction                         = Direction.EAST;


    private SuperMarioType      currentSuperMarioType   = SuperMarioType.SUPER_MARIO_JUMPING;
    private CharacterRelative   currentSuperMarioSize   = CharacterRelative.NORMAL;
	private int                 currentSuperMarioIndex  = 0;
	private final String[] images = {"resources/Mario1.jpg", "resources/Mario2.jpg", "resources/Mario3.jpg", "resources/Mario4.jpg",
                "resources/Mario5.jpg", "resources/Mario6.jpg", "resources/Mario7.jpg", "resources/Mario8.jpg",
                "resources/Mario9.jpg", "resources/Mario10.jpg", "resources/Mario11.jpg", "resources/Mario12.jpg"};

	
	/**
	 *  The following two arrays hold the widths and heights of the different images (only for the first normal sizes)
     *  The others are calculated according to the current image relative size.
	 */
	private final int[] imageWidth      = {200, 200, 200, 200};
	private final int[] imageHeight     = {199, 213, 239, 250};

	
	private boolean isMoving            = true;
	private int rotation                = 0;	// In degrees
	
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
		imageID = "SuperMario";
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
	}

    /**
        * switchSuperMarioImage method
        * 
        * @implNote Switchs between the 4 possible MyCharacter images
        *
        * @param () (No parameters)
        * @return (No return value)
        */
	public void switchSuperMarioImage() {
        final SuperMarioType    currentSuperMarioType       = this.currentSuperMarioType;
        final CharacterRelative currentSuperMarioSize       = this.currentSuperMarioSize;
        SuperMarioType          superMarioTypeToUpdateTo    = this.currentSuperMarioType;

        if (SuperMarioType.SUPER_MARIO_JUMPING == currentSuperMarioType) {
            superMarioTypeToUpdateTo    = SuperMarioType.SUPER_MARIO_RUNNING_LEFT;
        } 
        else if (SuperMarioType.SUPER_MARIO_RUNNING_LEFT == currentSuperMarioType) {
            superMarioTypeToUpdateTo    = SuperMarioType.SUPER_MARIO_IN_CIRCLE;
        } 
        else if (SuperMarioType.SUPER_MARIO_IN_CIRCLE == currentSuperMarioType) {
            superMarioTypeToUpdateTo    = SuperMarioType.SUPER_MARIO_RUNNING_RIGHT;
        } 
        else {
            superMarioTypeToUpdateTo    = SuperMarioType.SUPER_MARIO_JUMPING;
        }
  
        setImage(superMarioTypeToUpdateTo, currentSuperMarioSize);
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
        final SuperMarioType    currentSuperMarioType       = this.currentSuperMarioType;
        final CharacterRelative currentSuperMarioSize       = this.currentSuperMarioSize;
        CharacterRelative       superMarioSizeToUpdateTo    = this.currentSuperMarioSize;

        if (CharacterRelative.NORMAL == currentSuperMarioSize) {
            superMarioSizeToUpdateTo   = CharacterRelative.DOUBLE;
        } 
        else if (CharacterRelative.DOUBLE == currentSuperMarioSize) {
            superMarioSizeToUpdateTo   = CharacterRelative.HALF;
        } 
        else {
            superMarioSizeToUpdateTo   = CharacterRelative.NORMAL;
        }
  
        setImage(currentSuperMarioType, superMarioSizeToUpdateTo);
	}

    private double characterRelativeSize() {
        return this.currentSuperMarioSize.factorToMultiplyFor;
    }

	public void setImage(SuperMarioType superMarioType, CharacterRelative characterRelative) {
        if  (superMarioType != this.currentSuperMarioType || characterRelative != this.currentSuperMarioSize) {
            if  (null == characterRelative) {
                characterRelative = this.currentSuperMarioSize;
            }   

            if  (null == superMarioType) {
                superMarioType = this.currentSuperMarioType;
            }       

            this.currentSuperMarioType  = superMarioType;
            this.currentSuperMarioSize  = characterRelative;
            this.currentSuperMarioIndex = (this.currentSuperMarioSize.baseIndex + this.currentSuperMarioType.relativeIndex);
            Game.UI().canvas().changeImage(imageID, getImageName(), (int) Math.round(characterRelativeSize() * getImageWidth()), 
                    (int) Math.round(characterRelativeSize() * getImageHeight()));
        }
	}

	public void stopMoving() {
		isMoving = false;
	}

	public void resumeMoving() {
		isMoving = true;
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
		Image image         = new Image(getImageID(), getImageName(), 
                                    (int) Math.round(characterRelativeSize() * getImageWidth()), 
                                    (int) Math.round(characterRelativeSize() * getImageHeight()),
                                    location.x, location.y);
		image.setShapeListener(this);
		image.setzOrder(3);
		canvas.addShape(image);
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
        * getImageWidth method
        * 
        * @implNote Image's width getter
        *
        * @param () (No parameters)
        * @return (int) (Image's width in pixels)
        */
	private int getImageWidth() {
		return imageWidth[currentSuperMarioIndex % 4];
	}
	
    /**
        * getImageHeight method
        * 
        * @implNote Image's height getter
        *
        * @param () (No parameters)
        * @return (int) (Image's height in pixels)
        */
	private int getImageHeight() {
		return imageHeight[currentSuperMarioIndex % 4];
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
	    stopMoving();
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
	    switchSuperMarioSize();
	}

    /**
        * mouseEnterShape method
        * 
        * @implNote ShapeListener's mouseEnterShape method when the user left clicked over MyCharacter
        *
        * @param (String shapeID) (shape's ID)
        * @param (int x) (x position)
        * @param (int y) (y position)
        * @return (No return value)
        */
	@Override
	public void mouseEnterShape(String shapeID, int x, int y) {
	    switchSuperMarioImage();
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
        //int intersectionWidth = getImageWidth();
        //int intersectionHeight = getImageHeight();

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
		//	new ScreenPoint(leftX + intersectionWidth, topY),
		//	new ScreenPoint(leftX + intersectionWidth, topY + intersectionHeight),
		//	new ScreenPoint(leftX, topY + intersectionHeight)
        new ScreenPoint(leftX, topY),
        new ScreenPoint(leftX, topY),
        new ScreenPoint(leftX, topY)
	};
        return vertices;
    }
}	

