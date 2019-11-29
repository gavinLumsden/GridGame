package gridgame; 

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class Types 
{
    public static final String PATH = "https://drive.google.com/uc?id=";
    
    public static int    GROUND        = 0;
    public static int    HERO          = 1;
    public static int    BOUNDRY       = 3;
    public static int    ENEMY         = 4;
    
    public static String HERO_IMAGE    = PATH + "1oAxS9-vUv4bVUqD1FvMSogPKluDrAzLt"; 
    public static String GROUND_IMAGE  = PATH + "1Gz5ej43CLhh2wh5sC8FtgmTovHwYRnD3"; 
    public static String ENEMY_IMAGE   = PATH + "10G3VmBGb9jCawlQeHUfbdQZLN5ARGGw9"; 
    public static Color  BOUNDRY_IMAGE = Color.RED;
    public static Color  BACKGROUND    = Color.BLACK;
    
    public static Border TILE_BORDER   = 
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
              
}