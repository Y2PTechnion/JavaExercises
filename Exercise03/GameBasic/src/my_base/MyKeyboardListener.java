package my_base;

import my_game.MyCharacter;
import my_game.Pokimon;
import my_ui_elements.DirectionCombo;

import java.awt.event.KeyEvent;

import base.Game;
import base.KeyboardListener;

public class MyKeyboardListener extends KeyboardListener{

	private MyContent myContent;
	
	public MyKeyboardListener() {
		super();
		myContent = (MyContent) this.content;
	}

	@Override
	public void directionalKeyPressed(Direction direction) {
        switch (direction) {
		    case RIGHT: {
			    myContent.pokimon().setDirectionPolicy(Pokimon.Direction.RIGHT);
			    ((DirectionCombo) (Game.UI().dashboard().getUIElement("directionCombo"))).setDirection("Right");
                //  Moves Super Mario to right
                myContent.changeCharacter(MyCharacter.ModificationType.RELATIVE_POSITION, 5, 0);
			    break;
            }

		    case LEFT: {
			    myContent.pokimon().setDirectionPolicy(Pokimon.Direction.LEFT);
			    ((DirectionCombo) (Game.UI().dashboard().getUIElement("directionCombo"))).setDirection("Left");
                //  Moves Super Mario to left
                myContent.changeCharacter(MyCharacter.ModificationType.RELATIVE_POSITION, -5, 0);
			    break;
            }

		    case UP: {
			    //myContent.pokimon().setDirectionPolicy(Pokimon.Direction.UP);
			    myContent.pokimon().setRotation(myContent.pokimon().getRotation() + 20);
                //  Moves Super Mario up
                myContent.changeCharacter(MyCharacter.ModificationType.RELATIVE_POSITION, 0, -5);
			    break;
            }

		    case DOWN: {
			    //myContent.pokimon().setDirectionPolicy(Pokimon.Direction.DOWN);
			    myContent.pokimon().setRotation(myContent.pokimon().getRotation() - 20);
                //  Moves Super Mario down
                myContent.changeCharacter(MyCharacter.ModificationType.RELATIVE_POSITION, 0, 5);
			    break;
            }
		}
	}
	
	@Override
	public void characterTyped(char c) {
		System.out.println("key character = '" + c + "'" + " pressed.");
        if ('r' == c) {
            myContent.superMario().setRotation(myContent.superMario().getRotation() + 45);
        } 
        else if ('R' == c) {
            myContent.superMario().setRotation(myContent.superMario().getRotation() - 45);
        }
    }
	
	@Override
	public void enterKeyPressed() {
		System.out.println("enter key pressed.");
	}
	
	@Override
	public void backSpaceKeyPressed() {
		System.out.println("backSpace key pressed.");
	}
	
	@Override
	public void spaceKeyPressed() {
		System.out.println("space key pressed.");
	}
	
	public void otherKeyPressed(KeyEvent e) {
		System.out.println("other key pressed. type:" + e);
	}
}
