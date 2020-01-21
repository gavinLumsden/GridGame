package gridgame;

/**
 * @author g.lumsden
 */
public class Chunk4 implements Runnable {
    
    public int tileWidth = 80; 
    public int tileHeight = 80; 
    
    public int rowSizeLow = 27;
    public int rowSizeHigh = 37;
    
    public int columnSizeLow = 63;
    public int columnSizeHigh = 85;
    
    public Chunk4(Location[][] locations, Grid grid) {
        int y = 0; 
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

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
