import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HoverInfoPanel extends JPanel {
    final private BufferedImage picImage;
    final private JLabel textLabel;

    public HoverInfoPanel(int x, int y) throws IOException {
        Rectangle mouseHoverInfoPanelRectangle = new Rectangle(x, y, 725, 220);
        setBounds(mouseHoverInfoPanelRectangle);

        picImage = ImageIO.read(new File("assets\\blank_info.png"));
        textLabel = new JLabel();
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        setOpaque(false);
        setLayout(null);
        setVisible(false);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (picImage != null) {
            g.drawImage(picImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void ShowInfoPanel(String facultyName, String headFacultyName, String owner, int salePrice, int tribute, int houseAmount, boolean hotel) {
        String htmlText = "<html>"
                + "<b>Nazwa:</b> " + facultyName + "<br>"
                + "<b>Wydział:</b> " + headFacultyName + "<br>"
                + "<b>Właściel:</b> " + owner + "<br>"
                + "<b>Wartość:</b> " + salePrice + " P$ <br>"
                + "<b>Opłata za wejście:</b> " + tribute + " P$ <br>"
                + "<b>Kadra:</b> ";

        if (hotel) {
            htmlText += "Prodziekan";
        }
        else{
            htmlText += houseAmount + " Profesorowie";
        }

        htmlText += "</html>";
        textLabel.setText(htmlText);

        Insets insets = getInsets();
        int textX = insets.left + 20;
        int textY = insets.top - 10;
        int textWidth = getWidth() - insets.left - insets.right - 2 * textX;
        int textHeight = getHeight() - insets.top - insets.bottom - 2 * textY;

        textLabel.setBounds(textX, textY, textWidth, textHeight);

        removeAll();
        add(textLabel);

        setVisible(true);
    }
}
