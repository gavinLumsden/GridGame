package gridgame; 

import java.awt.event.KeyEvent;

public class Hero 
{

    public Location[][] locations;
    
    private int      row;
    private int      column;
    private Boundary boundary;
    private Grid     grid;
    private int      currentTile;
    private int      previousTile;
    
    public Hero(Location[][] locations, Boundary boundary, Grid grid) {
        this.locations = locations;
        this.row       = locations.length    / 2;
        this.column    = locations[0].length / 2;
        this.grid      = grid;
        this.locations[row][column].type = Types.HERO;
        previousTile = Types.GROUND; 
        currentTile  = Types.HERO; 
        redraw();
    }
    
    public void move(int row, int column) {
        previousTile = currentTile; 
        locations[this.row][this.column].type = previousTile;
        this.row    = row;
        this.column = column;
        currentTile = locations[row][column].type; 
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
        for (int row = 0; row < locations.length; row++) {
            for (int column = 0; column < locations[row].length; column++) {
                locations[row][column].draw();
            }
        }
    }
    
}