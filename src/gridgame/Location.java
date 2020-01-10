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
        if      (type == Types.GROUND)  tile.setImage(Types.GROUND_IMAGE);
        else if (type == Types.WALL)    tile.setImage(Types.WALL_IMAGE);
        else if (type == Types.BOUNDRY) tile.setImage(Types.BOUNDRY_IMAGE);
        else if (type == Types.ENEMY)   tile.setImage(Types.ENEMY_IMAGE);
        else if (type == Types.HOUSE)   tile.setImage(Types.HOUSE_IMAGE);
        else if (type == Types.RANDOM_TEST) setSource(); 
    }
    
    private void setSource() {
        int random = random(1, 3); 
        if (random == Types.GRASS) sourceType = Types.GRASS; 
        if (random == Types.DIRT)  sourceType = Types.DIRT; 
        if (random == Types.WATER) sourceType = Types.WATER; 
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