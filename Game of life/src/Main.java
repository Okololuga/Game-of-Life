public class Main {
    public static void main(String[] args) {
        new GUI();

        GameClock gc = new GameClock();
        GenAlg.setMap();

        gc.start();

    }
}

