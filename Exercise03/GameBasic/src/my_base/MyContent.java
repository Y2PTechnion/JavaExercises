package my_base;




import my_game.Pokimon;
import ui_elements.ScreenPoint;

import base.GameContent;
import my_game.MyCharacter;
import my_game.MyPolygon;
import shapes.Shape;
import shapes.Image;


public class MyContent extends GameContent {
	private Pokimon         pokimon;
	private MyPolygon       myPolygon;
	
	//  Declare your own character
    private MyCharacter     superMario;
    private Image           stopImage;

	@Override
	public void initContent() {
		pokimon = new Pokimon();
		ScreenPoint[] points = {
			new ScreenPoint(100, 100),
			new ScreenPoint(130, 50),
			new ScreenPoint(170, 50),
			new ScreenPoint(200, 100),
			new ScreenPoint(220, 170),
			new ScreenPoint(170, 150),
			new ScreenPoint(130, 150)
		};

		myPolygon = new MyPolygon(points);

        //  Create the stop image and hide it
        stopImage   = new Image("stopImage", "resources/stop-hand-floor-graphic-marker-1_1.jpg", 100, 75, 200,200);
        stopImage.setzOrder(10);
        stopSignHide();
	}	
	
	public Pokimon pokimon() {
		return pokimon;
	}

	public MyPolygon polygon() {
		return myPolygon;
	}
	
    /**
        * addCharacter method
        * 
        * @implNote Create an instance of your character and set its properties with initial position,
        *               adding it to the canvas.
        *
        * @param (ScreenPoint myCharacterLocation) (myCharacter first location into the canvas)
        * @return (No return value)
        */
	public void addCharacter(ScreenPoint myCharacterLocation) {
        if (null == superMario)
        {
            //  Only create a new superMario if it was not created earlier
            superMario = new MyCharacter(myCharacterLocation);
		
		    //  Add your character visual representation to the canvas using its addToCanvas() method.
            superMario.addToCanvas();
        }
        else
        {
            System.out.println("A previous 'Super Mario' object was created");
        }
    }
	
    /**
        * superMario method
        * 
        * @implNote This method returns your character ('Super Mario') for others to use.
        *
        * @param () (No parameters)
        * @return (MyCharacter)
        */
	public MyCharacter superMario() {
		return superMario;
	}

    /**
        * stopSign method
        * 
        * @implNote This method returns the stop sign to be shown when there is collision for others to use.
        *
        * @param () (No parameters)
        * @return (Image)
        */
	public Image stopSign() {
		return stopImage;
	}

    /**
        * stopSignHide method
        * 
        * @implNote This method hides the stop sign
        *
        * @param () (No parameters)
        * @return (No return value)
        */
	public void stopSignHide() {
		stopImage.setStatus(Shape.STATUS.HIDE);
	}

    /**
        * stopSignShow method
        * 
        * @implNote This method shows the stop sign
        *
        * @param (int x) (No parameters)
        * @return (No return value)
        */
	public void stopSignShow(int x, int y) {
        stopImage.moveToLocation(x, y);
		stopImage.setStatus(Shape.STATUS.SHOW);
	}
	
    /**
        * changeCharacter method
        * 
        * @implNote changeCharacter method changes inside all the properties you like
        *               NOTE: Even though this method should be 'generic', meaning another
        *               character can be used, we are using 'superMario' as a reference to
        *               the character we want to modify. It is ONLY an exercise based on        
        *               the 'superMario' character. In a more generic implementation we 
        *               would use the character reference as a parameter.   
        *
        * @param (ModificationType modificationType) (Type of modification we want to perform)
        * @param (int x) (x position) - optional for overloading
        * @param (int y) (y position) - optional for overloading
        * @return (No return value)
        */
    public void changeCharacter(MyCharacter.ModificationType modificationType) {
        changeCharacter(modificationType, 0, 0);
    }
    public void changeCharacter(MyCharacter.ModificationType modificationType, int x) {
        changeCharacter(modificationType, x, 0);
    }
    public void changeCharacter(MyCharacter.ModificationType modificationType, int x, int y) {
        if (null != superMario)
        {
            //  superMario object was created

            switch (modificationType) {
                case RELATIVE_SIZE: {
                    superMario.switchSuperMarioSize();
                    break;
                }

                case RELATIVE_POSITION: {
                    superMario.moveSuperMarioCharacter(x, y);
                    break;
                }
                case RELATIVE_ROTATION: {
                    superMario.setRotation(superMario.getRotation() + x);
                    break;
                }       
                case RELATIVE_DIRECTION: {
 //                   superMario.setDirection(x);
                    break;
                }   
                case RELATIVE_SPEED: {
                    superMario.setSpeed(superMario.getSpeed() + x);
                    break;
                }   

                case COLOR: {
 //                   superMario.setColor(x);
                    break;
                }       
                
                default:
                    System.out.println("Unknown modification type");
                    break;
            }
        }
        else
        {
            //  superMario object has not been created yet
            System.out.println("The 'Super Mario' object has not been created yet");
        }
    }
}
