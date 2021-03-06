package gridgame;

/**
 * @author g.lumsden
 */
public class Chunk4 implements Runnable {
    
    public Thread thread; 
    
    public Location[][] locations; 
    public Grid grid; 
    
    public int tileWidth = 80; 
    public int tileHeight = 80; 
    
    public int rowSizeLow = 31;
    public int rowSizeHigh = 39;
    
    public int columnSizeLow = 0;
    public int columnSizeHigh = 84;
    
    public Chunk4(Location[][] locations, Grid grid) {
        this.locations = locations; 
        this.grid      = grid;  
        thread = new Thread(this); 
    }

    @Override
    public void run() {
        int y = rowSizeLow*tileWidth; 
        for (int r = rowSizeLow; r <= rowSizeHigh; r++) {
            int x = columnSizeLow*tileWidth; 
            for (int c = columnSizeLow; c <= columnSizeHigh; c++) {
                System.out.println("setting tile: row: " + r + " column: " + c);
                int type = Map.map[r][c];
                locations[r][c] = new Location(r, c, x, y, tileWidth, tileHeight, type, grid); 
                locations[r][c].draw();
                x += tileWidth; 
            }
            y += tileHeight; 
        }
    }
    
    public Location getLocation(int row, int column) {
        if (locations[row][column] != null) return locations[row][column]; 
        else return null; 
    }
    
}
