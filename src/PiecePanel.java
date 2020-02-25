import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PiecePanel extends JPanel {
    private BufferedImage _image;

    public PiecePanel (BufferedImage image) {
        _image = image;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(_image, 0, 0, this);
    }
}
