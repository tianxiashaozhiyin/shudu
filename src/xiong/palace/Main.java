package xiong.palace;

public class Main {
    public static void main(String[] args) {
        int[][] ninePalace =
                {
                        {5, 0, 0, 8, 0, 0, 0, 9, 0},
                        {3, 0, 0, 0, 0, 5, 7, 0, 0},
                        {0, 0, 0, 9, 4, 3, 0, 0 ,8},
                        {0, 0, 9, 7, 0, 0, 8, 3, 0},
                        {6, 0, 0, 0, 0, 0, 0, 0, 4},
                        {0, 3, 2, 0, 0, 4, 6, 0, 0},
                        {7, 0, 0, 2, 3, 9, 0, 0, 0},
                        {0, 0, 3, 4, 0, 0, 0, 0, 5},
                        {0, 1, 0, 0, 0, 7, 0, 0, 9},
                };

        NinePalace thePalace = new NinePalace(ninePalace);
        thePalace.fill(0,0);
        thePalace.printNinePalace();
    }
}
