import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 3/06/13
 * Time: 9:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssociationsWindow extends JFrame {

    AssociationsPanel associationsPanel = new AssociationsPanel();
    public String associationCategory;

    public AssociationsWindow(String associationCategory)
    {
        this.setTitle(associationCategory);
        this.associationCategory = associationCategory;

        getContentPane().add(associationsPanel);
        // frame.addMouseListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
    }

    private class AssociationsPanel extends JComponent implements MouseListener {

        SpiderNetNode[] nodes;
        int nodesAmount  = 10; // retrieve from database

        private BasicStroke stroke = new BasicStroke(2.0f);
        private BasicStroke lineStroke = new BasicStroke(1.0f);

        private Color entityInsideColour = Color.decode("0xFFE5B4");
        private Color entityBorderColour = Color.decode("0xCC4E5C");
        private Color lineColour = Color.decode("0x00000");
        private Color textColour = Color.decode("0x900020");

        public void paintComponent(Graphics g) {
            //retrieve all words of given category
            System.out.println(associationCategory);
            System.out.println(associationCategory.equals("Объект"));
            if(associationCategory.equals("Объект"))
            {
                Graphics2D g2d = (Graphics2D) g;

                nodes = new SpiderNetNode[18];

                addSpiderNetNode(0,"爸爸", 0,0);

                addSpiderNetNode(2,"老师",0,100);
                addSpiderNetNode(9,"朋友",0,200);

                addSpiderNetNode(1,"家",100,0);

                addSpiderNetNode(3,"儿子",100,100);
                addSpiderNetNode(6,"女儿",250,150);

                addSpiderNetNode(4,"他",500,50);
                addSpiderNetNode(5,"她",300,50);
                addSpiderNetNode(7,"你",250,300);
                addSpiderNetNode(8,"小姐",0,300);
                addSpiderNetNode(10,"同学",100,400);
                addSpiderNetNode(11,"我",400,300);
                addSpiderNetNode(12,"我们",500,200);
                addSpiderNetNode(13,"先生",350,450);
                addSpiderNetNode(14,"学生",200,550);
                addSpiderNetNode(15,"医生",450,550);
                addSpiderNetNode(16,"妈妈",200,0);
                addSpiderNetNode(17,"人",400,150);

                g2d.setStroke(lineStroke);
                g2d.setColor(lineColour);

                /**
                 * Drawing the lines
                 */

                g2d.draw(link(16,5));
                g2d.draw(link(6,5));
                g2d.draw(link(6,3));
                g2d.draw(link(17,4));
                g2d.draw(link(17,12));
                g2d.draw(link(11,12));
                g2d.draw(link(17,7));
                g2d.draw(link(10,14));
                g2d.draw(link(13,14));
                g2d.draw(link(13,15));



                for(int i = 0; i < 18; i++)
                {
                    g2d.setColor(entityInsideColour);


                    g2d.fill(nodes[i].getGraphicRepresentation()); // fills ellipse with colour

                    g2d.setStroke(stroke);//sets Stroke
                    g2d.setPaint(entityBorderColour); // sets stroke colour
                    this.addMouseListener(nodes[i]);


                    g2d.draw(nodes[i].getGraphicRepresentation());


                    g2d.setColor(textColour);
                    g2d.drawString(nodes[i].getText(), (int) nodes[i].getGraphicRepresentation().getMinX()+20, (int) nodes[i].getGraphicRepresentation().getCenterY()+3);
                }
            }



        }

        private Line2D link(int first, int second) {
            return new Line2D.Double(nodes[first].getGraphicRepresentation().getCenterX(),
                    nodes[first].getGraphicRepresentation().getCenterY(),
                    nodes[second].getGraphicRepresentation().getCenterX(),
                    nodes[second].getGraphicRepresentation().getCenterY());
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        private void addSpiderNetNode(int number, String chineseWord, int x, int y) {
            nodes[number] = new SpiderNetNode(x, y, 50, 50);
            nodes[number].setName(number + "й эллипс");
            nodes[number].setText(chineseWord);
        }
    }
}
