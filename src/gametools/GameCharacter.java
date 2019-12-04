package gametools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * GameCharacter.java - representation of a character in a game
 *
 * @author g.lumsden
 * @since 14-May-2019
 */
public abstract class GameCharacter extends GameObject {

    /**
     * Controls the user input actions for this game character
     */
    public UserInput input;
    /**
     * Timer times the actions of this game character
     */
    public Timer timer;

    public final int WALK_DELAY = 800;
    public final int IDLE_DELAY = 100;

    private int directionToStop;
    

    /**
     * Constructor for the class, sets class property data
     *
     * @param image the label associated with the image for the game character
     * @param amount the amount the game character will move
     * @param direction the direction the game character will move
     * @param delay the delay in milliseconds of the character's timer
     * @param numberOfDirections the number of directions defined
     */
    public GameCharacter(JLabel image,
            int amount,
            int direction,
            int numberOfDirections,
            int delay) {
        super(image, amount, direction, numberOfDirections);
        input = new UserInput(super.coordinates, numberOfDirections);
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });
        timer.start();
    }

    /**
     * The abstract action this character does in it's timer
     */
    public abstract void action();

    /**
     * The user's keyboard event of pressing a key to respond to
     *
     * @param event the keyboard event registered
     */
    public void keyPress(KeyEvent event) {
        input.keypress(event);
    }

    /**
     * "Animates" a sprite
     */
    public void animate() {
        if (coordinates.direction == Directions.UP) {
            if (sprite.isRunning(0) == false) {
                directionToStop = Directions.UP; 
                sprite.run(0);
            }
            directionToStop = Directions.UP;
        } else if (coordinates.direction == Directions.DOWN) {
            if (sprite.isRunning(1) == false) {
                directionToStop = Directions.DOWN; 
                sprite.run(1);
            }
            directionToStop = Directions.DOWN;
        } else if (coordinates.direction == Directions.LEFT) {
            if (sprite.isRunning(2) == false) {
                directionToStop = Directions.LEFT; 
                sprite.run(2);
            }
            directionToStop = Directions.LEFT;
        } else if (coordinates.direction == Directions.RIGHT) {
            if (sprite.isRunning(3) == false) {
                directionToStop = Directions.RIGHT; 
                sprite.run(3);
            }
            directionToStop = Directions.RIGHT;
        } else if (coordinates.direction == Directions.STOP) {
            if (directionToStop == Directions.UP) {
                sprite.run(4);
            } else if (directionToStop == Directions.DOWN) {
                sprite.run(5);
            } else if (directionToStop == Directions.LEFT) {
                sprite.run(6);
            } else if (directionToStop == Directions.RIGHT) {
                sprite.run(7);
            }
        }
    }
    
}
