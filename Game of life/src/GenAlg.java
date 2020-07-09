import java.util.Date;
import java.util.Random;

public class GenAlg {
   static Random r = new Random();
    static Date t = new Date();

    protected static long seed;
    protected static int gen = 0;  //номер поколения
    protected static int aliveNumber = 0;  //количество живых
    protected static int rows = 130;
    protected static int cols = 130;
    protected static boolean[][] currentGeneration = new boolean[rows][cols];

    public GenAlg(long S) {
        seed = S;
    }


    /* Заселение */
    public  static void setMap() {
        new GenAlg(t.getTime());
        gen = 0;
        aliveNumber = 0;
        Random random = new Random(seed);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean isState = random.nextBoolean();
                currentGeneration[i][j] = isState;
            }
        }
    }


    private static void aliveCount() {
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (currentGeneration[i][j])
                    counter++;
            }
        }
        aliveNumber = counter;
    }

    private static int countNeighbors (int x, int y) {
        int count = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                var col = (x + i + cols) % cols;
                var row = (y + j + rows) % rows;

                var isSelfChecking = col == x && row == y;
                var hasLife = currentGeneration[col][row];
                if (hasLife && !isSelfChecking)
                    count++;
            }
        }
        return count;
    }

    public static void nextGeneration () {
        gen++;

        final int SIZE = currentGeneration.length; //размер массива
        cols = SIZE;
        rows = SIZE;
        boolean[][] nextGeneration = new boolean[SIZE][SIZE];

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                var neighboursCount = countNeighbors(x, y);
                var hasLife = currentGeneration[x][y];

                if(!hasLife && neighboursCount == 3)
                    nextGeneration[x][y] = true;
                else if (hasLife && (neighboursCount < 2 || neighboursCount > 3))
                    nextGeneration[x][y] = false;
                else
                    nextGeneration[x][y] = currentGeneration[x][y];
            }
        }
        currentGeneration = nextGeneration;
        aliveCount();
    }
}
