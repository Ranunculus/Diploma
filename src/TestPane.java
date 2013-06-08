import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 20/03/13
 * Time: 7:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestPane extends JPanel implements MouseListener{
    public Graphics2D g2d;
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100, 100);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g2d = (Graphics2D)g;
            for(int i = 0; i < 3; i++)
            {
//                this.add(new SpiderNetNode(56 + i * 10, 58 + i * 20, g2d));
            }
//            g2d.drawOval(200,100,50,42);
////
////            int radius = Math.min(getWidth(), getHeight());
////            int x = (getWidth() - radius) / 2;
////            int y = (getHeight()- radius) / 2;
////
////            BufferedImage buffer = new BufferedImage(radius, radius, BufferedImage.TYPE_INT_ARGB);
//
//            g2d.setColor(Color.BLACK);
//            Ellipse2D ellipse2D = new Ellipse2D.Float(500, 300, 100, 50);
////            ellipse2D.setFrame(5,5,100,50);
////            g2d.setClip(ellipse2D);
//            g2d.setStroke(stroke);
////            g2d.setStroke();
//            g2d.draw(ellipse2D);
//
//            g2d.setColor(Color.getHSBColor(56,51,150));
//            g2d.fill(ellipse2D);
//            g2d.setColor(Color.BLACK);
//
//            g2d.drawString("Nya",55,27);
//
//            g2d.setColor(Color.BLACK);
//
//            Ellipse2D ellipse = new Ellipse2D.Float(500, 600, 100, 50);
////            ellipse.setFrame(5,5,500,50);
//            g2d.fill(ellipse);
//
//            g2d.setColor(Color.getHSBColor(56,51,150));
//
//            g2d.drawString("Nya",500,600);
////            g2d.setColor(Color.BLACK);//if line is here, it runs below polygon with hyxogen
//            g2d.drawLine(500,500,100,100);
//            g2d.setColor(Color.DARK_GRAY);
//            g2d.drawOval(69,23,300,300);
//            Ellipse2D circle = new Ellipse2D.Float(5, 5, 100, 90);
//            Shape clip = g2d.getClip();
//            g2d.setClip(circle);
//            g2d.setColor(Color.MAGENTA);
//            g2d.fill(circle);
//            g2d.setClip(clip);
//            g2d.setColor(Color.PINK);
//            g2d.draw(circle);
//            Polygon p = new Polygon();
//            for (int i = 0; i < 5; i++)
//                // position + size * Math...
//                p.addPoint((int) (300 + 25 * Math.sin(i * 2
//                        * Math.PI/5 )),
//                        (int) (300 + 25 * Math.cos(i * 2 * Math.PI/5)));
//
//            g2d.setClip(p);
//
//            g2d.fill(p);
//            p.getBounds().setSize(new Dimension(100,50));
//            g2d.drawPolygon(p);

//
//
//            String text = "Nya";
//            g2d.setColor(Color.MAGENTA);
//            g2d.drawString("Nya", 290, 300);
//            g2d.dispose();
//            g.drawImage(buffer, x, y, this);
        }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.g2d.setColor(Color.black);
        this.g2d.drawString("NYA", 450,450);
//        repaint();
        System.out.println("nya");
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}