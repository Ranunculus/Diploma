import javax.swing.table.DefaultTableModel;

/**
 * Make it possible to show and edit boolean type in the zero's column
 *
 * User: malyutik
 * Date: 11/02/13
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class DictionaryTableModel extends DefaultTableModel
{
    public DictionaryTableModel(Object[][] data, Object[] columnNames)
    {
        super(data, columnNames);
    }

    @Override
    public Class getColumnClass(int index)
    {
        return index != 0 ? String.class : Boolean.class;
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return column == 0 || column == 1;
    }

}
