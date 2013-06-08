import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 8/02/13
 * Time: 8:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainWindow extends JFrame
{
    JButton startWorkingWithDictionaryButton;
    JButton startTranslatingSentencesButton;
    JButton startWorkingWithAssociationsButton;

    JPanel mainWindow = (JPanel) getContentPane();

    public MainWindow(final Session session)
    {
        super("Cистема изучения китайского языка");

        /**
         * Setting layout
         */
        mainWindow.setLayout(new BoxLayout(mainWindow, BoxLayout.LINE_AXIS));

        add(startWorkingWithDictionaryButton = new JButton("Работа со словарём"));

        startWorkingWithDictionaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                DictionaryWindow dictionaryWindow = new DictionaryWindow(session);
                dictionaryWindow.setMinimumSize(new Dimension(900, 600));
                dictionaryWindow.setVisible(true);
            }
        });

        add(startTranslatingSentencesButton = new JButton("Перевод предложений"));

        add(startWorkingWithAssociationsButton = new JButton("Ассоциации"));

        startWorkingWithAssociationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AssociationsCategoryWindow associationsWindow = new AssociationsCategoryWindow(session);
                associationsWindow.setMinimumSize(new Dimension(900, 600));
                associationsWindow.setVisible(true);
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });

        pack();


    }

    private void add(Object element)
    {
        mainWindow.add((Component) element);
    }
}
