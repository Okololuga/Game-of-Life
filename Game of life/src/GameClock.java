
public class GameClock extends Thread{
    public static boolean running = true;
    public static long speed = 100;

    public void run() {
        while (running) {
            try {
                if (!GUI.startPause.isSelected()) {
                    sleep(speed);

                    String genStr = "Generation #" + GenAlg.gen;
                    String aliveStr = "Alive: " + GenAlg.aliveNumber;

                    GUI.generationLabel.setText(genStr);
                    GUI.aliveLabel.setText(aliveStr);

                    GenAlg.nextGeneration();
                }
                else if (GUI.startPause.isSelected()) {
                    sleep(100);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
