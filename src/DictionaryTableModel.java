import database.CategoriesEntity;
import database.DictionariesEntity;
import database.WordsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<WordsEntity> words;
    protected String[] columnNames = {"",
            "",
            "Слово",
            "Транскрипция",
            "Перевод",
            "Словарь",
            "Категория"};

    private Session session;

    public DictionaryTableModel(ArrayList<WordsEntity> data, Session session)
    {
        super();
        this.session = session;
        words = new ArrayList<WordsEntity>(data);
        this.setDataVector(convertToVector(data.toArray()), convertToVector(new String[]{"",
                "",
                "Слово",
                "Транскрипция",
                "Перевод",
                "Словарь",
                "Категория"}));

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
    @Override
    public int getRowCount()
    {
        return words == null ? 0 : words.size();

    }

    @Override
    public int getColumnCount() {
        return 7;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "";
        WordsEntity word = words.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = false;
                break;
            case 1:
                break;
            case 2:
                value = word.getWord();
                break;
            case 3:
                value = word.getPronunciation();
                break;
            case 4:
                value = word.getTranslation(session);
                break;
            case 5:

                value = "";
                for(DictionariesEntity dictionary : word.getDictionaries())
                {
                    value = value.toString().concat(dictionary.getName());
                }
//                System.out.println("for word " + word.getWord() + " dict : " + word.getDictionaries().toString());
                break;
            case 6:
                value = "";
                for(CategoriesEntity dictionary : word.getCategories())
                {
                    value = value.toString().concat(dictionary.getCategory());
                }
                break;
        }

        return value;

    }

    public WordsEntity getWordAt(int row) {
        return words.get(row);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public void removeRow(int row) {
        dataVector.removeElementAt(row);
        int deletingWordId = words.get(row).getId();

        Transaction tx;

        /**
         * Deleting from database
         */
        tx = session.beginTransaction();
        session.delete(words.get(row));
        tx.commit();
        session.flush();
        words.remove(row);

        /**
         * Getting translation word id
         */
        tx = session.beginTransaction();
        // TODO: Use prepared statement
        List deletingWordTranslationId = session.createQuery("select link.word2Id from LinksEntity link where link.word1Id = " + deletingWordId).list();
        System.out.println(deletingWordTranslationId);
        tx.commit();
        session.flush();
        /**
         * Deleting link
         */
        tx = session.beginTransaction();
        session.createQuery("delete from LinksEntity link where link.word1Id = " + deletingWordId).executeUpdate();
        tx.commit();
        session.flush();
        /**
         * Deleting translation
         */
        tx = session.beginTransaction();
        session.createQuery("delete from WordsEntity word where word.id = " + deletingWordTranslationId.get(0)).executeUpdate();
        tx.commit();
        session.flush();

        fireTableRowsDeleted(row, row);
    }
}
