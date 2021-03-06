import database.WordsEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 8/02/13
 * Time: 9:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class DictionaryWindow extends JFrame
{
    JPanel mainWindow = (JPanel) getContentPane();
    FiltersCheckBoxTreePanel filterPanel;

    JButton addWordButton = new JButton("Добавить слово");
    public DictionaryWindow(Session session)
    {
        super("Словарь");
        filterPanel = new FiltersCheckBoxTreePanel(session);

        /**
         * Setting layout
         */

        GroupLayout layout = new GroupLayout(mainWindow);
        mainWindow.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        /**
         * Configuring panels
         */
        JLabel dictionaryLabel = new JLabel("Словарь");
        JLabel filterLabel = new JLabel("Фильтры");
        Object[] columnNames = {"","","Слово", "Транскрипция", "Перевод", "Словарь", "Категория"};


        ArrayList<WordsEntity> words = new ArrayList<WordsEntity>();

//        try {
                final ClassMetadata classMetadata = session.getSessionFactory().getClassMetadata(WordsEntity.class);
                System.out.println(classMetadata.toString());
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery("from " + entityName);

                if(entityName.equals("database.WordsEntity"))
                {

                    for (Object o : query.list()) {
                        if(((WordsEntity)o).getLanguage().equals("Chinese"))
                        {
//                            System.out.println("  " + o.toString());
                            words.add((WordsEntity)o);
                        }
                    }
                }
//
//        } finally {
//
//            session.flush();
//            session.close();
//        }
        Object[][] newData = new Object[words.size()][7];
        int i = 0;
        for(WordsEntity word : words)
        {
            newData[i][0] = false;
            newData[i][1] = "";
            newData[i][2] = word.getWord();
            newData[i][3] = word.getPronunciation();
            newData[i][4] = "Перевод";
            newData[i][5] = word.getDictionaries().isEmpty() ? "" : word.getDictionaries().get(0).getName();
            newData[i][6] = "";

            i++;
        }
        Object[][] data = {
                {false,"","和", "huo3", "огонь","HSK 3","Стихия"},
                {false,"","岁", "shuo", "вода", "HSK 2", "Стихия"},
                {false,"","知道", "zhi dao", "знать", "Кондрашевский", "Глагол"},
                {false,"","春天", "chun1 tian", "spring", "HSK 3", "Время года"},
                {false,"","冬天", "dong1 tian1", "зима", "HSK 3", "Время года"},
                {false,"","分钟", "fen1 zhong1", "минута", "HSK 1", "Время"},
                {false,"","刚才", "gang1 cai1", "сейчас", "HSK 3", "Время"},
                {false,"","过去", "guo4 qu5", "прошлое", "HSK 3", "Время"},
                {false,"","办公室", "bang4 gong1 shi4", "офис", "HSK 3", "Место"},
                {false,"","北京", "bei3 jing1", "Пекин", "HSK 1", "Город"},
                {false,"","对", "dui4", "opposite", "HSK 2", "Расположение"},
                {false,"","爱", "ai4", "любить", "HSK 1", "Чувство"},
                {false,"","把", "ba3", "держть", "HSK 3", "Действие"},
                {false,"","帮助", "bang1 zhu4", "помогать", "HSK 2", "Действие"},
                {false,"","向", "xiang4", "в направлении", "HSK 2", "Предлог"},
        };

        DictionaryTableModel dictionaryTableModel = new DictionaryTableModel(words, session);
        DictionaryTable dictionaryTable = new DictionaryTable(dictionaryTableModel, session);

        JScrollPane scrollPane = new JScrollPane(dictionaryTable);
        scrollPane.createVerticalScrollBar();
        scrollPane.setWheelScrollingEnabled(true);



        mainWindow.add(scrollPane);
        //following settings change only outer borders of panel
        filterPanel.setSize(new Dimension(200,600));
        filterPanel.setMaximumSize(new Dimension(200,600));
        filterPanel.setMinimumSize(new Dimension(225,600));
        filterPanel.setPreferredSize(new Dimension(225,600));
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(dictionaryLabel)
                                .addComponent(addWordButton))
                                //.addComponent(dictionaryLabel)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(dictionaryTable.getTableHeader())
                                .addComponent(scrollPane))

                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(filterLabel)
                        .addComponent(filterPanel))
        );

        layout.linkSize(SwingConstants.VERTICAL, dictionaryLabel, filterLabel);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(dictionaryLabel)
                                .addComponent(addWordButton))
                        .addComponent(filterLabel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(dictionaryTable.getTableHeader())
                                        .addComponent(scrollPane))
                                .addComponent(filterPanel)))
        );

        addWordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                WordCardWindow dictionaryWindow = new WordCardWindow("Новое слово");
                String[] filterNames = new String[]{"HSK 1", "HSK 2", "HSK 3", "HSK 4", "HSK 5", "HSK 6", "Кондрашевский", "Персональный"};
                dictionaryWindow.setDictionaryNames(filterNames);
                dictionaryWindow.setMinimumSize(new Dimension(400, 300));
                dictionaryWindow.setVisible(true);
            }
        });

        addWindowListener(new WindowAdapter( ) {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose( );
                System.exit(0);
            }
        });
        pack();
    }
}
