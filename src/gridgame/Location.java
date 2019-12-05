package gridgame; 

public class Location
{

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
        else if (type == Types.BOUNDRY) tile.setImage(Types.BOUNDRY_IMAGE);
        else if (type == Types.ENEMY)   tile.setImage(Types.ENEMY_IMAGE);
        else if (type == Types.HOUSE)   tile.setImage(Types.HOUSE_IMAGE);
    }
    
}