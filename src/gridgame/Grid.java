package gridgame; 

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class Grid extends JFrame
{
    
    private final double SCALE_FACTOR = 0.045;
        
    private int frameWidth;
    private int frameHeight;    
    private int tileWidth;
    private int tileHeight;
    private int rows;
    private int columns;

    private Location[][] locations;
    private Hero         hero;
    private Enemy        enemy; 
    
    public Grid() {
        setDataStructures();
        setFrame();                
        setActions();
        setVisible(true);
    }

    private void setDataStructures() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frameWidth  = (int)screenSize.getWidth();
        frameHeight = (int)screenSize.getHeight();  
        tileWidth   = (int)((double)frameWidth * SCALE_FACTOR);
        tileHeight  = tileWidth;
        rows        = frameHeight / tileHeight;
        columns     = frameWidth  / tileWidth;
        setTiles();
        enemy       = new Enemy(locations, this); 
        hero        = new Hero(locations, this);
    }
    
    private void setFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setUndecorated(true);
        setBackground(Types.BACKGROUND);
        getContentPane().setBackground(Types.BACKGROUND);        
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
    }
    
    private void setTiles() {
        locations = new Location[rows][columns];
        int y = 0;  
        for (int row = 0; row < rows; row++) {
            int x = 0;
            for (int column = 0; column < columns; column++) {
                locations[row][column] = new Location(row,column,x,y,
                        tileWidth,tileHeight,Types.GROUND,this);                
                x += tileWidth;
            }
            y += tileHeight;
        }
    }

    private void setActions() {
        this.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {  } 
            public void keyPressed(KeyEvent e) {                 
                hero.keyPress(e);
            } 
            public void keyReleased(KeyEvent e) { }
        });
    }

}