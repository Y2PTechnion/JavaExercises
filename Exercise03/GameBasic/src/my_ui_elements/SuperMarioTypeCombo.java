package my_ui_elements;

import base.Game;
import my_base.MyContent;
import my_game.MyCharacter;
import ui_elements.GameComboBox;

/**
 * SuperMarioTypeCombo class
 * 
 * @implNote This class extends the GameComboBox class and is used to create a combo box
 *              for selecting the type of Super Mario character.
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class SuperMarioTypeCombo extends GameComboBox {
    MyContent myContent;

    public SuperMarioTypeCombo(int posX, int posY) {
        super("superMarioTypeCombo", "Super Mario Type", posX, posY, 160, 30, new String[] {"Mario Jumping", "Mario Running Left", "Mario In Circle", "Mario Running Right"});
        myContent = (MyContent) Game.Content();
        this.comboBox.setSelectedItem("Mario Jumping");
    }

    public void setMarioCharacterType(String superMarioCharacterType) {
        this.comboBox.setSelectedItem(superMarioCharacterType);
    }

    @Override
	public void action() {
        super.action();
        if (null != myContent.superMario()) {

            switch (getOption()) {
                case "Mario Jumping": {
                    myContent.superMario().setImage(MyCharacter.SuperMarioType.SUPER_MARIO_JUMPING, null);
                    break;
                }

                case "Mario Running Left": {
                    myContent.superMario().setImage(MyCharacter.SuperMarioType.SUPER_MARIO_RUNNING_LEFT, null);
                    break;
                }

                case "Mario In Circle": {
                    myContent.superMario().setImage(MyCharacter.SuperMarioType.SUPER_MARIO_IN_CIRCLE, null);
                    break;
                }

                case "Mario Running Right": {
                    myContent.superMario().setImage(MyCharacter.SuperMarioType.SUPER_MARIO_RUNNING_RIGHT, null);
                    break;
                }

                default: {
                    break;
                }
            }
        }
        else {
            System.out.println("No Super Mario object was created");
            return;
        }
	}
}
