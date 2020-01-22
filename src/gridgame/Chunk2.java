package gridgame;

/**
 * @author g.lumsden
 */
public class Chunk2 implements Runnable {
    
    public Thread thread; 
    
    public Location[][] locations; 
    public Grid grid; 
    
    public int tileWidth = 80; 
    public int tileHeight = 80; 
    
    public int rowSizeLow = 9;
    public int rowSizeHigh = 18;
    
    public int columnSizeLow = 21;
    public int columnSizeHigh = 42;
    
    public Chunk2(Location[][] locations, Grid grid) {
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
