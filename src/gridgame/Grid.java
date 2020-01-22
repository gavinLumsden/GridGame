package gridgame; 

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Grid extends JFrame
{
    
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
        System.out.println("creating grid");
        setDataStructures();
        setTiles();
        setFrame();    
        setHero(); 
        setActions();
        trim();  
        System.out.println("grid created");
        this.setVisible(true);
        System.out.println("iytoyu");
    }
    
    private void setDataStructures() {
        System.out.println("settings data structures");
        frameWidth           = Map.map.length;
        frameHeight          = Map.map[0].length;
        tileWidth            = 80;
        tileHeight           = 80;
        rows                 = Map.map.length;
        columns              = Map.map[0].length;
        System.out.println("data structures set");
    }
    
    private void setFrame() {
        System.out.println("setting frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setUndecorated(true);
        setBackground(Types.BACKGROUND);
        getContentPane().setBackground(Types.BACKGROUND);        
        setSize(frameWidth, frameHeight);
        System.out.println("frame set");
    }
    
    private void setTiles() {
        System.out.println("setting tiles");
        locations = new Location[rows][columns];
        
        Chunk1 chunk1 = new Chunk1(locations, this); 
        Chunk2 chunk2 = new Chunk2(locations, this); 
        Chunk3 chunk3 = new Chunk3(locations, this); 
        Chunk4 chunk4 = new Chunk4(locations, this); 
        
        chunk1.thread.start(); 
        chunk2.thread.start(); 
        chunk3.thread.start(); 
        chunk4.thread.start(); 
        
        boolean done = false; 
        
        while(done == false) {
            if (chunk1.thread.isAlive() == false && chunk2.thread.isAlive() == false && chunk3.thread.isAlive() == false && chunk4.thread.isAlive() == false) {
                for (int r = chunk1.rowSizeLow; r <= chunk1.rowSizeHigh; r++) {
                    for (int c = chunk1.columnSizeLow; c <= chunk1.columnSizeHigh; c++) {
                        Location newLocation = chunk1.getLocation(r, c);
                        if (newLocation != null) locations[chunk1.rowSizeLow][chunk1.columnSizeLow] = newLocation; 
                        else System.out.println("Location: row: " + r + " column: " + c + " is a null");
                    }
                }
                for (int r = chunk2.rowSizeLow; r <= chunk2.rowSizeHigh; r++) {
                    for (int c = chunk2.columnSizeLow; c <= chunk2.columnSizeHigh; c++) {
                        Location newLocation = chunk2.getLocation(r, c);
                        if (newLocation != null) locations[chunk2.rowSizeLow][chunk2.columnSizeLow] = newLocation; 
                        else System.out.println("Location: row: " + r + " column: " + c + " is a null");
                    }
                }
                for (int r = chunk3.rowSizeLow; r <= chunk3.rowSizeHigh; r++) {
                    for (int c = chunk3.columnSizeLow; c <= chunk3.columnSizeHigh; c++) {
                        Location newLocation = chunk3.getLocation(r, c);
                        if (newLocation != null) locations[chunk3.rowSizeLow][chunk3.columnSizeLow] = newLocation; 
                        else System.out.println("Location: row: " + r + " column: " + c + " is a null");
                    }
                }
                for (int r = chunk4.rowSizeLow; r <= chunk4.rowSizeHigh; r++) {
                    for (int c = chunk4.columnSizeLow; c <= chunk4.columnSizeHigh; c++) {
                        Location newLocation = chunk4.getLocation(r, c);
                        if (newLocation != null) locations[chunk4.rowSizeLow][chunk4.columnSizeLow] = newLocation; 
                        else System.out.println("Location: row: " + r + " column: " + c + " is a null");
                    }
                }
                for (int r = 0; r < locations.length; r++) {
                    for (int c = 0; c < locations[r].length; c++) {
                        System.out.println("Drawing: row: " + r + " column: " + c);
                        locations[r][c].draw();
                    }
                }

                done = true; 
                System.out.println("tiles set");
            }
        }
    }

    private void setActions() {
        this.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {  } 
            public void keyPressed(KeyEvent e) {                 
                hero.keyPress(e);
            } 
            public void keyReleased(KeyEvent e) {
                hero.mover.stop();
            }
        });
    }

    public void trim() {
        System.out.println("trimming form");
        frameHeight -= (frameHeight - (locations.length * tileHeight));
        frameWidth -= (frameWidth - (locations[0].length * tileWidth));
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null);
        System.out.println("form trimmed");
    }

    private void setHero() {
        System.out.println("setting hero");
        JLabel heroImage = new JLabel();
        this.getContentPane().add(heroImage);
        heroImage.setBounds(100, 100, tileWidth, tileHeight);
        heroImage.setOpaque(true);
        this.getContentPane().setComponentZOrder(heroImage, 0);
        hero = new Hero(locations, this, heroImage);
        System.out.println("hero set");
    }
    
    /**
     * Generates a random number
     *
     * @param low the lowest number in the range
     * @param high the highest number in the range
     * @return the generated number
     */
    private int random(int low, int high) {
        double seed = Math.random();
        double L = (double) low;
        double H = (double) high;
        double number = (H - L + 1) * seed + L;
        int answer = (int) number;
        return answer;
    }

    
    
}