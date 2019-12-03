package gametools;

import javax.swing.JLabel;

/**
 * GameObject.java - represents a typical object in a game
 *
 * @author g.lumsden
 * @since 14-May-2019
 */
public class GameObject {

    public Coordinates coordinates;
    public GameImage   gameImage; 
    public Mover       mover; 

    public GameObject(JLabel image,
            int amount,
            int direction,
            int numberOfDirections) {
        gameImage   = new GameImage(image); 
        coordinates = new Coordinates(amount, direction);
        mover       = new Mover(coordinates, numberOfDirections); 
        spawn();
    }

    /**
     * Updates the current location of the coordinates for the image
     */
    public void update() {
        gameImage.update(coordinates);
    }

    /**
     * Re-positions image in it's container based on game character's data
     */
    public void redraw() {
        gameImage.redraw(coordinates);
    }

    /**
     * Spawns the game object, makes it alive and visible
     */
    public void spawn() {
        gameImage.show();
    }

    /**
     * De-spawns the game object, makes it not alive and invisible
     */
    public void despawn() {
        gameImage.hide();
    }

}
