import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 6/03/13
 * Time: 7:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssociationsCategoryWindow extends JFrame {

    MyDrawPanel drawpanel = new MyDrawPanel();
    public AssociationsCategoryWindow(Session session) {
        getContentPane().add(drawpanel);
        // frame.addMouseListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);

    }

    class MyDrawPanel extends JComponent implements MouseListener {
//    SpiderNetNode oval = new SpiderNetNode(70, 70);

        SpiderNetNode[] nodes = new SpiderNetNode[10];

        private BasicStroke stroke = new BasicStroke(3.0f);
        private BasicStroke lineStroke = new BasicStroke(2.0f);

//        //Brown
//        private Color entityInsideColour = Color.decode("0xCCFF99");
//        private Color entityBorderColour = Color.decode("0x964B00");
//        private Color lineColour = Color.decode("0x00000");
//        private Color textColour = Color.decode("0x704214");
//
//
        //Peach
//        private Color entityInsideColour = Color.decode("0xFFCC99");
//        private Color entityInsideColour = Color.decode("0xFADFAD");
        private Color entityInsideColour = Color.decode("0xFFE5B4");
//        private Color entityBorderColour = Color.decode("0xFF9966");
//        private Color entityBorderColour = Color.decode("0xFFCC99");
//        private Color entityBorderColour = Color.decode("0x900020");
//        private Color entityBorderColour = Color.decode("0xCD5C5C");
        private Color entityBorderColour = Color.decode("0xCC4E5C");
        private Color lineColour = Color.decode("0x00000");
//        private Color textColour = Color.decode("0xC04000");
        private Color textColour = Color.decode("0x900020");

//        //Pink
//        private Color entityInsideColour = Color.decode("0xFFCBDB");
//        private Color entityBorderColour = Color.decode("0xCC8899");
//        private Color lineColour = Color.decode("0x00000");
//        private Color textColour = Color.decode("0x4B0082");

        //Green
//        private Color entityInsideColour = Color.decode("0xCCFF99");
//        private Color entityBorderColour = Color.decode("0x7BA05B");
//        private Color lineColour = Color.decode("0x00000");
//        private Color textColour = Color.decode("0x4B0082");

//        //Gray
//        private Color entityInsideColour = Color.decode("0xCECECE");
//        private Color entityBorderColour = Color.decode("0x9E9E9E");
//        private Color lineColour = Color.decode("0x00000");
//        private Color textColour = Color.decode("0x4D5D53");

        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            String[] speechParts = {"Объект", "Время", "Место", "Наречие", "Глагол", "Предлог", "Субъкт", "Частица"};
            for(int i = 0; i < 3; i++)
            {
                addSpiderNetNode(i, speechParts[i], i*200,0);

            }
            addSpiderNetNode(3, speechParts[3], 150, 150);
            addSpiderNetNode(4, speechParts[4], 150, 250);
            addSpiderNetNode(5, speechParts[5], 350, 250);
            addSpiderNetNode(6, speechParts[6], 0, 350);
            addSpiderNetNode(7, speechParts[7], 150, 350);//todo: refactor


//        for(int i = 3; i < 8; i++)
//        {
//
//            nodes[i] = new SpiderNetNode(i*100-i*10, i*150);
//            nodes[i].setName(i + "й эллипс");
//            nodes[i].setText(speechParts[i]);
//
//            //todo try to draw the line here!
//
//        }
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_OVER, (float) 0.75);

            /**
             * Setting stile for lines
             */
            g2d.setStroke(lineStroke);
            g2d.setColor(lineColour);

            /**
             * Drawing the lines
             */
            for(int i = 0; i < 7; i++)
            {
                if(i == 4)
                {
                    Line2D line = new Line2D.Double(nodes[i].getGraphicRepresentation().getCenterX(), nodes[i].getGraphicRepresentation().getCenterY(), nodes[i + 2].getGraphicRepresentation().getCenterX(), nodes[i + 2].getGraphicRepresentation().getCenterY());
                    g2d.draw(line);
                } else if(i == 5 || i == 2)
                {
                    continue;
                }
                Line2D line = new Line2D.Double(nodes[i].getGraphicRepresentation().getCenterX(), nodes[i].getGraphicRepresentation().getCenterY(), nodes[i + 1].getGraphicRepresentation().getCenterX(), nodes[i + 1].getGraphicRepresentation().getCenterY());
                g2d.draw(line);
            }
            Line2D line = new Line2D.Double(nodes[0].getGraphicRepresentation().getCenterX(), nodes[0].getGraphicRepresentation().getCenterY(), nodes[4].getGraphicRepresentation().getCenterX(), nodes[4].getGraphicRepresentation().getCenterY());
            g2d.draw(line);

            line = new Line2D.Double(nodes[0].getGraphicRepresentation().getCenterX(), nodes[0].getGraphicRepresentation().getCenterY(), nodes[6].getGraphicRepresentation().getCenterX(), nodes[6].getGraphicRepresentation().getCenterY());
            g2d.draw(line);
            for(int i = 0; i < 8; i++)
            {
                g2d.setColor(entityInsideColour);


                g2d.fill(nodes[i].getGraphicRepresentation()); // fills ellipse with colour

                g2d.setStroke(stroke);//sets Stroke
                g2d.setPaint(entityBorderColour); // sets stroke colour
                this.addMouseListener(nodes[i]);
                g2d.draw(nodes[i].getGraphicRepresentation());

                Font font = new Font("Arial", Font.BOLD, 14);

                g2d.setFont(font);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(textColour);
                g2d.drawString(nodes[i].getText(), (int) nodes[i].getGraphicRepresentation().getMinX()+25, (int) nodes[i].getGraphicRepresentation().getCenterY()+3);
            }
        }

        private void addSpiderNetNode(int number, String speechPart, int x, int y) {
            nodes[number] = new SpiderNetNode(x, y);
            nodes[number].setName(number + "й эллипс");
            nodes[number].setText(speechPart);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

    }

}
