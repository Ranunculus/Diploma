import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 31/03/13
 * Time: 5:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpiderNetNode implements MouseListener {
    private Ellipse2D.Double graphicRepresentation;
    private String name;
    private String text;

    public SpiderNetNode(int x, int y) {
        graphicRepresentation = new Ellipse2D.Double(x, y, 100, 50);
    }

    public SpiderNetNode(int x, int y, int width, int height) {
        graphicRepresentation = new Ellipse2D.Double(x, y, width, height);
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

        System.out.println(getGraphicRepresentation().contains(e.getX(), e.getY()));
        if(e.getButton() == 3)
        {
            if(getGraphicRepresentation().contains(e.getX(), e.getY()))
            {
                System.out.println("IF");
                AssociationsCategoryContextMenu menu = new AssociationsCategoryContextMenu(text);
                menu.show(e.getComponent(), e.getX(), e.getY());


                System.out.println("Released button" + (e.getButton()));
            }
//            else if(!getGraphicRepresentation().contains(e.getX(), e.getY())){
//                System.out.println("ELSE IF");
//                AssociationsCategoryContextMenu menu = new AssociationsCategoryContextMenu();
//                menu.show(e.getComponent(), e.getX(), e.getY());
//            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

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