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
    
    public int rowSizeLow = 27;
    public int rowSizeHigh = 37;
    
    public int columnSizeLow = 63;
    public int columnSizeHigh = 85;
    
    public Chunk4(Location[][] locations, Grid grid) {
        this.locations = locations; 
        this.grid      = grid;  
        thread = new Thread(this); 
    }

    @Override
    public void run() {int y = 0; 
        for (int r = rowSizeLow; r < rowSizeHigh; r++) {
            int x = 0; 
            for (int c = columnSizeLow; c < columnSizeHigh; c++) {
                System.out.println("setting tile: row: " + r + " column: " + c);
                locations[r][c] = new Location(r, c, x, y, tileWidth, tileHeight, grid); 
                locations[r][c].draw();
                x += tileWidth; 
            }
            y += tileHeight; 
        }
    }
    
}