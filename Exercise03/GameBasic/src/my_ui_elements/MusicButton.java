package my_ui_elements;

import java.awt.Color;

import base.Game;
import base.AudioPlayer.MusicStatus;
import ui_elements.GameImageButton;

import shapes.TextLabel;

public class MusicButton extends GameImageButton{
	
	public MusicButton(String id, String name, int posX, int posY) {
		super(id, name, 70, 60, posX, posY, "resources/play.jpg");
	}

	@Override
	public void action() {
		// The basic buttonAction prints the id of the button to the console.
		// Keep the call to super to preserve this behavior or remove it if you don't want the printing.
		super.action();
		if (Game.audioPlayer().getStatus() == MusicStatus.STOPPED) {
			Game.audioPlayer().play("resources/audio/audio_sample.wav", 0);
			this.setImage("resources/stop.jpg");
			//this.setText("Stop");
			Game.UI().canvas().setBackgroundImage("resources/background2.jpg");
            //  TODO: These lines are not working, why ?
            TextLabel   t1 = (TextLabel) (Game.UI().canvas().getShape("t1"));
            t1.setColor(Color.WHITE);
            TextLabel   t2 = (TextLabel) (Game.UI().canvas().getShape("t2"));
            t2.setColor(Color.WHITE);
            TextLabel   t3 = (TextLabel) (Game.UI().canvas().getShape("t3"));
            t3.setColor(Color.WHITE);
            TextLabel   t4 = (TextLabel) (Game.UI().canvas().getShape("t4"));
            t4.setColor(Color.WHITE);
            TextLabel   t5 = (TextLabel) (Game.UI().canvas().getShape("t5"));
            t5.setColor(Color.WHITE);
            TextLabel   t6 = (TextLabel) (Game.UI().canvas().getShape("t6"));
            t6.setColor(Color.WHITE);
            TextLabel   t7 = (TextLabel) (Game.UI().canvas().getShape("t7"));
            t7.setColor(Color.WHITE);
            TextLabel   t8 = (TextLabel) (Game.UI().canvas().getShape("t8"));
            t8.setColor(Color.WHITE);
            TextLabel   t9 = (TextLabel) (Game.UI().canvas().getShape("t9"));
            t9.setColor(Color.WHITE);
		}
		else {
			Game.audioPlayer().stop();
			this.setImage("resources/play.jpg");
			//this.setText("Play");
			Game.UI().canvas().setBackgroundImage("resources/background1.jpg");
            //  TODO: These lines are not working, why ?
            TextLabel   t1 = (TextLabel) (Game.UI().canvas().getShape("t1"));
            t1.setColor(Color.BLACK);
            TextLabel   t2 = (TextLabel) (Game.UI().canvas().getShape("t2"));
            t2.setColor(Color.BLACK);
            TextLabel   t3 = (TextLabel) (Game.UI().canvas().getShape("t3"));
            t3.setColor(Color.BLACK);
            TextLabel   t4 = (TextLabel) (Game.UI().canvas().getShape("t4"));
            t4.setColor(Color.BLACK);
            TextLabel   t5 = (TextLabel) (Game.UI().canvas().getShape("t5"));
            t5.setColor(Color.BLACK);
            TextLabel   t6 = (TextLabel) (Game.UI().canvas().getShape("t6"));
            t6.setColor(Color.BLACK);
            TextLabel   t7 = (TextLabel) (Game.UI().canvas().getShape("t7"));
            t7.setColor(Color.BLACK);
            TextLabel   t8 = (TextLabel) (Game.UI().canvas().getShape("t8"));
            t8.setColor(Color.BLACK);
            TextLabel   t9 = (TextLabel) (Game.UI().canvas().getShape("t9"));
            t9.setColor(Color.BLACK);
		}
	}

}
