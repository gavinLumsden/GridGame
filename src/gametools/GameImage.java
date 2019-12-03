package gametools;

import collections.LinkedList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * GameImage.java - represents a box to display an image in a container for a
 * user interface and useful methods
 *
 * @author g.lumsden
 * @since 14-May-2019
 */
public class GameImage {

    private LinkedList<Animation> animations;
    private int currentIndex;

    private JLabel label;
    private String imageFile;
    private URL url; 
    private ImageIcon icon;

    /**
     * Constructor for the class, sets class properties
     *
     * @param label the label used to display the image
     */
    public GameImage(JLabel label) {
        this(label, "");               // set debug mode
    }

    /**
     * Constructor for the class, sets class properties
     *
     * @param label the label used to display the image
     * @param background the background color of the label
     */
    public GameImage(JLabel label, Color background) {
        this(label, "", background);
    }

    /**
     * Constructor for the class, sets class properties
     *
     * @param label the label used to display the image
     * @param text the text inside the label
     * @param background the background color of the label
     */
    public GameImage(JLabel label, String text, Color background) {
        this.label = label;                         // set parameter to property
        setDebug(text, background);                  // set debug mode
    }

    /**
     * Constructor for the class, sets class properties
     *
     * @param label the label used to display the image
     * @param imageFile the relative image filename to display
     */
    public GameImage(JLabel label, String imageFile) {
        this.label = label;                         // set parameter to property
        setImage(imageFile);                        // set image
    }
    
    private void setLabelIcon() {
        try {
            this.url = new URL(imageFile);      // set property to parameter
            if (this.url == null) icon = new ImageIcon(imageFile);        // set icon
            else                  icon = new ImageIcon(url);        // set icon
            label.setIcon(icon);                // set icon to label
        } catch (MalformedURLException ex) {
            System.out.println("url error");
        } 
    }
    

    /**
     * Shows (makes visible) the GameImage in the container
     */
    public void show() {
        if (imageFile != null) {
            setLabelIcon();
        }
        label.setVisible(true);                     // make label visible
        run();
    }

    /**
     * Hides (makes invisible) the GameImage in the container
     */
    public void hide() {
        label.setIcon(null);                        // removes any image
        label.setVisible(false);                    // make label invisible
        stop();
    }

    /**
     * Resizes the image for the GameImage
     *
     * @param width the new width to set to
     * @param height the new height to set to
     */
    public void resize(int width, int height) {
        label.setSize(width, height);                   // resize label
        if (animations == null) {
            return;                 // error trap
        }
        for (int i = 0; i < animations.size(); i++) {   // traverse animations
            animations.get(i).resize(width, height);     // resize each animation
        }
        resizeToContainer();                            // resize images
    }

    /**
     * Resizes the image inside the label to match the size of the label
     */
    public void resizeToContainer() {
        int width = label.getWidth();     // get label width
        int height = label.getHeight();    // get label height
        ImageIcon originalIcon = (ImageIcon) label.getIcon();   // get icon
        if (originalIcon == null) {
            return;               // error trap
        }
        Image originalImage = originalIcon.getImage();      // get image
        Image newImage = originalImage.getScaledInstance(
                width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);  // set new image
        if (animations == null) {
            return;                 // error trap
        }
        for (int i = 0; i < animations.size(); i++) {   // traverse animations
            animations.get(i).resizeToContainer();      // resize images
        }
    }

    public void setImage(String imageFile) {
        if (!imageFile.equals("")) {  // checking to see if there is an image assosiated with this game image
            this.imageFile = imageFile;
            setLabelIcon();
        }            
        label.setBorder(null);                  // remove border
        label.setOpaque(false);                 // remove background color
        show();                                 // display picturebox 
    }

    /**
     * Sets the image to debug mode, meaning it removes any graphics and changes
     * to a colored rectangle with text
     *
     * @param text the text to display in the image
     * @param background the background color of the image
     */
    public void setDebug(String text, Color background) {
        setColor(background);                                   // set color
        label.setBorder(BorderFactory.createEtchedBorder());    // set border
        label.setHorizontalAlignment(SwingConstants.CENTER);    // set align
        label.setFont(new Font("Arial Narrow", Font.BOLD, 8));    // set font
        label.setText(text);                                    // set text
    }

    /**
     * Sets the image to debug mode, meaning it removes any graphics and changes
     * to a colored rectangle with text
     *
     * @param text the text to display in the image
     */
    public void setDebug(String text) {
        label.setBorder(BorderFactory.createEtchedBorder());    // set border
        label.setHorizontalAlignment(SwingConstants.CENTER);    // set align
        label.setFont(new Font("Arial Narrow", Font.BOLD, 8));    // set font
        label.setText(text);                                    // set text
    }

    /**
     * Sets the background color of the GameImage to the passed color and the
     * foreground color of the GameImage to the opposite color
     *
     * @param color the background color to set to
     */
    public void setColor(Color color) {
        label.setBorder(null);                  // remove any border
        label.setIcon(null);                    // remove any icon
        label.setOpaque(true);                  // allow background color
        label.setBackground(color);             // set background color
        label.setForeground(invert(color));     // set foreground to opposite
        label.setText("");                      // remove any text
        stop();
    }

    /**
     * Inverts (creates an opposite color) directly opposite the passed color on
     * the color wheel
     *
     * @param color the color to invert
     * @return an opposite (inverted) color
     */
    private Color invert(Color color) {
        if (color == null) {
            return null;
        }
        int r = color.getRed();         // get red value of original color
        int g = color.getGreen();       // get green value of original color
        int b = color.getBlue();        // get blue value of original color
        r = 255 - r;                    // calculate opposite red value
        g = 255 - g;                    // calculate opposite green value
        b = 255 - b;                    // calculate opposite blue value
        return new Color(r, g, b);        // return new color
    }

    /**
     * Sets the game image to be an "invisible" but active game object
     */
    public void setClear() {
        label.setBorder(null);          // remove any border
        label.setIcon(null);            // remove any icon
        label.setOpaque(false);         // no background color
        label.setBackground(null);      // no background color
        label.setForeground(null);      // no foreground 
        label.setText("");              // no text
    }

    /**
     * sets a label
     *
     * @param label
     */
    public void setLabel(JLabel label) {
        this.label = label;
    }

    /**
     * removes the border of a label
     */
    public void removeBorder() {
        label.setBorder(null);          // remove any border
    }

    /**
     * gets the image file
     *
     * @return
     */
    public String getImageFile() {
        return imageFile;
    }

    /**
     * sets a labels background
     *
     * @param r
     * @param g
     * @param b
     */
    public void setBackground(int r, int g, int b) {
        Color colour = new Color(r, g, b);
        label.setBackground(colour);
        label.setOpaque(true);
    }

    /**
     * sets the location of a game image
     *
     * @param x
     * @param y
     */
    public void setLocation(int x, int y) {
        label.setLocation(x, y);
    }

     //////////////////////////////
    

    /**
     * Sets the animations from the passed data
     *
     * @param animations the animation objects for this game image
     */
    public void setAnimations(LinkedList<Animation> animations) {
        this.animations = animations;                   // assign to property
        for (int i = 0; i < animations.size(); i++) {   // traverse array
            animations.get(i).stop();                   // stop each animation
        }
        run();                                          // run first animation
    }

    /**
     * Animate the passed index value
     *
     * @param index the index of the animation to run
     */
    public void animate(int index) {
        animations.get(currentIndex).stop();       // stop current animation
        run(index);                         // run passed animation index
        currentIndex = index;               // remember passed index
    }

    /**
     * Stops all animations
     */
    public void stop() {
        if (animations == null) {
            return;                 // error trap
        }
        for (int i = 0; i < animations.size(); i++) {   // traverse animations
            stop(i);                                    // stop animation
        }
    }

    /**
     * Stops the passed animation
     *
     * @param index the animation index to stop
     */
    public void stop(int index) {
        if (animations == null) {
            return;                 // error trap
        }
        animations.get(index).stop();                   // stop animation
    }

    /**
     * Runs the passed animation
     *
     * @param index the animation index to run
     */
    public void run(int index) {
        if (animations == null) {
            return;                 // error trap
        }
        animations.get(currentIndex).stop();
        if (animations.get(index).isRunning() == false) {
            animations.get(index).run();
        }                    // run animation
        currentIndex = index;
    }

    /**
     * Runs the first animation
     */
    public void run() {
        if (animations == null) {
            return;                     // error trap
        }
        if (animations.get(0).isRunning() == false) {
            run(0); // run first animation
        }
    }

    /**
     * Restarts the passed animation back to the first frame
     *
     * @param index the animation to restart
     */
    public void restart(int index) {
        animations.get(index).restart();
    }

    /**
     * Restarts all animations back to the first frame
     */
    public void restart() {
        stop();                                 // stops all animations
        restart(0);                             // restarts first animation
    }

    /**
     * Sets the passed animation to loop (repeat from the last frame back to the
     * first frame) or not
     *
     * @param index the animation to set the loop to
     * @param shouldLoop should the animation loop (true) or not (false)
     */
    public void setLoop(int index, boolean shouldLoop) {
        animations.get(index).setLoop(shouldLoop);
    }

    /**
     * Sets all animations to loop (repeat from the last frame back to the first
     * frame) or not
     *
     * @param shouldLoop should all animations loop (true) or not (false)
     */
    public void setLoop(boolean shouldLoop) {
        for (int i = 0; i < animations.size(); i++) {   // traverse animations
            setLoop(i, shouldLoop);                      // set animation loop
        }
    }

    /**
     * Set the delay (in milliseconds) for the passed animation
     *
     * @param index the animation to set the loop to
     * @param delay the delay (in milliseconds) for the passed animation
     */
    public void setDelay(int index, int delay) {
        animations.get(index).setDelay(delay);
    }

    /**
     * Set all delays (in milliseconds) for all animations
     *
     * @param delay the delay (in milliseconds) for all animations
     */
    public void setDelay(int delay) {
        for (int i = 0; i < animations.size(); i++) {   // traverse animations
            setDelay(i, delay);                          // set animation delay
        }
    }

    /**
     * Sets all the frame image files for the passed animation
     *
     * @param index the animation to set the image files to
     * @param imageFiles the array of relative image file names
     */
    private void setImageFiles(int index, LinkedList<String> imageFiles) {
        animations.get(index).setImageFiles(imageFiles);
    }

    /**
     * Gets the frames per second (FPS) for the passed animation
     *
     * @param index the animation to get the FPS from
     * @return the frames per second (FPS) for the passed animation
     */
    public int getFPS(int index) {
        return animations.get(index).getFPS();
    }

    /**
     * Determines if the passed animation is running (true) or not (false)
     *
     * @param index the animation to determine if running
     * @return the passed animation is running (true) or not (false)
     */
    public boolean isRunning(int index) {
        return animations.get(index).isRunning();
    }
 
    /**
     * Update the coordinates of the GameImage current location data
     *
     * @param coordinates the coordinates object to update
     */
    public void update(Coordinates coordinates) {
        if (coordinates == null) {
            coordinates = new Coordinates();
        }
        coordinates.x = label.getX();
        coordinates.y = label.getY();
        coordinates.width = label.getWidth();
        coordinates.height = label.getHeight();
        coordinates.recalculate();
    }
    
    /**
     * Re-positions GameImage in it's container based on coordinate data
     *
     * @param coordinates the coordinates object to re-position to
     */
    public void redraw(Coordinates coordinates) {
        if (coordinates == null) {
            return;            // error trap
        }
        int x = coordinates.x;
        int y = coordinates.y;
        int w = coordinates.width;
        int h = coordinates.height;
        label.setBounds(x, y, w, h);
    }
    
}
