import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class DiceRoll extends JPanel {

    private static final int DICE_SIZE = 230;
    private static final int ANIMATION_DELAY = 100;
    private static final int FRAME_DELAY = 2000;
    private static final int ARC_SIZE = 70;

    private final Image[] diceImages;
    private int currentDiceValue;

    public int result;

    public DiceRoll() {
        diceImages = new Image[6];
        for (int i = 0; i < 6; i++) {
            diceImages[i] = new ImageIcon("assets/dice/" + (i + 1) + ".png").getImage();
        }
        currentDiceValue = 1; // Początkowa wartość kostki

        setPreferredSize(new Dimension(DICE_SIZE, DICE_SIZE));
        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rollDice();
                //wysylanie danych do servera
            }
        });
    }

    public void rollDice() {
        RollDiceWorker worker = new RollDiceWorker();
        worker.execute();
    }

    private class RollDiceWorker extends SwingWorker<Integer, Integer> {
        @Override
        protected Integer doInBackground() throws Exception {
            Random random = new Random();
            int rolls = 15;
            int random_number = 0;

            for (int i = 0; i < rolls; i++) {
                random_number = random.nextInt(6) + 1;
                publish(random_number);
                Thread.sleep(ANIMATION_DELAY);
            }


            result = random_number;
            System.out.println(result);
            return random_number; // Final dice value
        }

        @Override
        protected void process(java.util.List<Integer> chunks) {
            currentDiceValue = chunks.get(chunks.size() - 1);
            repaint();
        }

        @Override
        protected void done() {
            try {
                currentDiceValue = get();
                repaint();

                Thread.sleep(FRAME_DELAY);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Rysowanie zaokrąglonego prostokąta
        RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, width, height, ARC_SIZE, ARC_SIZE);
        g2d.clip(roundRect);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width, height, ARC_SIZE, ARC_SIZE);

        Image currentImage = diceImages[currentDiceValue - 1];
        int x = (getWidth() - DICE_SIZE) / 3;
        int y = (getHeight() - DICE_SIZE) / 3;

        g2d.drawImage(currentImage, x, y, this);
        g2d.dispose();
    }

}
