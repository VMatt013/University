package model;

/** The map of the game used by the game model */
public class Map {
    /** A very easy map, that is solvable in one move */
    public static String[][] Easy = {
            {"x","x","x","0","x","0","x","0","x","x"},
            {"1","2","3","4","5","6","7","8","0","9"}
    };

    /** A medium map, that is very easy to solve */
    public static String[][] Medium = {
            {"x","x","x","0","x","0","x","7","x","x"},
            {"1","2","3","4","0","5","0","6","8","9"}
    };

    public static String[][] Test = {
            {"x","x","x","0","x","1","x","0","x","x"},
            {"0","0","0","1","0","0","0","0","1","1"}
    };
    public static String[][] Solved = {
            {"x","x","x","0","x","0","x","0","x","x"},
            {"1","2","3","4","5","6","7","8","9","0"}
    };
    /** The default map of the game */
    public static String[][] Default = {
            {"x","x","x","0","x","0","x","0","x","x"},
            {"0","2","3","4","5","6","7","8","9","1"}
    };

    /** The base unplayable map without any numbers */
    public static String[][] Base = {
            {"x","x","x","0","x","0","x","0","x","x"},
            {"0","0","0","0","0","0","0","0","0","0"}
    };



    /** The rows of the map */
    public static int rows = Base.length;

    /** The columns of the map*/
    public static int columns = Base[0].length;
}
