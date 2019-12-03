package gridgame; 

import java.awt.event.KeyEvent;

public class Hero 
{

    public  Location[][]  locations;
    
    private int      currentLocation;
    private int      previousLocation;
    private int      row;
    private int      column;
    private Boundary boundary;
    private Grid     grid;
    
    public Hero(Location[][] locations, Boundary boundary, Grid grid) {
        this.locations = locations;
        this.row       = locations.length    / 2;
        this.column    = locations[0].length / 2;
        this.grid      = grid;
        this.locations[row][column].type = Types.HERO;
        currentLocation  = locations[row][column].type; 
        previousLocation = locations[row][column].type; 
        this.boundary = boundary; 
    }
    
    public void move(int row, int column) {
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