import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 3/06/13
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssociationsCategoryContextMenu extends JPopupMenu{

    JMenuItem anItem;

    public AssociationsCategoryContextMenu(final String name) {
        String[] speechParts = {"Объект", "Время", "Место", "Наречие", "Глагол", "Предлог", "Субъкт", "Частица"};
        System.out.println(name);
        if(Arrays.asList(speechParts).contains(name))
        {
            System.out.println(name);
            anItem = new JMenuItem(new AbstractAction("Открыть") {
                public void actionPerformed(ActionEvent ae) {
                    AssociationsWindow associationsWindow = new AssociationsWindow(name);
                    associationsWindow.setSize(new Dimension(600,500));
                    associationsWindow.setDefaultCloseOperation(associationsWindow.EXIT_ON_CLOSE);
                    associationsWindow.setVisible(true);
                }
            });
            add(anItem);
        } else {
            anItem = new JMenuItem(new AbstractAction("Удалить") {
                public void actionPerformed(ActionEvent ae) {
                }
            });
            add(anItem);
            anItem = new JMenuItem(new AbstractAction("Добавить связь") {
                public void actionPerformed(ActionEvent ae) {
                }
            });
            add(anItem);

        }
    }

    public AssociationsCategoryContextMenu() {
        anItem = new JMenuItem(new AbstractAction("Добавить объект") {
            public void actionPerformed(ActionEvent ae) {
            }
        });
        add(anItem);
    }
}
