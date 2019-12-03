package gridgame; 

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class Grid extends JFrame
{
    
    private final double SCALE_FACTOR = 0.045;
        
    public int frameWidth;
    public int frameHeight;    
    public int tileWidth;
    public int tileHeight;
    private int rows;
    private int columns;

    private Location[][] locations;
    private Boundary     boundary; 
    private Hero         hero;
    
    public Grid() {
        setDataStructures();
        setFrame();                
        setActions();
        trim(); 
        hero.setLocation(); 
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
        boundary = new Boundary(locations);
        House house = new House(locations, this); 
        Enemy enemy = new Enemy(locations, this); 
        hero        = new Hero(locations, boundary, this, locations[(locations.length/2)][(locations[0].length/2)].tile.tile);
    }
    
    private void setFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setUndecorated(true);
        setBackground(Types.BACKGROUND);
        getContentPane().setBackground(Types.BACKGROUND);        
        setSize(frameWidth, frameHeight);
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

    public void trim() {
        frameHeight -= (frameHeight - (locations.length * tileHeight));
        frameWidth -= (frameWidth - (locations[0].length * tileWidth));
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null);
    }
    
}