import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class DragImage extends JComponent implements MouseMotionListener {
    static int imageWidth = 60, imageHeight = 60;
    int imageX, imageY;

    private static Image image;
    private static Image image1;

    public DragImage() {
        addMouseMotionListener(this);
    }

    public void mouseDragged(MouseEvent e) {
        imageX = e.getX()-30;
        imageY = e.getY()-30;
        repaint();
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D g3 = (Graphics2D) g;

        g3.drawImage(image, imageX, imageY, this);
        g2.drawImage(image1, 90, 90, this);
    }

    public static void main(String[] args) {
        String imageFile = "nya.jpg";
        // Turn off double buffering
        RepaintManager.currentManager(null).setDoubleBufferingEnabled(false);

        image = Toolkit.getDefaultToolkit().getImage(DragImage.class.getResource(imageFile));
        image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
        JFrame frame = new JFrame("DragImage");
        frame.getContentPane().add(new DragImage());

        image1 = Toolkit.getDefaultToolkit().getImage(DragImage.class.getResource("icons/accept.png"));
        image1 = image1.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);

        frame.getContentPane().add(new DragImage());
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}