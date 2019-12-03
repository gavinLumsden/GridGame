package gridgame; 

import gametools.Icons;
import gametools.Animation;
import gametools.Directions;
import gametools.GameImage;
import gametools.GameObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import collections.LinkedList;
import javax.swing.JLabel;

public class Hero extends GameObject
{
    
    public  Location[][]  locations;
    private int           currentLocation;
    private int           previousLocation;
    private int           row;
    private int           column;
    private Boundary      boundary;
    private Grid          grid;
    public  GameImage     gameImage; 
    public  Timer         animator;
    
    public final int WALK_DELAY = 800;
    public final int IDLE_DELAY = 100;
    
    private int directionToStop;

    public Hero(Location[][] locations, Boundary boundary, Grid grid, JLabel image) {
        super(image, 100, Directions.STOP, Directions.FOUR_DIRECTIONS);
        animator = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animate();
            }
        });
        animator.start();
        this.locations                   = locations;
        this.row                         = locations.length    / 2;
        this.column                      = locations[0].length / 2;
        this.grid                        = grid;
        this.locations[row][column].type = Types.HERO;
        currentLocation                  = Types.GROUND; 
        previousLocation                 = Types.GROUND; 
        this.boundary                    = boundary; 
        gameImage                        = new GameImage(locations[row][column].tile.tile); 
        setAnimations(); 
    }
    
    private void setAnimations() {
        LinkedList<String> walkUpFiles = new LinkedList<>();
        walkUpFiles.add(Icons.BANDIT_WALK_UP_1);
        walkUpFiles.add(Icons.BANDIT_IDLE_UP);
        walkUpFiles.add(Icons.BANDIT_WALK_UP_2);
        walkUpFiles.add(Icons.BANDIT_IDLE_UP);

        LinkedList<String> walkDownFiles = new LinkedList<>();
        walkDownFiles.add(Icons.BANDIT_WALK_DOWN_1);
        walkDownFiles.add(Icons.BANDIT_IDLE_DOWN);
        walkDownFiles.add(Icons.BANDIT_WALK_DOWN_2);
        walkDownFiles.add(Icons.BANDIT_IDLE_DOWN);

        LinkedList<String> walkLeftFiles = new LinkedList<>();
        walkLeftFiles.add(Icons.BANDIT_WALK_LEFT_1);
        walkLeftFiles.add(Icons.BANDIT_IDLE_LEFT);
        walkLeftFiles.add(Icons.BANDIT_WALK_LEFT_2);
        walkLeftFiles.add(Icons.BANDIT_IDLE_LEFT);

        LinkedList<String> walkRightFiles = new LinkedList<>();
        walkRightFiles.add(Icons.BANDIT_WALK_RIGHT_1);
        walkRightFiles.add(Icons.BANDIT_IDLE_RIGHT);
        walkRightFiles.add(Icons.BANDIT_WALK_RIGHT_2);
        walkRightFiles.add(Icons.BANDIT_IDLE_RIGHT);

        LinkedList<String> stopUpFiles = new LinkedList<>();
        stopUpFiles.add(Icons.BANDIT_IDLE_UP);
        stopUpFiles.add(Icons.BANDIT_IDLE_UP);

        LinkedList<String> stopDownFiles = new LinkedList<>();
        stopDownFiles.add(Icons.BANDIT_IDLE_DOWN);
        stopDownFiles.add(Icons.BANDIT_IDLE_DOWN);

        LinkedList<String> stopLeftFiles = new LinkedList<>();
        stopLeftFiles.add(Icons.BANDIT_IDLE_LEFT);
        stopLeftFiles.add(Icons.BANDIT_IDLE_LEFT);

        LinkedList<String> stopRightFiles = new LinkedList<>();
        stopRightFiles.add(Icons.BANDIT_IDLE_RIGHT);
        stopRightFiles.add(Icons.BANDIT_IDLE_RIGHT);

        Animation walkUpAnimation    = new Animation(locations[row][column].tile.tile, walkUpFiles, 800, true);
        Animation walkDownAnimation  = new Animation(locations[row][column].tile.tile, walkDownFiles, 800, true);
        Animation walkLeftAnimation  = new Animation(locations[row][column].tile.tile, walkLeftFiles, 800, true);
        Animation walkRightAnimation = new Animation(locations[row][column].tile.tile, walkRightFiles, 800, true);

        Animation stopUpAnimation    = new Animation(locations[row][column].tile.tile, stopUpFiles, 800, true);
        Animation stopDownAnimation  = new Animation(locations[row][column].tile.tile, stopDownFiles, 800, true);
        Animation stopLeftAnimation  = new Animation(locations[row][column].tile.tile, stopLeftFiles, 800, true);
        Animation stopRightAnimation = new Animation(locations[row][column].tile.tile, stopRightFiles, 800, true);

        LinkedList<Animation> animations = new LinkedList<>();

        animations.add(walkUpAnimation);
        animations.add(walkDownAnimation);
        animations.add(walkLeftAnimation);
        animations.add(walkRightAnimation);

        animations.add(stopUpAnimation);
        animations.add(stopDownAnimation);
        animations.add(stopLeftAnimation);
        animations.add(stopRightAnimation);

        gameImage.setAnimations(animations);
    }
    
    public void animate() {
        if (coordinates.direction == Directions.UP) {
            if (gameImage.isRunning(0) == false) {
                directionToStop = Directions.UP; 
                gameImage.run(0);
            }
            directionToStop = Directions.UP;
        } else if (coordinates.direction == Directions.DOWN) {
            if (gameImage.isRunning(1) == false) {
                directionToStop = Directions.DOWN; 
                gameImage.run(1);
            }
            directionToStop = Directions.DOWN;
        } else if (coordinates.direction == Directions.LEFT) {
            if (gameImage.isRunning(2) == false) {
                directionToStop = Directions.LEFT; 
                gameImage.run(2);
            }
            directionToStop = Directions.LEFT;
        } else if (coordinates.direction == Directions.RIGHT) {
            if (gameImage.isRunning(3) == false) {
                directionToStop = Directions.RIGHT; 
                gameImage.run(3);
            }
            directionToStop = Directions.RIGHT;
        } else if (coordinates.direction == Directions.STOP) {
            if (directionToStop == Directions.UP) {
                gameImage.run(4);
            } else if (directionToStop == Directions.DOWN) {
                gameImage.run(5);
            } else if (directionToStop == Directions.LEFT) {
                gameImage.run(6);
            } else if (directionToStop == Directions.RIGHT) {
                gameImage.run(7);
            }
        }
    }
    
    public void move(int row, int column) {
        mover.move(); 
        locations[this.row][this.column].tile.isUpdated = true; 
        locations[row][column].tile.isUpdated           = true; 
        previousLocation = currentLocation; 
        locations[this.row][this.column].type = previousLocation;
        this.row    = row;
        this.column = column;
        currentLocation = locations[row][column].type;
        locations[row][column].type = Types.HERO;
        redraw();
    }

    public void keyPress(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
        int row    = this.row;
        int column = this.column;
        if (event.getKeyCode() == KeyEvent.VK_UP) row--;
        else if (event.getKeyCode() == KeyEvent.VK_DOWN) row++;
        else if (event.getKeyCode() == KeyEvent.VK_LEFT) column--;
        else if (event.getKeyCode() == KeyEvent.VK_RIGHT) column++;
        if (boundary.inside(row,column)) move(row,column);        
    }

    public void redraw() {
        for (int r = 0; r < locations.length; r++) {
            for (int c = 0; c < locations[0].length; c++) {
                if (locations[r][c].tile.isUpdated) {
                    locations[r][c].draw();
                    locations[r][c].tile.isUpdated = false; 
                }
            }
        }
    }

    public void setLocation() {
        locations[row][column].type = Types.HERO; 
    }
    
}