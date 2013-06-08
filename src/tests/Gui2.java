package tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;


public class Gui2 extends JFrame {
    JFrame frame = new JFrame();
    MyDrawPanel drawpanel = new MyDrawPanel();

    public static void main(String[] args) {
        Gui2 gui = new Gui2();
        gui.go();
    }

    public void go() {

        frame.getContentPane().add(drawpanel);
        // frame.addMouseListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);

    }

}

class MyDrawPanel extends JComponent implements MouseListener {
//    SpiderNetNode oval = new SpiderNetNode(70, 70);

    SpiderNetNode[] nodes = new SpiderNetNode[10];

    private BasicStroke stroke = new BasicStroke(2.0f);
    private BasicStroke lineStroke = new BasicStroke(1.0f);

    private Color entityInsideColour = Color.decode("0xCCFF99");
    private Color entityBorderColour = Color.decode("0x000000");
    private Color lineColour = Color.decode("0x00000");
    private Color textColour = Color.decode("0x0033FF");

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
         * Drawing the lines
         */
        for(int i = 0; i < 7; i++)
        {
            g2d.setStroke(lineStroke);
            g2d.setColor(lineColour);
            Line2D line = new Line2D.Double(nodes[i].getGraphicRepresentation().getCenterX(), nodes[i].getGraphicRepresentation().getCenterY(), nodes[i + 1].getGraphicRepresentation().getCenterX(), nodes[i + 1].getGraphicRepresentation().getCenterY());
            g2d.draw(line);
        }
        for(int i = 0; i < 8; i++)
        {
            g2d.setColor(entityInsideColour);


            g2d.fill(nodes[i].getGraphicRepresentation()); // fills ellipse with colour

            g2d.setStroke(stroke);//sets Stroke
            g2d.setPaint(Color.blue); // sets stroke colour
            this.addMouseListener(nodes[i]);
            g2d.draw(nodes[i].getGraphicRepresentation());


            g2d.setColor(textColour);
            g2d.drawString(nodes[i].getText(), (int) nodes[i].getGraphicRepresentation().getMinX()+25, (int) nodes[i].getGraphicRepresentation().getCenterY()+3);
        }
//        this.addMouseListener(oval);

    }

    private void addSpiderNetNode(int number, String speechPart, int x, int y) {
        nodes[number] = new SpiderNetNode(x, y);
        nodes[number].setName(number + "й эллипс");
        nodes[number].setText(speechPart);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if ((e.getButton() == 1) && oval.contains(e.getX(), e.getY())) {
//            this.repaint();
//            System.out.println(e.getButton());
//            // JOptionPane.showMessageDialog(null,e.getX()+ "\n" + e.getY());
//        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        if(oval.contains(e.getX(), e.getY()))
//        {
//            System.out.println("Entered");
//        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println(e.getX());
//        System.out.println(e.getY());
//        if(oval.contains(e.getX(), e.getY()))
//        {
//            System.out.println("Exited");
//        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

//        if(oval.contains(e.getX(), e.getY()))
//        {
//            System.out.println("Pressed button" + (e.getButton() == 1 ? "Left" : "Right"));
//        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
class PopUpDemo extends JPopupMenu {
    JMenuItem anItem;
    public PopUpDemo(){
        anItem = new JMenuItem("Open");
        add(anItem);
    }

    public PopUpDemo(String oval) {
        add(new JMenuItem("Add"));
        add(new JMenuItem("Delete"));
    }
}
