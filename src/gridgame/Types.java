package gridgame; 

import java.awt.Color;

public class Types 
{
    public static final String PATH = "https://drive.google.com/uc?id=";
    
    public static int BLANK   = 0;
    public static int GRASS   = 1;
    public static int DIRT    = 2;
    public static int WATER   = 3;
    public static int ENEMY   = 4;
    public static int TRAINER = 5; 
    public static int BOUNDRY = 6;
    
    public static String BLANK_IMAGE    = PATH + "1hsHMe3DqD9yEmUFcWcyYYTbj0BNf82rt"; 
    public static String[] GRASS_IMAGES = {
        PATH + "1TIOnvURt7b61y254cBnxgNXNrVUxdZ33", 
        PATH + "1eYni2BLUzN2ar3DRnvcgYIHhFirWXYGR", 
        PATH + "1nVrk1IsfMpVMBjcHSYkaaxOSH0dPijfD", 
        PATH + "1aCzIUiwcrlORgMiljPd4wpfepE3DV8zO", 
        PATH + "1k_nGP8eBBn1qpSjVEyZXJ4cErg1pLCqz", 
        PATH + "1o1slSVASI5x0tBJzrtuJ0tdrKmNEeujD", 
        PATH + "1PE8FwIQpm-o_y0Ps1_2dQR0RUiIocPWo", 
        PATH + "13U-wYBLrSYHJwwDDMD6q3HerYvJO5UMO", 
        PATH + "1kxzEnoP1IsPB5Z_EnIwZDcOr198IYMI7"}; 
    public static String[] DIRT_IMAGES = {
        PATH + "", 
        PATH + "", 
        PATH + "", 
        PATH + "", 
        PATH + "", 
        PATH + "", 
        PATH + "", 
        PATH + "", 
        PATH + ""}; 
    public static String[] WATER_IMAGES = {
        PATH + "", 
        PATH + "", 
        PATH + "", 
        PATH + "", 
        PATH + "", 
        PATH + "", 
        PATH + "", 
        PATH + "", 
        PATH + ""}; 
    public static String BOUNDRY_IMAGE = PATH + "1hsHMe3DqD9yEmUFcWcyYYTbj0BNf82rt"; 
    public static String ENEMY_IMAGE   = PATH + "10G3VmBGb9jCawlQeHUfbdQZLN5ARGGw9"; 
    public static String HERO_IMAGE    = PATH + "1oAxS9-vUv4bVUqD1FvMSogPKluDrAzLt"; 
    
    public static Color  BACKGROUND    = Color.WHITE;
    
}
