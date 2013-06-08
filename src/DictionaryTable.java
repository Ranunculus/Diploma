import org.hibernate.Session;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 11/02/13
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class DictionaryTable extends JTable
{
    CRUDCellRenderer crudCellRenderer;

    public DictionaryTable(DictionaryTableModel dictionaryTableModel, Session session)
    {
        super(dictionaryTableModel);
        setFont(new Font("Arial", Font.PLAIN,12));//todo: 1t column AR PL UKai CN other - Arial

        getColumnModel().getColumn(0).setPreferredWidth(12);
        getColumnModel().getColumn(0).setMaxWidth(12);
        getColumnModel().getColumn(1).setPreferredWidth(40);
        getColumnModel().getColumn(1).setMaxWidth(40);

        setPreferredScrollableViewportSize(new Dimension(50, 50));

        crudCellRenderer = new CRUDCellRenderer(this,session);
    }

    /**
     * Makes first column look like buttons
     * @param row
     * @param column
     * @return
     */
    public TableCellRenderer getCellRenderer(int row, int column)
    {
        return column == 1? crudCellRenderer : super.getCellRenderer(row,column);
    }

    /**
     * Makes zero column read boolean type
     * @param index
     * @return
     */
    public Class getColumnClass(int index)
    {
        return index != 0 ? getValueAt(0, index).getClass(): Boolean.class;
    }

    public TableCellEditor getCellEditor(int row, int column)
    {
        return column == 1 ? crudCellRenderer : super.getCellEditor(row,column);
    }
}
