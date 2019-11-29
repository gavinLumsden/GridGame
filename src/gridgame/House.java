package gridgame;

/**
 * @author g.lumsden
 */
public class House {
    
    public Location[][] locations;
    
    private int      row;
    private int      column;
    private Boundary  boundry;
    private Grid     grid;
    
    
    public House(Location[][] locations, Grid grid) {
        this.locations = locations;
        this.row       = 6;
        this.column    = 6;
        this.grid      = grid;
        this.locations[row][column].type = Types.HOUSE;
        redraw();
    }
    
    public void redraw() {
        for (int row = 0; row < locations.length; row++) {
            for (int column = 0; column < locations[row].length; column++) {
                locations[row][column].draw();
            }
        }
    }
    
}
