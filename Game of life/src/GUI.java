import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    public static Draw d = new Draw();
    JPanel controlls;
    JPanel field;
    JPanel buttons;
    JPanel counters;

    static JToggleButton startPause;
    static JButton restart;
    static JLabel generationLabel;
    static JLabel aliveLabel;
    static JLabel speedLabel;
    static JLabel cellColorLbl;
    static JLabel fieldColorLbl;
    static JSlider speed;
    static JComboBox cellColorChooser;
    static JComboBox fieldColorChooser;

    private static Color fieldColor = Color.CYAN;

    private String colors[] = {"Black", "Red", "Yellow", "Green", "Blue", "Pink", "Magenta", "Cyan"};


    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(920, 700);
        setResizable(true);

        setLayout(null);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    public void initComponents() {

        Color backgroundColor = Color.lightGray;
        //d.setBounds(10,5,270,270);
        d.setBounds(10,5,690,690);
        d.setVisible(true);
        add(d);

        /* панель кнопок и счетчиков */
        controlls = new JPanel();
        controlls.setBounds(0, 0, 220, 700);
        controlls.setLayout(null);
       // controlls.setBackground(Color.gray);
        controlls.setBackground(backgroundColor);
        add(controlls);

        /* панель поле */
        field = new JPanel();
        field.setBounds(221,0,700, 700);
        field.setLayout(null);
        field.setBackground(fieldColor);
       // field.setBackground(backgroundColor);
        field.add(d);
        //add(field);
        add(field);


        /*Generation label*/
        generationLabel = new JLabel("Generation #0");
        generationLabel.setName("GenerationLabel");
        generationLabel.setBounds(1, 1, 100, 30);
        generationLabel.setHorizontalAlignment(SwingConstants.LEFT);
        generationLabel.setVerticalAlignment(SwingConstants.CENTER);
       // controlls.add(generationLabel);

        /*Alive label*/
        aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");
        aliveLabel.setBounds(15, 15, 100, 30);
        aliveLabel.setHorizontalAlignment(SwingConstants.LEFT);
        //generationLabel.setVerticalAlignment(SwingConstants.CENTER);
       // controlls.add(aliveLabel);

        /*Speed label*/
        speedLabel = new JLabel("Speed");
        speedLabel.setBounds(15,85,100,30);
        speedLabel.setHorizontalAlignment(SwingConstants.LEFT);

        cellColorLbl = new JLabel("Cell color");
        cellColorLbl.setBounds(15,170,100,30);
        cellColorLbl.setHorizontalAlignment(SwingConstants.LEFT);

        fieldColorLbl = new JLabel("Field color");
        fieldColorLbl.setBounds(15,260,100,30);
        fieldColorLbl.setHorizontalAlignment(SwingConstants.LEFT);

        /*Button panel*/
        buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 2, 3, 5)); //координаты кнопок
        buttons.setBounds(5,10,210,30);
       // buttons.setBackground(Color.gray);
        buttons.setBackground(backgroundColor);
        //buttons.setSize(100, 50);
        startPause = new JToggleButton();
        startPause.setText("Pause");
        startPause.setName("PlayToggleButton");
        startPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startPause.isSelected() ) startPause.setText("Start");
                else startPause.setText("Pause");
            }
        });


        restart = new JButton();
        restart.setText("Restart");
        restart.setName("ResetButton");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenAlg.setMap();
            }
        });

        speed = new JSlider(0,10,5);
        speed.setSize(200,50);
        speed.setBounds(10,111,200,50);
        speed.setPaintTicks(true);
        speed.setMinorTickSpacing(1);
        speed.setPaintLabels(true);
        //speed.setToolTipText("Crjhjcnm");
       // speed.setBackground(Color.gray);
        speed.setBackground(backgroundColor);
        speed.setForeground(Color.magenta);

        speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                GameClock.speed = speed.getValue() * 40;
            }
        });
        controlls.add(speed);
        controlls.add(speedLabel);

        cellColorChooser = new JComboBox(colors);
        cellColorChooser.setSize(200,50);
        cellColorChooser.setBounds(10, 200, 200, 50);
        cellColorChooser.setToolTipText("Цвет клеток");


        //cellColorChooser.addItemListener(e -> setupColor(cellColorChooser, Draw.cellColor));
        cellColorChooser.addItemListener(e -> {
            switch (cellColorChooser.getSelectedItem().toString()) {
                case "Black" :Draw.cellColor = Color.BLACK; break;
                case "Red" : Draw.cellColor = Color.RED; break;
                case "Yellow" : Draw.cellColor = Color.YELLOW; break;
                case "Green" : Draw.cellColor = Color.GREEN; break;
                case "Blue" : Draw.cellColor = Color.BLUE; break;
                case "Pink" : Draw.cellColor = Color.pink; break;
                case "Magenta" : Draw.cellColor = Color.magenta; break;
                case "Cyan" : Draw.cellColor = Color.CYAN; break;
            }

        });
        controlls.add(cellColorChooser);
        controlls.add(cellColorLbl);


        fieldColorChooser = new JComboBox(colors);
        fieldColorChooser.setSize(200,50);
        fieldColorChooser.setBounds(10, 290, 200, 50);
        fieldColorChooser.setSelectedItem("Cyan");
        fieldColorChooser.addItemListener(e -> {
            switch (fieldColorChooser.getSelectedItem().toString()) {
                case "Black" : fieldColor = Color.BLACK; break;
                case "Red" : fieldColor = Color.RED; break;
                case "Yellow" : fieldColor = Color.YELLOW; break;
                case "Green" : fieldColor = Color.GREEN; break;
                case "Blue" : fieldColor = Color.BLUE; break;
                case "Pink" : fieldColor = Color.pink; break;
                case "Magenta" : fieldColor = Color.magenta; break;
                case "Cyan" : fieldColor = Color.CYAN; break;
            }
            field.setBackground(fieldColor);
            repaint();
        });
        controlls.add(fieldColorChooser);
        controlls.add(fieldColorLbl);


        buttons.add(startPause);
        buttons.add(restart);

        /*Счетчики*/
        counters = new JPanel();
        counters.setLayout(new GridLayout(2,1,5,5)); //координаты счетчиков
        counters.setBounds(10,51,180,30);
        //counters.setBackground(Color.gray);
        counters.setBackground(backgroundColor);

        counters.add(generationLabel);
        counters.add(aliveLabel);

        controlls.add(counters);
        controlls.add(buttons);

        /*шрифты*/
        Font font = new Font("Courier", Font.PLAIN, 5);
        generationLabel.setFont(font);
        generationLabel.setFont(generationLabel.getFont().deriveFont(12f));
        aliveLabel.setFont(font);
        aliveLabel.setFont(aliveLabel.getFont().deriveFont(12f));


    }
}

class Draw extends JLabel {
    static Color cellColor = Color.BLACK;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        final int WIDTH = 5, HEIGHT = 5; //размер квадратиков

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        g.setColor(Color.BLACK);

        for (int x = 0, xOffset = 1; x < GenAlg.rows; x++, xOffset+=WIDTH - 1) {
            for (int y = 0,  yOffset = 1; y < GenAlg.cols; y++, yOffset+=HEIGHT - 1) {
                if (GenAlg.currentGeneration[x][y]) {
                    //g.setColor(Color.BLACK);
                    g.setColor(cellColor);
                    g.fillRect(xOffset + x, yOffset + y, WIDTH, HEIGHT);
                } else {
                    g.setColor(Color.GRAY);
                    g.drawRect(xOffset + x, yOffset + y, WIDTH, HEIGHT);
                }
            }
        }
        repaint();
    }
}
