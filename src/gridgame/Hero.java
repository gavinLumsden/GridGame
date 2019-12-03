package gridgame; 

import gametools.Icons;
import gametools.Animation;
import gametools.Directions;
import gametools.GameObject;
import java.awt.event.KeyEvent;
import javax.swing.JLabel; 
import collections.LinkedList;
import gametools.UserInput;

public class Hero extends GameObject
{
    private LinkedList<Animation> animations; 
    public  Location[][]  locations;
    public  JLabel          label; 
    private int           currentLocation;
    private int           previousLocation;
    private int           row;
    private int           column;
    private Boundary      boundary;
    private Grid          grid;
    public  UserInput     input; 
    
    public final int WALK_DELAY = 800;
    public final int IDLE_DELAY = 100;
    
    private int directionToStop;

    public Hero(Location[][] locations, Boundary boundary, Grid grid) {
        super(locations[(locations.length/2)][(locations[0].length/2)].tile.tile, 100, Directions.STOP, Directions.FOUR_DIRECTIONS);
        this.locations                   = locations;
        this.row                         = locations.length    / 2;
        this.column                      = locations[0].length / 2;
        this.grid                        = grid;
        this.locations[row][column].type = Types.HERO;
        currentLocation                  = Types.GROUND; 
        previousLocation                 = Types.GROUND; 
        this.boundary                    = boundary; 
        input = new UserInput(coordinates, Directions.FOUR_DIRECTIONS); 
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

        Animation walkUpAnimation    = new Animation(locations[row][column].tile.tile, walkUpFiles, WALK_DELAY, true);
        Animation walkDownAnimation  = new Animation(locations[row][column].tile.tile, walkDownFiles, WALK_DELAY, true);
        Animation walkLeftAnimation  = new Animation(locations[row][column].tile.tile, walkLeftFiles, WALK_DELAY, true);
        Animation walkRightAnimation = new Animation(locations[row][column].tile.tile, walkRightFiles, WALK_DELAY, true);

        Animation stopUpAnimation    = new Animation(locations[row][column].tile.tile, stopUpFiles, WALK_DELAY, true);
        Animation stopDownAnimation  = new Animation(locations[row][column].tile.tile, stopDownFiles, WALK_DELAY, true);
        Animation stopLeftAnimation  = new Animation(locations[row][column].tile.tile, stopLeftFiles, WALK_DELAY, true);
        Animation stopRightAnimation = new Animation(locations[row][column].tile.tile, stopRightFiles, WALK_DELAY, true);

        animations = new LinkedList<>();

        animations.add(walkUpAnimation);
        animations.add(walkDownAnimation);
        animations.add(walkLeftAnimation);
        animations.add(walkRightAnimation);

        animations.add(stopUpAnimation);
        animations.add(stopDownAnimation);
        animations.add(stopLeftAnimation);
        animations.add(stopRightAnimation);

        label.animations.get(5); 
        locations[row][column].tile.animation.run();
    }
    
    public void animate() {
        if (locations[row][column].tile.animation == null) return; 
        if (coordinates.direction == Directions.UP) {
            if (locations[row][column].tile.animation.isRunning() == false) {
                directionToStop = Directions.UP; 
                locations[row][column].tile.animation = animations.get(0); 
                locations[row][column].tile.animation.run();
            }
            directionToStop = Directions.UP;
        } else if (coordinates.direction == Directions.DOWN) {
            if (locations[row][column].tile.animation.isRunning() == false) {
                directionToStop = Directions.DOWN; 
                locations[row][column].tile.animation = animations.get(1); 
                locations[row][column].tile.animation.run();
            }
            directionToStop = Directions.DOWN;
        } else if (coordinates.direction == Directions.LEFT) {
            if (locations[row][column].tile.animation.isRunning() == false) {
                directionToStop = Directions.LEFT; 
                locations[row][column].tile.animation = animations.get(2); 
                locations[row][column].tile.animation.setLabel(locations[row][column].tile.tile); 
                locations[row][column].tile.animation.run();
            }
            directionToStop = Directions.LEFT;
        } else if (coordinates.direction == Directions.RIGHT) {
            if (locations[row][column].tile.animation.isRunning() == false) {
                directionToStop = Directions.RIGHT; 
                locations[row][column].tile.animation = animations.get(3); 
                locations[row][column].tile.animation.run();
            }
            directionToStop = Directions.RIGHT;
        } else if (coordinates.direction == Directions.STOP) {
            if (directionToStop == Directions.UP) {
                locations[row][column].tile.animation = animations.get(4); 
                locations[row][column].tile.animation.run();
            } else if (directionToStop == Directions.DOWN) {
                locations[row][column].tile.animation = animations.get(5); 
                locations[row][column].tile.animation.run();
            } else if (directionToStop == Directions.LEFT) {
                locations[row][column].tile.animation = animations.get(6); 
                locations[row][column].tile.animation.run();
            } else if (directionToStop == Directions.RIGHT) {
                locations[row][column].tile.animation = animations.get(7); 
                locations[row][column].tile.animation.run();
            }
        }
    }
    
    public void move(int row, int column) {
        mover.move(); 
        label = locations[row][column].tile.tile; 
        locations[this.row][this.column].tile.isUpdated = true; 
        locations[row][column].tile.isUpdated           = true; 
        previousLocation = currentLocation; 
        locations[this.row][this.column].type = previousLocation;
        this.row    = row;
        this.column = column;
        currentLocation = locations[row][column].type;
        locations[row][column].type = Types.HERO;
        animate(); 
        redraw();
    }

    public void keyPress(KeyEvent event) {
        if      (event.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
        int row    = this.row;
        int column = this.column;
        input.keypress(event); 
        if      (event.getKeyCode() == KeyEvent.VK_UP)    row--;
        else if (event.getKeyCode() == KeyEvent.VK_DOWN)  row++;
        else if (event.getKeyCode() == KeyEvent.VK_LEFT)  column--;
        else if (event.getKeyCode() == KeyEvent.VK_RIGHT) column++;
        if      (boundary.inside(row,column)) move(row,column);        
    }

    @Override
    public void redraw() {
        for (int r = 0; r < locations.length; r++) {
            for (int c = 0; c < locations[0].length; c++) {
                if (locations[r][c].tile.isUpdated) {
                    gameImage.redraw(coordinates);
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