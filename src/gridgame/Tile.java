package gridgame; 

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tile 
{
    
    public JLabel    tile;
    public int       width;
    public int       height;
    public URL       url; 
    public ImageIcon icon; 
    
    public Tile(int width, int height, int x, int y, JFrame frame) {
        tile = new JLabel();
        frame.getContentPane().add(tile);
        tile.setBorder(Types.TILE_BORDER);
        tile.setBounds(x, y, width, height);
        tile.setOpaque(true);
    }

    public void setColor(Color color) {
        tile.setBackground(color);
        tile.setBorder(null);                  // remove border
        tile.setOpaque(false);                 // remove background color
        show(null);                                 // display picturebox 
    }
    
    public void setImage(String imageFile) {
        if (!imageFile.equals("")) {  // checking to see if there is an image assosiated with this game image
            setLabelIcon(imageFile);
        }            
        tile.setBorder(null);                  // remove border
        tile.setOpaque(false);                 // remove background color
        show(imageFile);                                 // display picturebox 
    }

    private void show(String imageFile) {
        if (imageFile != null) {
            setLabelIcon(imageFile);
        }
        tile.setVisible(true);                     // make label visible
    }
    
    private void setLabelIcon(String imageFile) {
        try {
            this.url = new URL(imageFile);      // set property to parameter
            if (this.url == null) icon = new ImageIcon(imageFile);        // set icon
            else                  icon = new ImageIcon(url);        // set icon
            tile.setIcon(icon);                // set icon to label
        } catch (MalformedURLException ex) {
            System.out.println("url error");
        } 
    }
    
}