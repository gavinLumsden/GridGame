package gridgame; 

public class Location
{

    public boolean source; 
    public int sourceType; 
    public int row;
    public int column;
    public int type;
    public Tile tile;
    
    public Location(int row, int column, int x, int y, 
                    int tileWidth, int tileHeight, int type, Grid grid) {
        this.row    = row;
        this.column = column;   
        this.type   = type;
        this.tile   = new Tile(tileWidth, tileHeight, x, y, grid);
    }
    
    public void draw() {
        if      (type == Types.BLANK)   tile.setImage(Types.BLANK_IMAGE);
        else if (type == Types.GRASS)   setSource(Types.GRASS); 
        else if (type == Types.DIRT)    setSource(Types.DIRT); 
        else if (type == Types.WATER)   setSource(Types.WATER); 
        else if (type == Types.ENEMY)   tile.setImage(Types.ENEMY_IMAGE);
        else if (type == Types.BOUNDRY) tile.setImage(Types.BLANK_IMAGE);
    }
    
    private void setSource(int type) {
        if (type == Types.GRASS) {
            sourceType = Types.GRASS;
            int random = random(1, Types.GRASS_IMAGES.length); 
            tile.setImage(Types.GRASS_IMAGES[random]);
        } 
        if (type == Types.DIRT)  {
            sourceType = Types.DIRT;
            int random = random(1, Types.DIRT_IMAGES.length); 
            tile.setImage(Types.DIRT_IMAGES[random]);
        } 
        if (type == Types.WATER) {
            sourceType = Types.WATER;
            int random = random(1, Types.WATER_IMAGES.length); 
            tile.setImage(Types.WATER_IMAGES[random]);
        } 
    }
    
    /**
     * Generates a random number
     *
     * @param low the lowest number in the range
     * @param high the highest number in the range
     * @return the generated number
     */
    private int random(int low, int high) {
        double seed = Math.random();
        double L = (double) low;
        double H = (double) high;
        double number = (H - L + 1) * seed + L;
        int answer = (int) number;
        return answer;
    }
    
}