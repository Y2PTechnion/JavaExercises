///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Exercise02 (Second exercise)
// Files:            Ex2.java
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
package my_game;

import shapes.Circle;
import shapes.Line;
import shapes.Text;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * Ex1 class
 * 
 * @implNote This class is an a group exercise that should implement the
 *           following classes and verifications.
 *           1) The modifications should be done on GameEx2.zip files.
 *           2) MyPoint class
 *              2.1) Implement the following methods in MyPoint class
 *                  2.1.1) MyPoint constructor without parameters - public MyPoint()
 *                  2.1.2) MyPoint constructor with parameters x, y - public MyPoint(int x, int y)
 *                  2.1.3) MyPoint setters and getters implementation
 *                      public void set(int x, int y)
 *                      public void set(MyPoint myPoint)
 *                      public int getX()
 *                      public int getY()
 *                      public MyPoint get()
 *                  2.1.4) public String toString() implementation printing (x, y)
 *                  2.1.5) getDistance implementation to calculated distance between another point and the object point
 *                      public double getDistance(int x, int y)
 *                      public double getDistance(MyPoint myPoint)
 *           3) MyCircle class
 *              3.1) Implement the following methods in MyCircle class
 *                  3.1.1) MyCircle constructor with parameters x, y, radius - public MyCircle(int x, int y, int radius)
 *                  3.1.2) MyCircle constructor with parameters MyPoint, radius - public MyCircle(MyPoint centerPoint, int radius)
 *                  3.1.3) MyCircle setters and getters implementation
 *                      public void set(int x, int y, int radius)
 *                      public void set(MyPoint centerPoint, int radius)
 *                      public void set(MyCircle circle)
 *                      public MyPoint getCenter()
 *                      public int getRadius()
 *                      public MyCircle get()
 *                  3.1.4) public String toString() implementation printing [(x, y), r]
 *                  3.1.5) Implementation of a function that given a point verifies if it is located inside the circle
 *                      public boolean isPointInsideCircle(MyPoint point)
 *                  3.1.6) Implementation of a intersects function that given another circle, informs if the circles overlap
 *                      public boolean intersects(MyCircle otherCircle)
 *           4) Ex2.main additions
 *              4.1) Create 3 circles (mc1, mc2, mc3) and making mc1 and mc2 overlap, at the same time mc3 does not overlap
 *              4.2) Create point mp1 located inside mc1 and verify and print if it is located inside the circle
 *              4.3) Create point mp2 located outside mc2 and verify and print if it is located inside the circle
 *              4.4) Verify and print if mc1 and mc2 overlap
 *              4.5) Verify and print if mc2 and mc3 overlap
 *              4.6) Print the names and ID of the group members
 *           5) Basic use of the graphic infrastrure
 *              5.1) Make changes to the existing code in order to improve the 'visual' experience
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class Ex2 {

        private JFrame frame;
        private GameCanvas canvas;

        private void loadCanvas() {
                // Create a frame window and set its name, size and behavior when clicking the X
                frame = new JFrame("My Screen");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 600);
                canvas = new GameCanvas();

                // Add the canvas to the frame and show it
                frame.getContentPane().add(canvas);
                frame.setVisible(true);
        }

        // --------------------------------------------------------------------------
        // -------------- This is where your code starts ---------------------------
        // --------------------------------------------------------------------------
        
        /**
         * printPointInsideCircleVerification method
         * 
         * @implNote printPointInsideCircleVerification() method takes the needed parameters in
         *           order to verify if a point is located inside a circle and print it in a
         *           standard way to the screen
         *
         * @param (MyCircle circle)     (Circle)
         * @param (MyPoint point)       (Point to be verified if it is inside the previous circle)
         * @return (true if the point is inside the circle, false otherwise)
         */
        private boolean printPointInsideCircleVerification(MyCircle circle, MyPoint point) {
                boolean isPointInsideCircle = circle.isPointInsideCircle(point);
                String  textForDisplay      = " is";

                if (false == isPointInsideCircle) {
                        textForDisplay      = " is NOT";
                }

                System.out.println("Point " + point + textForDisplay + " inside the circle " + circle);

                return isPointInsideCircle;
        }

        /**
         * printCirclesOverlappingVerification method
         * 
         * @implNote printCirclesOverlappingVerification() method takes two circles as parameters in
         *           order to verify they overlap and print it in a standard way to the screen
         *
         * @param (MyCircle firstCircle)    (First circle)
         * @param (MyCircle secondCircle)   (Second circle to be verified if it overlaps with the previous one)
         * @return (true if the point is inside the circle, false otherwise)
         */
        private boolean printCirclesOverlappingVerification(MyCircle firstCircle, MyCircle secondCircle) {
                boolean doBothCirclesOverlap    = firstCircle.intersects(secondCircle);
                String  textForDisplay          = " overlaps ";

                if (false == doBothCirclesOverlap) {
                        textForDisplay          = " does NOT overlap ";
                }

                System.out.println("Circle " + firstCircle + textForDisplay + "circle " + secondCircle);

                return doBothCirclesOverlap;
        }

        public enum CircleType {
            SMALL(5),   //   calls constructor with value 5
            MEDIUM(10), //   calls constructor with value 10
            LARGE(15)   //   calls constructor with value 15
            ; // semicolon needed when fields / methods follow

            private final int circleRadiusSize;
            private CircleType(int circleRadiusSize) {
            this.circleRadiusSize   = circleRadiusSize;
            }
        }

        /**
         * AddNewCircleToCanvas method
         * 
         * @implNote AddNewCircleToCanvas() method takes the needed info from calling
         *           method and prints
         *           to the canvas the circle in a standard way
         *
         * @param (Circle circleInCanva)    (Circle to be drawn)
         * @param (String string)           (Needed for the interface)
         * @param (int x)                   (x coordinate of circle center point)
         * @param (int y)                   (y coordinate of circle center point)
         * @param (int radius)               (circle radius)
         * @param ()                        (No parameters)
         */
        private void AddNewCircleToCanvas(Circle circleInCanvas, String string, int x, int y, CircleType circleType) {
                Color   circleColor;
                circleInCanvas  = new Circle(string, x, y, circleType.circleRadiusSize);
                circleInCanvas.setColor(Color.BLACK);
                circleInCanvas.setIsFilled(true);
                
                switch (circleType) {
                    case SMALL:
                    default: {
                        circleColor = Color.PINK;
                    }
                    break;

                    case MEDIUM: {
                        circleColor = Color.YELLOW;
                    }
                    break;

                    case LARGE: {
                        circleColor = Color.CYAN;
                    }
                    break;
                }

                circleInCanvas.setFillColor(circleColor);
                canvas.addShape(circleInCanvas);
        }

        /**
         * AddNewLineToCanvas method
         * 
         * @implNote AddNewLineToCanvas() method takes the needed info from calling
         *           method and prints
         *           to the canvas the line in a standard way
         *
         * @param (Line lineInCanva)    (Line to be drawn)
         * @param (String string)       (Needed for the interface)
         * @param (int x1)              (x coordinate of point 1 of the line)
         * @param (int y1)              (y coordinate of point 1 of the line)
         * @param (int x2)              (x coordinate of point 2 of the line)
         * @param (int y2)              (y coordinate of point 2 of the line)
         * @param ()                    (No parameters)
         */
        private void AddNewLineToCanvas(Line lineInCanvas, String string, int x1, int y1, int x2, int y2) {
                lineInCanvas    = new Line(string, x1, y1, x2, y2);
                lineInCanvas.setColor(Color.BLACK);
                lineInCanvas.setWeight(1);
                canvas.addShape(lineInCanvas);
        }

        // --------------------------------------------------------------------------
        // -------------- This is where your code ends -----------------------------
        // --------------------------------------------------------------------------

        public static void main(String[] args) {

                /*
                 * Create a canvas and load it - Do not touch !
                 */
                Ex2 ex2 = new Ex2();
                ex2.loadCanvas();
                GameCanvas canvas = ex2.canvas;

                // --------------------------------------------------------------------------
                // -------------- This is where your code starts ---------------------------
                // --------------------------------------------------------------------------

                // Remove the nulls and create 3 circles and 2 points (assume an area of 400 X
                // 400)

                // Circle1 definitions
                final int CIRCLE1_CENTER_POINT_X    = 320;
                final int CIRCLE1_CENTER_POINT_Y    = 70;
                final int CIRCLE1_RADIUS            = 50;

                // Circle2 definitions
                final int CIRCLE2_CENTER_POINT_X    = 290;
                final int CIRCLE2_CENTER_POINT_Y    = 160;
                final int CIRCLE2_RADIUS            = 105;

                // Circle3 definitions
                final int CIRCLE3_CENTER_POINT_X    = 95;
                final int CIRCLE3_CENTER_POINT_Y    = 300;
                final int CIRCLE3_RADIUS            = 80;

                // Point1 definition (this point is inside Circle1)
                final int POINT1_X_INSIDE_CIRCLE1   = CIRCLE1_CENTER_POINT_X - (CIRCLE1_RADIUS / 2);
                final int POINT1_Y_INSIDE_CIRCLE1   = CIRCLE1_CENTER_POINT_Y - (CIRCLE1_RADIUS / 2);

                // Point2 definition (this point is outside Circle2)
                final int POINT2_X_OUTSIDE_CIRCLE2  = CIRCLE2_CENTER_POINT_X + (CIRCLE2_RADIUS * 37 / 36);
                final int POINT2_Y_OUTSIDE_CIRCLE2  = CIRCLE2_CENTER_POINT_Y + (CIRCLE2_RADIUS * 76 / 75);

                MyPoint mp1CenterOfCircle           = new MyPoint(CIRCLE1_CENTER_POINT_X, CIRCLE1_CENTER_POINT_Y);
                MyPoint mp2CenterOfCircle           = new MyPoint(CIRCLE2_CENTER_POINT_X, CIRCLE2_CENTER_POINT_Y);
                MyPoint mp1                         = new MyPoint(POINT1_X_INSIDE_CIRCLE1, POINT1_Y_INSIDE_CIRCLE1);
                MyPoint mp2                         = new MyPoint(POINT2_X_OUTSIDE_CIRCLE2, POINT2_Y_OUTSIDE_CIRCLE2);
                MyCircle mc1                        = new MyCircle(mp1CenterOfCircle, CIRCLE1_RADIUS);
                MyCircle mc2                        = new MyCircle(mp2CenterOfCircle, CIRCLE2_RADIUS);
                MyCircle mc3                        = new MyCircle(CIRCLE3_CENTER_POINT_X, CIRCLE3_CENTER_POINT_Y, CIRCLE3_RADIUS);

                // Fill in your details
                String student1                     = "Yuval Shechter" + ", " + "0-3170874-6";
                String student2                     = "Yossi Huttner" + ", " + "0-1183208-6";
                String student3                     = "Pablo Daniel Jelsky" + ", " + "3-2093823-6";

                // Write here the code that performs all the checks and prints the results
                ex2.printPointInsideCircleVerification(mc1, mp1);
                ex2.printPointInsideCircleVerification(mc2, mp2);
                ex2.printCirclesOverlappingVerification(mc1, mc2);
                ex2.printCirclesOverlappingVerification(mc2, mc3);

                // Fill the no intersecting circle
                Circle noIntersectingCircle         = new Circle("circle3", 
                                                            mc3.getCenter().getX(), 
                                                            mc3.getCenter().getY(),
                                                            mc3.getRadius());
                noIntersectingCircle.setColor(Color.RED);
                noIntersectingCircle.setIsFilled(true);
                noIntersectingCircle.setFillColor(Color.LIGHT_GRAY);
                canvas.addShape(noIntersectingCircle);

                // Visualize a line between the no intersecting circles
                Line lineBetweenCentersOfCircle1AndCircle3 = new Line("line1_3",
                                                                        mc1.getCenter().getX(), 
                                                                        mc1.getCenter().getY(),
                                                                        mc3.getCenter().getX(), 
                                                                        mc3.getCenter().getY());
                lineBetweenCentersOfCircle1AndCircle3.setColor(Color.MAGENTA);
                canvas.addShape(lineBetweenCentersOfCircle1AndCircle3);

                Line lineBetweenCentersOfCircle2AndCircle3 = new Line("line2_3",
                                                                    mc2.getCenter().getX(), 
                                                                    mc2.getCenter().getY(),
                                                                    mc3.getCenter().getX(), 
                                                                    mc3.getCenter().getY());
                lineBetweenCentersOfCircle2AndCircle3.setColor(Color.ORANGE);
                canvas.addShape(lineBetweenCentersOfCircle2AndCircle3);

                // Corner circles definitions
                final int UPPER_LEFT_CORNER_POINT_X     = 1;
                final int UPPER_LEFT_CORNER_POINT_Y     = 1;
                final int UPPER_RIGHT_CORNER_POINT_X    = 400;
                final int UPPER_RIGHT_CORNER_POINT_Y    = 1;
                final int LOWER_LEFT_CORNER_POINT_X     = 1;
                final int LOWER_LEFT_CORNER_POINT_Y     = 400;
                final int LOWER_RIGHT_CORNER_POINT_X    = 400;
                final int LOWER_RIGHT_CORNER_POINT_Y    = 400;


                // Drawing the 4 circles closing the perimeter defined by 400 x 400 pixels
               Circle largeCircleUpperLeftCircle = null;
                ex2.AddNewCircleToCanvas(largeCircleUpperLeftCircle, "LargeUpperLeftCircle",
                                UPPER_LEFT_CORNER_POINT_X, UPPER_LEFT_CORNER_POINT_Y, CircleType.LARGE);
                Circle largeCircleUpperRightCircle = null;
                ex2.AddNewCircleToCanvas(largeCircleUpperRightCircle, "LargeUpperRightCircle",
                                UPPER_RIGHT_CORNER_POINT_X, UPPER_RIGHT_CORNER_POINT_Y, CircleType.LARGE);
                Circle largeCircleLowerLeftCircle = null;
                ex2.AddNewCircleToCanvas(largeCircleLowerLeftCircle, "LargeLowerLeftCircle",
                                LOWER_LEFT_CORNER_POINT_X, LOWER_LEFT_CORNER_POINT_Y, CircleType.LARGE);
                Circle largeCircleLowerRightCircle = null;
                ex2.AddNewCircleToCanvas(largeCircleLowerRightCircle, "LargeLowerRightCircle",
                                LOWER_RIGHT_CORNER_POINT_X, LOWER_RIGHT_CORNER_POINT_Y, CircleType.LARGE);

                Circle smallCircleUpperLeftCircle = null;
                ex2.AddNewCircleToCanvas(smallCircleUpperLeftCircle, "UpperLeftCircle",
                                UPPER_LEFT_CORNER_POINT_X, UPPER_LEFT_CORNER_POINT_Y, CircleType.SMALL);
                Circle smallCircleUpperRightCircle = null;
                ex2.AddNewCircleToCanvas(smallCircleUpperRightCircle, "UpperRightCircle",
                                UPPER_RIGHT_CORNER_POINT_X, UPPER_RIGHT_CORNER_POINT_Y, CircleType.SMALL);
                Circle smallCircleLowerLeftCircle = null;
                ex2.AddNewCircleToCanvas(smallCircleLowerLeftCircle, "LowerLeftCircle",
                                LOWER_LEFT_CORNER_POINT_X, LOWER_LEFT_CORNER_POINT_Y, CircleType.SMALL);
                Circle smallCircleLowerRightCircle = null;
                ex2.AddNewCircleToCanvas(smallCircleLowerRightCircle, "LowerRightCircle",
                                LOWER_RIGHT_CORNER_POINT_X, LOWER_RIGHT_CORNER_POINT_Y, CircleType.SMALL);

                // Drawing the 4 lines closing the perimeter defined by 400 x 400 pixels
                Line upperLine  = null;
                ex2.AddNewLineToCanvas(upperLine, "UpperLine",
                                UPPER_LEFT_CORNER_POINT_X, UPPER_LEFT_CORNER_POINT_Y,
                                UPPER_RIGHT_CORNER_POINT_X, UPPER_RIGHT_CORNER_POINT_Y);
                Line leftLine   = null;
                ex2.AddNewLineToCanvas(leftLine, "LeftLine",
                                UPPER_LEFT_CORNER_POINT_X, UPPER_LEFT_CORNER_POINT_Y,
                                LOWER_LEFT_CORNER_POINT_X, LOWER_LEFT_CORNER_POINT_Y);
                Line rightLine  = null;
                ex2.AddNewLineToCanvas(rightLine, "RightLine",
                                UPPER_RIGHT_CORNER_POINT_X, UPPER_RIGHT_CORNER_POINT_Y,
                                LOWER_RIGHT_CORNER_POINT_X, LOWER_RIGHT_CORNER_POINT_Y);
                Line lowerLine  = null;
                ex2.AddNewLineToCanvas(lowerLine, "LowerLine",
                                LOWER_RIGHT_CORNER_POINT_X, LOWER_RIGHT_CORNER_POINT_Y,
                                LOWER_LEFT_CORNER_POINT_X, LOWER_LEFT_CORNER_POINT_Y);

                // --------------------------------------------------------------------------
                // -------------- This is where your code ends -----------------------------
                // --------------------------------------------------------------------------

                // The following code visualizes your shapes using a part of the game
                // infrastructure
                // You have to read, understand and modify the visual representation of the
                // elements ...

                // Visualize your circles with the infrastructure circle shapes
                Circle c1 = new Circle("c1", mc1.getCenter().getX(), mc1.getCenter().getY(), mc1.getRadius());
                Circle c2 = new Circle("c2", mc2.getCenter().getX(), mc2.getCenter().getY(), mc2.getRadius());
                Circle c3 = new Circle("c3", mc3.getCenter().getX(), mc3.getCenter().getY(), mc3.getRadius());

                // Visualize the points with red filled circles
                Circle p1 = new Circle("p1", mp1.getX(), mp1.getY(), 3);
                p1.setColor(Color.RED);
                p1.setIsFilled(true);
                Circle p2 = new Circle("p2", mp2.getX(), mp2.getY(), 3);
                p2.setColor(Color.RED);
                p2.setIsFilled(true);

                // Visualize a line between the two intersecting circles
                Line l1 = new Line("l1", c1.getPosX(), c1.getPosY(), c2.getPosX(), c2.getPosY());
                l1.setColor(Color.green);

                // Add all the shapes to the canvas
                canvas.addShape(c1);
                canvas.addShape(c2);
                canvas.addShape(c3);
                canvas.addShape(p1);
                canvas.addShape(p2);
                canvas.addShape(l1);

                // Texts are also shapes. Show your details as texts at the bottom of the
                // screen.
                canvas.addShape(new Text("st1", student1, 50, 400));
                canvas.addShape(new Text("st2", student2, 50, 450));
                canvas.addShape(new Text("st3", student3, 50, 500));
        }
}