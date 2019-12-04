package gridgame; 

import gametools.Animation;
import gametools.Directions;
import gametools.GameCharacter;
import collections.LinkedList;
import javax.swing.JLabel; 

public class Hero extends GameCharacter
{
    private LinkedList<Animation> animations; 
    public  JLabel                label; 
    private Boundary              boundary;
    
    private int directionToStop;

    public Hero(Boundary boundary, JLabel label) {
        super(label, 50, Directions.STOP, Directions.FOUR_DIRECTIONS, 100);
        this.label    = label; 
        this.boundary = boundary; 
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

        Animation walkUpAnimation    = new Animation(label, walkUpFiles, WALK_DELAY, true);
        Animation walkDownAnimation  = new Animation(label, walkDownFiles, WALK_DELAY, true);
        Animation walkLeftAnimation  = new Animation(label, walkLeftFiles, WALK_DELAY, true);
        Animation walkRightAnimation = new Animation(label, walkRightFiles, WALK_DELAY, true);

        Animation stopUpAnimation    = new Animation(label, stopUpFiles, WALK_DELAY, true);
        Animation stopDownAnimation  = new Animation(label, stopDownFiles, WALK_DELAY, true);
        Animation stopLeftAnimation  = new Animation(label, stopLeftFiles, WALK_DELAY, true);
        Animation stopRightAnimation = new Animation(label, stopRightFiles, WALK_DELAY, true);

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

    @Override
    public void action() {
        mover.move();
        animate(); 
        redraw(); 
    }
    
    
    
    
    
    
//    public void move(int row, int column) {
//        mover.move(); 
//        label = locations[row][column].tile.tile; 
//        locations[this.row][this.column].tile.isUpdated = true; 
//        locations[row][column].tile.isUpdated           = true; 
//        previousLocation = currentLocation; 
//        locations[this.row][this.column].type = previousLocation;
//        this.row    = row;
//        this.column = column;
//        currentLocation = locations[row][column].type;
//        locations[row][column].type = Types.HERO;
//        animate(); 
//    }

//    public void redrawGrid() {
//        for (int r = 0; r < locations.length; r++) {
//            for (int c = 0; c < locations[0].length; c++) {
//                if (locations[r][c].tile.isUpdated) {
//                    locations[r][c].draw();
//                    locations[r][c].tile.isUpdated = false; 
//                }
//            }
//        }
//    }
    
}