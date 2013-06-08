import database.CategoriesEntity;
import database.DictionariesEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 12/02/13
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class FiltersCheckBoxTreePanel extends JPanel
{
    public FiltersCheckBoxTreePanel(Session session)
    {
        Query query = session.createQuery("from DictionariesEntity");
        CheckBoxNode dictionaryFilterOptions[] = new CheckBoxNode[query.list().size()];
        for(int i = 0; i < query.list().size(); i++)
        {
            dictionaryFilterOptions[i] = new CheckBoxNode(((DictionariesEntity)query.list().get(i)).getName(), false);
        }
        //todo: add categories, that a user added during the session
        query = session.createQuery("from CategoriesEntity ");
        CheckBoxNode categoryFilterOptions[] = new CheckBoxNode[query.list().size()];
        for(int i = 0; i < query.list().size(); i++)
        {
            categoryFilterOptions[i] = new CheckBoxNode(((CategoriesEntity)query.list().get(i)).getCategory(), false);
        }
        //todo: to think about it
        CheckBoxNode radicalFilterOptions[] = {
                new CheckBoxNode("月", false),
                new CheckBoxNode("日", false),
                new CheckBoxNode("氵", false),
                new CheckBoxNode("夕", false),
                new CheckBoxNode("大", false),
                new CheckBoxNode("女", false),
                new CheckBoxNode("力", false),
                new CheckBoxNode("口", false),
                new CheckBoxNode("土", false),
                new CheckBoxNode("寸", false),
                new CheckBoxNode("目", false),
                new CheckBoxNode("火", false),
                new CheckBoxNode("灬", false),
                new CheckBoxNode("气", false) };

        Vector dictionaryVector = new NamedVector("Словари", dictionaryFilterOptions);
        Vector categoryVector = new NamedVector("Категории", categoryFilterOptions);
        Vector radicalVector = new NamedVector("Ключи", radicalFilterOptions); //todo think about multilingual interface, for the time being: russian and chinese?
        Object rootNodes[] = { dictionaryVector, categoryVector, radicalVector };
        Vector rootVector = new NamedVector("Root", rootNodes);
        JTree tree = new JTree(rootVector);
        CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
        tree.setCellRenderer(renderer);

        tree.setCellEditor(new CheckBoxNodeEditor(tree));
        tree.setEditable(true);
        //changing the size of tree window
        tree.setPreferredSize(new Dimension(195,600));
        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane, BorderLayout.CENTER);
        setSize(600, 500);
        this.setPreferredSize(new Dimension(200, 600));
//        this.setMinimumSize(new Dimension(900,900));
        setVisible(true);


//        this.add(tree);

    }

}
