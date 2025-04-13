///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Exercise03 (Third exercise)
// Files:            MyGame.java
// Semester:         Spring 2025
//
// Author:           YuvalYossiPablo
// Email:            
// CS Login:         
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Yossi Huttner
// Email:            yossihuttner@yahoo.com
// CS Login:         yossef.h@campus.technion.ac.il
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Yuval Shechter
// Email:            yuvalshe@gmail.com
// CS Login:         y.shechter@campus.technion.ac.il
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Pablo Daniel Jelsky
// Email:            PabloDanielJelsky@gmail.com
// CS Login:         pablo.jelsky@campus.technion.ac.il
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   The headers in this file were taken as an example from
//                   https://pages.cs.wisc.edu/~cs302/resources/guides/commenting.html
//
//////////////////////////// 80 columns wide //////////////////////////////////

package my_base;

import java.awt.Color;

import base.Game;
import base.GameCanvas;
import base.GameContent;
import base.GameDashboard;
import my_game.MyCharacter;
import my_game.Pokimon;
import my_ui_elements.AddButton;
import my_ui_elements.ChangeButton;
import my_ui_elements.DirectionCombo;
import my_ui_elements.DragCircleCB;
import my_ui_elements.EditPolygonButton;
import my_ui_elements.EndButton;
import my_ui_elements.GetNameButton;
import my_ui_elements.MusicButton;
import my_ui_elements.RotatePolygonButton;
import my_ui_elements.SlowDownButton;
import my_ui_elements.SuperMarioTypeCombo;
import shapes.Circle;

/**
 * MyGame class
 * 
 * @implNote This class is an a group exercise that has as the objective
 *              the understanding of the game infrastructure.
 *           0) The modifications should be done on GameBasic.zip files.
 *           1) Understanding the game infrastructure and how to use it, 
 *              the current base code and what it does.
 *              1.1) Run the code.
 *              1.2) Pokimon
 *              1.3) Circle
 *              1.4) Polygon
 *              1.5) Play button
 *              1.6) End button
 * 
 *           2) Addition of a new character in the game
 *              2.1) MyCharacter - Character definition
 *              2.2) MyContent - Insertion of the new character in the game content
 *              2.3) MyButton - Button to insert the new character
 *              2.4) First run test
 *              2.5) Changes in character through changeCharacter() method in MyContent
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class MyGame extends Game {
	
	private MyContent content;

	@Override
	protected void initCanvas() {
		
		GameCanvas canvas = gameUI.canvas();
		canvas.setMouseHandler(Game.MouseHandler());
		canvas.setBackground(Color.WHITE);
		canvas.setBackgroundImage("resources/background1.jpg");

		Pokimon pokimon = content.pokimon();
		pokimon.addToCanvas();
		canvas.addShape(content.polygon().getVisualPolygon());
		Circle c = new Circle("circle", 300, 300, 50);
		c.setDraggable(false);
		canvas.addShape(c);
		/**
		 * This is a use of a shape button.
		 * Note that it uses the addToCanvas method of the button and is not added in the regular way,
		 * since it includes multiple ui elements.
		 */
		SlowDownButton slow = new SlowDownButton("SlowDown", 800, 600);
		slow.addToCanvas();
	}
	
	@Override
	protected void initDashboard() {
		super.initDashboard();
		GameDashboard dashboard = gameUI.dashboard();
		
		dashboard.setBackground(Color.BLACK);
		
		// Add a the Polygon buttons
		dashboard.addUIElement(new EditPolygonButton("editButton", "Edit", 60, 40));
		dashboard.addUIElement(new RotatePolygonButton("rotateButton", "Rotate", 60, 100));

		// Add a the Circle drag checkbox
		dashboard.addUIElement(new DragCircleCB("dragCB", "Drag Circle", 280, 80, 200, 40, false));

		// Add a the direction list combo
		dashboard.addUIElement(new DirectionCombo(280, 40));

        // Add a Super Mario type list combo
        dashboard.addUIElement(new SuperMarioTypeCombo(280, 120));

		// Add a the AddButton button
		dashboard.addUIElement(new AddButton("addButton", "Add", 540, 40));
		
		// Add the ChangeButton button to the dashboard
		dashboard.addUIElement(new ChangeButton("changeButton", "Change", 540, 100));
		dashboard.addUIElement(new MusicButton("musicButton", "Play", 700, 40));

		dashboard.addUIElement(new EndButton("btnEND", "END", 130, 40, 800, 50));
		dashboard.addUIElement(new GetNameButton("btnName", "Get Name", 130, 40, 800, 100));
	}
	
	@Override
	public void setGameContent(GameContent content) {
		// Call the Game superclass to set its content 
		super.setGameContent(content);
		// point to the content with a variable of type MyContent so we have access to all
		// our game specific data
		this.content = (MyContent) content;
	}
	
	public MyContent getContent() {
		return this.content;
	}
	
	public static void main(String[] args) {
		MyGame game                     = new MyGame();
		game.setGameContent(new MyContent());
		MyPeriodicLoop periodicLoop     = new MyPeriodicLoop();
		periodicLoop.setContent(game.getContent());
		game.setPeriodicLoop(periodicLoop);
		game.setMouseHandler(new MyMouseHandler());
		game.setKeyboardListener(new MyKeyboardListener());
		game.initGame();
	}
}
