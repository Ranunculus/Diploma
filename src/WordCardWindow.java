import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 9/02/13
 * Time: 9:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class WordCardWindow  extends JFrame
{
    JPanel mainWindow = (JPanel) getContentPane();
    String[] dictionaryNames;
    public WordCardWindow(String word)
    {
        super(word);
        mainWindow.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        gbc.gridx = 0;
        mainWindow.add(new JLabel("Слово"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 4;
        mainWindow.add(new JTextField(), gbc);

        gbc.gridy = 1;

        gbc.gridx = 0;
        mainWindow.add(new JLabel("Транскрипция"), gbc);
        gbc.gridx = 1;
        mainWindow.add(new JTextField(), gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        mainWindow.add(new JLabel("Перевод"), gbc);
        gbc.gridx = 1;
        mainWindow.add(new JTextField(), gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        mainWindow.add(new JLabel("Словари"), gbc);
        gbc.gridx = 1;
        mainWindow.add(new JCheckBox("HKS 1"), gbc);
        gbc.gridy = 4;
        mainWindow.add(new JCheckBox("HKS 2"), gbc);
        gbc.gridy = 5;
        mainWindow.add(new JCheckBox("HKS 3"), gbc);
        gbc.gridy = 6;
        mainWindow.add(new JCheckBox("HKS 4"), gbc);
        gbc.gridy = 7;
        mainWindow.add(new JCheckBox("HKS 5"), gbc);
        gbc.gridy = 8;
        mainWindow.add(new JCheckBox("HKS 6"), gbc);

        gbc.gridy = 9;
        gbc.gridx = 0;
        mainWindow.add(new JLabel("Категория"), gbc);
        gbc.gridx = 1;
        mainWindow.add(new JTextField(), gbc);

        gbc.gridy = 10;
        gbc.gridx = 0;
        mainWindow.add(new JButton("Сохранить"), gbc);

    }

    public void setDictionaryNames(String[] names)
    {
        dictionaryNames = new String[names.length];
        for(int i = 0; i < names.length; i++)
        {
            dictionaryNames[i] = names[i];
        }

    }

}
