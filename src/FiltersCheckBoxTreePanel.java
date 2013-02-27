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
    public FiltersCheckBoxTreePanel()
    {
        CheckBoxNode dictionaryFilterOptions[] = {
                new CheckBoxNode("HSK 一", false),
                new CheckBoxNode("HSK 二", false),
                new CheckBoxNode("HSK 三", false),
                new CheckBoxNode("HSK 四", false),
                new CheckBoxNode("HSK 五", false),
                new CheckBoxNode("HSK 六", false),
                new CheckBoxNode("Кондрашевский", false),
                new CheckBoxNode("Персональный", false) };
        CheckBoxNode categoryFilterOptions[] = {
                new CheckBoxNode("Города", false),
                new CheckBoxNode("Страны", false),
                new CheckBoxNode("Погода", false),
                new CheckBoxNode("Время", false),
                new CheckBoxNode("Времена года", false) };
        CheckBoxNode radicalFilterOptions[] = {
                new CheckBoxNode("月", false),
                new CheckBoxNode("日", true),
                new CheckBoxNode("氵", true),
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

        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane, BorderLayout.LINE_START);
        setSize(400, 500);
        setVisible(true);

//        this.add(tree);

    }
}
