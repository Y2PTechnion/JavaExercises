package my_ui_elements;

import base.Game;
import my_base.MyContent;
import my_game.MyCharacter;
import my_game.Pokimon;
import ui_elements.GameButton;
import ui_elements.ScreenPoint;

public class AddButton extends GameButton{
	
	public AddButton(String id, String name, int posX, int posY) {
		super(id, name, 100, 40, posX, posY);
	}

	@Override
	public void action() {
		// The basic buttonAction prints the id of the button to the console.
		// Keep the call to super to preserve this behavior or remove it if you don't want the printing.
		super.action();
		
		MyContent content = (MyContent) Game.Content();

		//  Add your character to your game content
        ScreenPoint myCharacterLocation     = new ScreenPoint(150, 500);
        content.addCharacter(myCharacterLocation);
	}

}
