import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 11/02/13
 * Time: 12:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class CRUDCellRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor
{
    JPanel panel = new JPanel();

    public CRUDCellRenderer(final DictionaryTable dictionaryTable)
    {
        super();
        panel.setLayout(new BorderLayout());
        JButton deleteButton = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();

                String[] options = {"Да", "Нет"};
                int chosenOption = JOptionPane.showOptionDialog(panel,
                        "Вы уверены, что хотите удалить эту строку?",
                        "Удаление слова",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);
                if(chosenOption == 0)
                {
                    ((DictionaryTableModel)dictionaryTable.getModel()).removeRow(dictionaryTable.getSelectedRow());
                } else {
                    //todo: delete this kostil
                    ((DictionaryTableModel)dictionaryTable.getModel()).fireTableChanged(new TableModelEvent(((DictionaryTableModel)dictionaryTable.getModel()), 0, ((DictionaryTableModel)dictionaryTable.getModel()).getRowCount(),
                            TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
                }

            }
        });

        deleteButton.setPreferredSize(new Dimension(20,11));

        try {
            Image myImage = ImageIO.read(getClass().getResource("icons/cross.png"));
            ImageIcon myIcon = new ImageIcon(myImage);
            deleteButton.setIcon(myIcon);
        } catch (IOException e) {
            System.out.println("File not found: " + e);
        }
        panel.setOpaque(true);
        panel.add(deleteButton,BorderLayout.EAST);

        JButton updateButton = new JButton(new AbstractAction() {;
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                WordCardWindow dictionaryWindow = new WordCardWindow("Editing");
                String[] filterNames = new String[]{"HSK 1", "HSK 2", "HSK 3", "HSK 4", "HSK 5", "HSK 6", "Кондрашевский", "Персональный"};
                dictionaryWindow.setDictionaryNames(filterNames);
                dictionaryWindow.setMinimumSize(new Dimension(400, 300));
                dictionaryWindow.setVisible(true);
                fireEditingStopped();

            }
        });
        updateButton.setPreferredSize(new Dimension(20,11));
        try {
            Image myImage = ImageIO.read(getClass().getResource("icons/pencil.png"));
            ImageIcon myIcon = new ImageIcon(myImage);
            updateButton.setIcon(myIcon);

        } catch (IOException e) {
            System.out.println("File not found: " + e);
        }
        panel.add(updateButton, BorderLayout.WEST);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        return panel;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        panel.setBackground(table.getSelectionBackground());
        return panel;
    }
    @Override
    public Object getCellEditorValue() {
        panel.setVisible(true);
        return new JTextField();
    }


}
