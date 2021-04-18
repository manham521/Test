// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2021T1, Assignment 3
 * Name: Alex Guerin
 * Username: guerinalex1
 * ID: 300571105
 */

import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;

/** Paramterised Shapes: draw tricolour flags and game boards */
public class ParameterisedShapes {

    //Constants for CORE  (three strip flags)
    public static final double FLAG_WIDTH = 200;
    public static final double FLAG_HEIGHT = 133;

    //Constants for COMPLETION
    public static final double BOARD_LEFT = 15;  // Left side of each row
    public static final double BOARD_TOP = 15;   // Top of the first row
    public static final double ROW_SIZE = 40;    // Height of each row.
    public static final double DISH_WIDTH = ROW_SIZE - 4;      // Size of the dishes
    public static final double DISH_HEIGHT = DISH_WIDTH - 10;
    public static final double PEBBLE_DIAM = 10; // Size of the pebbles

    /**
     * CORE
     * asks user for a position and three colours, then calls the
     * drawTriColorFlag method, passing the appropriate arguments
     */
    public void doCore() {
        double left = UI.askDouble("Left of flag");
        double top = UI.askDouble("Top of flag");
        boolean horiz = UI.askBoolean("Are the stripes horizontal?");
        UI.println("Now choose the colours");
        Color stripe1 = JColorChooser.showDialog(null, "First Stripe", Color.white);
        Color stripe2 = JColorChooser.showDialog(null, "Second Stripe", Color.white);
        Color stripe3 = JColorChooser.showDialog(null, "Third Stripe", Color.white);
        this.drawThreeStripeFlag(left, top, stripe1, stripe2, stripe3,horiz);
    }



    /**
     * CORE
     * draws a three colour flag at the given position consisting of
     * three equal size stripes of the given colors
     * The stripes are horizontal or vertical
     * The size of the flag is specified by the constants FLAG_WIDTH and FLAG_HEIGHT
     */
    public void drawThreeStripeFlag(double left, double top, Color stripe1, Color stripe2, Color stripe3, boolean horiz) {
        UI.clearGraphics();
        double width = FLAG_WIDTH;
        double height = FLAG_HEIGHT;

        if(horiz == false){
        UI.setColor(stripe1); /** This displays the first stripe of the flag, and the colour of the users choice*/
        UI.fillRect(left, top, width, height);

        UI.setColor(stripe2); /** This displays the second stripe of the flag, and the colour of the users choice*/
        UI.fillRect(left + width / 3, top, width / 3, height);

        UI.setColor(stripe3); /** This displays the third stripe of the flag, and the colour of the users choice*/
        UI.fillRect(left + (width / 3) * 2, top, width / 3, height);}

        if(horiz == true){
        UI.setColor(stripe1); /** This displays the first stripe of the flag, and the colour of the users choice*/
        UI.fillRect(left, top, width, height);

        UI.setColor(stripe2); /** This displays the second stripe of the flag, and the colour of the users choice*/
        UI.fillRect(left , top+(height/3.0), width, height*(1.0/3.0));

        UI.setColor(stripe3); /** This displays the third stripe of the flag, and the colour of the users choice*/
        UI.fillRect(left , top+(height*(2.0/3.0)), width, height*(1.0/3.0));}

    }

    /**   COMPLETION
     * Draws a pebble game board with five rows of increasing size
     *   The first row has 6 dishes, the second has 7 dishes, the third has 8, etc.
     *   The positions of the red and blue pebbles are shown in this table:
     *   (where the |'s separate the dishes)
     *     |   | r |   |   |   | b |
     *     |   | b | r |   |   |   |   |
     *     |   |   |   |   |   | r |   | b |
     *     | b |   |   | r |   |   |   |   |   |
     *     |   |   | b |   |   |   |   |   |   | r |
     *
     *  It uses the drawPebbleRow method which draws one row and the two pebbles in it.
     */
    public void doCompletion(){
        UI.clearGraphics();
        this.drawPebbleRow(1,6,6);
        this.drawPebbleRow(2,7,7);
        this.drawPebbleRow(3,8,8);
        this.drawPebbleRow(4,9,9);
        this.drawPebbleRow(5,10,10);
    }

    /**   COMPLETION
     * Draws a row of a pebble game. Parameters must be sufficient to specify
     * the position and size of the row, and which dishes the red and blue pebbles are in.
     * Hint: use the drawRowOutline, drawDish and drawPebble methods!
     */
    public void drawPebbleRow(int rowNum, int numDishes, int dishNum) {
        for(int i = 0; i < dishNum; i++){
            this.drawDish(rowNum, i);
        }
        this.drawRowOutline(rowNum,  numDishes);
        if(dishNum == 6){
            this.drawPebble(rowNum,dishNum-5,Color.red);}
        if(dishNum == 6){
            this.drawPebble(rowNum,dishNum-1,Color.blue);}
        if(dishNum == 7){
            this.drawPebble(rowNum,dishNum-5,Color.blue);}
        if(dishNum == 7){
            this.drawPebble(rowNum,dishNum-6,Color.red);}
        if(dishNum == 8){
            this.drawPebble(rowNum,dishNum-1,Color.blue);}
        if(dishNum == 8){
            this.drawPebble(rowNum,dishNum-3,Color.red);}
        if(dishNum == 9){
            this.drawPebble(rowNum,dishNum-6,Color.red);}
        if(dishNum == 9){
            this.drawPebble(rowNum,dishNum-9,Color.blue);}
        if(dishNum == 10){
            this.drawPebble(rowNum,dishNum-1,Color.red);}
        if(dishNum == 10){
            this.drawPebble(rowNum,dishNum-8,Color.blue);}

    }

    /**
     * Draws the outline of the specified row with the specified number of dishes.
     * (rows numbered from 0)
     */
    public void drawRowOutline(int rowNum, int numDishes){
        UI.drawRect(BOARD_LEFT, BOARD_TOP + rowNum * ROW_SIZE, ROW_SIZE*numDishes, ROW_SIZE);
    }

    /**
     * Draw the specified dish in the specified row
     * (rows and dishes are numbered from 0)
     */
    public void drawDish(int rowNum, int dishNum){
        double dishLeft = BOARD_LEFT+dishNum*ROW_SIZE + ROW_SIZE/2 - DISH_WIDTH/2 ;
        double dishTop = BOARD_TOP + rowNum * ROW_SIZE + ROW_SIZE/2 - DISH_HEIGHT/2;
        UI.setColor(new Color(230, 230, 230));   // very light grey
        UI.fillOval(dishLeft, dishTop, DISH_WIDTH, DISH_HEIGHT);
        UI.setColor(Color.black);
        UI.drawOval(dishLeft, dishTop, DISH_WIDTH, DISH_HEIGHT);
    }

    /**
     * Draw a pebble in specified dish in the specified row with the specified color.
     * (rows and dishes are numbered from 0)
     */
    public void drawPebble(int rowNum, int dishNum, Color pebbleColor){
        double pebbleTop = BOARD_TOP + rowNum * ROW_SIZE + ROW_SIZE/2 - PEBBLE_DIAM/2;
        double pebbleLeft = BOARD_LEFT+dishNum*ROW_SIZE + ROW_SIZE/2 - PEBBLE_DIAM/2;
        UI.setColor(pebbleColor);
        UI.fillOval(pebbleLeft, pebbleTop, PEBBLE_DIAM, PEBBLE_DIAM);
    }


    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes );
        UI.addButton("Core", this::doCore );
        UI.addButton("Completion", this::doCompletion );
        UI.addButton("Quit", UI::quit );
    }

    public static void main(String[] args){
        ParameterisedShapes ps = new ParameterisedShapes ();
        ps.setupGUI();
    }

}
