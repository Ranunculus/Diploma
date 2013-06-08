package tests;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 1/04/13
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpiderNetNode implements MouseListener {
    private Ellipse2D.Double graphicRepresentation;
    private String name;
    private String text;

    public SpiderNetNode(int x, int y) {
        graphicRepresentation = new Ellipse2D.Double(x, y, 100, 50);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println("Mouse clicked");

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        if(graphicRepresentation.contains(e.getX(), e.getY()))
//        {
//            System.out.println("Pressed");
//            System.out.println(name);
//            System.out.println(e.getButton());
//            System.out.println(e.getClickCount());
//            if(e.getButton() == 1 && e.getClickCount() == 2)
//            {
//                PopUpDemo menu = new PopUpDemo("test");
//                menu.show(e.getComponent(), e.getX(), e.getY());
//            }
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(getGraphicRepresentation().contains(e.getX(), e.getY()) && e.getButton() == 0)
        {
            PopUpDemo menu = new PopUpDemo("oval");
            menu.show(e.getComponent(), e.getX(), e.getY());

            System.out.println("Released button" + (e.getButton()));
        } else {
            PopUpDemo menu = new PopUpDemo();
            menu.show(e.getComponent(), e.getX(), e.getY());

        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("Enter");
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("Exit");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Ellipse2D.Double getGraphicRepresentation() {
        return graphicRepresentation;
    }

    public void setGraphicRepresentation(Ellipse2D.Double graphicRepresentation) {
        this.graphicRepresentation = graphicRepresentation;
    }
}