package gridgame; 

import gametools.Animation;
import collections.LinkedList;
import gametools.Directions;
import gametools.GameCharacter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel; 

public class Hero extends GameCharacter
{
    private LinkedList<Animation> animations; 
    public  Location[][]  locations;
    public  JLabel        label; 
    private int           currentLocation;
    private int           previousLocation;
    private int           row;
    private int           column;
    private Boundary      boundary;
    private Grid          grid;
    
    private int directionToStop;

    public Hero(Location[][] locations, Boundary boundary, Grid grid, JLabel label) {
        super(label, 100, Directions.STOP, Directions.FOUR_DIRECTIONS, 100);
        this.label                       = label; 
        this.locations                   = locations;
        this.row                         = locations.length    / 2;
        this.column                      = locations[0].length / 2;
        this.grid                        = grid;
        this.boundary                    = boundary; 
        this.locations[row][column].type = Types.HERO;
        currentLocation                  = Types.GROUND; 
        previousLocation                 = Types.GROUND; 
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

        sprite.setAnimations(animations);
        sprite.animate(5);
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
    }

    @Override
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
                    locations[r][c].draw();
                    locations[r][c].tile.isUpdated = false; 
                }
            }
        }
    }

    @Override
    public void action() {
        redraw(); 
    }
    
}