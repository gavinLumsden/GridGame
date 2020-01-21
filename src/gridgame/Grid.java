package gridgame; 

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Grid extends JFrame
{
    public Map map; 
    
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
        setHero(); 
        setActions();
        trim();  
        this.setVisible(true);
    }
    
    private void setDataStructures() {
        frameWidth           = map.map.length;
        frameHeight          = map.map[0].length;
        tileWidth            = 80;
        tileHeight           = 80;
        rows                 = map.map.length;
        columns              = map.map[0].length;
        setTiles();
        boundary             = new Boundary(locations);
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
        
        Chunk1 chunk1 = new Chunk1(locations, this); 
        Chunk2 chunk2 = new Chunk2(locations, this); 
        Chunk3 chunk3 = new Chunk3(locations, this); 
        Chunk4 chunk4 = new Chunk4(locations, this); 
        
        locations = new Location[rows][columns];
        
        System.out.println("tiles set");
//        generatePatterns(); 
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

    private void generatePatterns() {
        System.out.println("generating patterns");
        for (int r = 0; r < locations.length; r++) {
            for (int c = 0; c < locations[0].length; c++) {
                if (locations[r][c].source == true) {
                    int sourceType = locations[r][c].sourceType; 
                    if (sourceType == Types.GRASS)  {
                        locations[r][c].type = Types.GRASS;
                        int random = random(1, 9); 
                        locations[r][c].tile.setImage(Types.GRASS_IMAGES[random]);
                    }  
                    if (sourceType == Types.DIRT)   generate(Types.DIRT, r, c); 
                    if (sourceType == Types.WATER)  generate(Types.WATER, r, c); 
                }
            }
        }
        System.out.println("patterns set");
    }
    
    private void generate(int type, int row, int column) {
        
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