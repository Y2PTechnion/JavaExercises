package my_base;


import java.awt.Color;
import java.awt.Rectangle;

import base.Game;
import base.GameCanvas;
import base.PeriodicLoop;
import javafx.scene.image.Image;
import my_game.MyCharacter;
import shapes.Circle;
import shapes.Shape;
import ui_elements.ScreenPoint;

import base.IntersectionAlgorithm;

public class MyPeriodicLoop extends PeriodicLoop {

	private MyContent   content;

	public void setContent(MyContent content) {
		this.content = content;
	}
	
	@Override
	public void execute() {
		//  Let the super class do its work first
		super.execute();
		
		//  You can comment this line if you don't want the pokimon to move.
		redrawPokimon();
		
		//  Redraw your character periodically by calling the correct method
        redrawCharacter();

        //  Verifies if the Pokimon and Super Mario collide
        if (null != content.superMario()) {
            if (IntersectionAlgorithm.areIntersecting(content.pokimon(), content.superMario())) {
                //  Shows the stop sign
                ScreenPoint superMarioCoordinates   = content.superMario().getLocation();
                content.stopSignShow(superMarioCoordinates.x, superMarioCoordinates.y);
                content.superMario().startMusic("automobile-horn-153260.wav");
            }
            else {
                //  Hides the stop sign
                content.stopSignHide();
            }
        }
	}
	
	private void redrawPokimon() {
		content.pokimon().move();
	}

	private void redrawCharacter() {
		GameCanvas canvas       = Game.UI().canvas();	
	    MyCharacter superMario  = content.superMario();
		
		//  Since this function is called every interval, it will also be called
		//  before the character is created. Therefore, we check if the character 
		//  exists and if not, we return without doing anything.
		
        if (null != superMario)
        {
            //  NOTE: This logic should NOT be done here, but it is done here
            //  only to experiment with new features as requested by the professor.

		    //  Call the canvas to change the shape properties according to
		    //  its current property values
		    //  You can get the shape using canvas.getShape(id) with the id of your character
		    //  Then you can cast it so you can refer to its specific properties.
		    //  For example, if your shape is a Circle you can use:
		    //  Circle circle = (Circle) canvas.getShape(id)
		    //  and then change the specific Circle properties.
            final long          elapsedTimeMilliSeconds         = super.elapsedTime();
            final int           decisionTime                    = ((int) (elapsedTimeMilliSeconds / 5000.0)) % 4;
            final String        circleAroundSuperMarioStringId  = superMario.getCircleID();
            final ScreenPoint   superMarioLocation              = superMario.getLocation();
            final Rectangle     canvasLimits                    = canvas.getBounds();
            Shape               shapeAroundSuperMario           = canvas.getShape(circleAroundSuperMarioStringId);
            Circle              circleAroundSuperMario          = (Circle) shapeAroundSuperMario;
            ScreenPoint         superMarioNewLocation           = new ScreenPoint((int) canvasLimits.getCenterX(), (int) canvasLimits.getCenterY());

            if (superMarioLocation.x < canvasLimits.x) {
                superMario.startMusic("smb_stage_clear.wav");
                superMario.setLocation(superMarioNewLocation);
            } 
            else if (superMarioLocation.y < canvasLimits.y) {
                superMario.startMusic("smb_world_clear.wav");
                superMario.setLocation(superMarioNewLocation);
            }
            else if ((superMarioLocation.x + superMario.getCircleRadius()) > canvasLimits.width) {
                superMario.startMusic("smb_mariodie.wav");
                superMario.setLocation(superMarioNewLocation);
            }
            else if ((superMarioLocation.y + superMario.getCircleRadius()) > canvasLimits.height) {
                superMario.startMusic("smb_gameover.wav");
                superMario.setLocation(superMarioNewLocation);
            }
            
            if (0 == decisionTime) {
                circleAroundSuperMario.setColor(Color.BLUE);
            }
            else if (1 == decisionTime) {
                circleAroundSuperMario.setColor(Color.RED);              
            }
            else if (2 == decisionTime) {
                circleAroundSuperMario.setColor(Color.GREEN);
            }
            else if (3 == decisionTime) {
                circleAroundSuperMario.setColor(Color.PINK);
            }   

            superMario.reDrawSuperMarioCharacter(true);
            superMario.reDrawCircleAroundSuperMario();
        }
	}
}
