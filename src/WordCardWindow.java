import database.CategoriesEntity;
import database.DictionariesEntity;
import database.WordsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        //todo: normal editor
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
        //todo: save changes
        mainWindow.add(new JButton("Сохранить"), gbc);

    }
    //todo: make one WordCardWindow
    public WordCardWindow(String editing, final WordsEntity word, final Session session, final DictionaryTableModel model) {
        super(editing +" \"" + word.getWord() + "\"");
        mainWindow.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int row = 0;
        gbc.gridy = row;

        gbc.gridx = 0;
        mainWindow.add(new JLabel("Слово"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 4;
        final JTextField wordTextField = new JTextField();
        wordTextField.setText(word.getWord());
        mainWindow.add(wordTextField, gbc);

        gbc.gridy = ++row;

        gbc.gridx = 0;
        mainWindow.add(new JLabel("Транскрипция"), gbc);
        gbc.gridx = 1;
        final JTextField pronunciationTextField = new JTextField();
        pronunciationTextField.setText(word.getPronunciation());
        mainWindow.add(pronunciationTextField, gbc);

        gbc.gridy = ++row;
        gbc.gridx = 0;
        mainWindow.add(new JLabel("Перевод"), gbc);
        gbc.gridx = 1;
        gbc.gridheight = 3;
        final JTextArea translationTextArea= new JTextArea(5,5);
        translationTextArea.setText(word.getTranslation(session));
        translationTextArea.setLineWrap(true);
        mainWindow.add(translationTextArea, gbc);
        gbc.gridheight = 1;
        gbc.gridy = row+=3;
        gbc.gridx = 0;


        mainWindow.add(new JLabel("Словари"), gbc);
        gbc.gridx = 1;

        final ArrayList dictionaries = (ArrayList) session.createQuery("from DictionariesEntity").list();
        final JCheckBox[] dictionary = new JCheckBox[dictionaries.size()];
        for(int i = 0; i < dictionaries.size() ; i++ )
        {
            dictionary[i] = new JCheckBox(((DictionariesEntity)dictionaries.get(i)).getName(), word.getDictionaries().contains((dictionaries.get(i))));
            mainWindow.add(dictionary[i], gbc);
            gbc.gridy = ++row;
        }

        gbc.gridy = ++row;
        gbc.gridx = 0;
        mainWindow.add(new JLabel("Категория"), gbc);
        gbc.gridx = 1;
        final JTextField categoryTextField = new JTextField();
        System.out.println("======>" + word.getCategories());
        if(word.getCategories().toString().equals("[]"))
        {
            categoryTextField.setText("");
        } else {
            String text = "";
            for(CategoriesEntity categories : word.getCategories())
            {
                text = text.concat(categories.getCategory() + ",");
            }
            categoryTextField.setText(text);
        }

        mainWindow.add(categoryTextField, gbc);


        gbc.gridy = ++row;
        gbc.gridx = 0;

        //todo: save changes
        JButton saveButton = new JButton("Сохранить");
        mainWindow.add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Boolean changed = false;
                if (!word.getWord().equals(wordTextField.getText())) {
                    word.setWord(wordTextField.getText());
                    changed = true;
                }
                if (!word.getTranslation(session).equals(translationTextArea.getText())) {
                    word.setTranslation(translationTextArea.getText());//todo
                    changed = true;
                }
                if (!word.getPronunciation().equals(pronunciationTextField.getText())) {
                    word.setPronunciation(pronunciationTextField.getText());
                    changed = true;
                }
//                if(!word.getCategories().toString().equals("[]"))
//                {
//                    System.out.println(categoryTextField.getText());
//                    System.out.println(categoryTextField.getText().equals(""));
//                    if(!word.getCategories().get(0).getCategory().equals(categoryTextField.getText())){
//                        word.addCategory(categoryTextField.getText());
//                        changed = true;
//                    }
//                }
//                System.out.println(Arrays.toString(word.getCategories().toArray()));
//                System.out.println(categoryTextField.getText());
//                if(!Arrays.toString(word.getCategories().toArray()).equals(categoryTextField.getText()))
//                {
//                    word.getCategories().add(new CategoriesEntity(categoryTextField.getText()));
//                    changed = true;
//                }
                String currentWord = "";
                boolean categoryContains = false;
                //todo: регулярные выражения
                for (int i = 0; i < categoryTextField.getText().length(); i++) {
                    currentWord = currentWord.concat(String.valueOf(categoryTextField.getText().charAt(i)));
                    System.out.println("currentWord = " + currentWord);
                    if (i != categoryTextField.getText().length() - 1) {
                        System.out.println(String.valueOf(categoryTextField.getText().charAt(i + 1)));
                        System.out.println(String.valueOf(categoryTextField.getText().charAt(i + 1)));
                        if ((String.valueOf(categoryTextField.getText().charAt(i + 1)).equals(",") || String.valueOf(categoryTextField.getText().charAt(i + 1)).equals(""))) {
                            System.out.println("currentWord = " + currentWord);
                            for (CategoriesEntity category : word.getCategories()) {
                                if (category.getCategory().equals(currentWord)) {
                                    categoryContains = true;
                                }
                            }
                            if (!categoryContains) {
                                word.getCategories().add(new CategoriesEntity(currentWord));
                            }
                            currentWord = "";
                        }
                    } else {
                        System.out.println("currentWord = " + currentWord);
                        for (CategoriesEntity category : word.getCategories()) {
                            if (category.getCategory().equals(currentWord)) {
                                categoryContains = true;
                            }
                        }
                        if (!categoryContains) {
                            word.getCategories().add(new CategoriesEntity(currentWord));
                        }
                        currentWord = "";
                    }
                }
                for (int i = 0; i < word.getCategories().size(); i++) {
                    if (!categoryTextField.getText().contains(word.getCategories().get(i).getCategory())) {
                        word.getCategories().add(new CategoriesEntity(""));
                        changed = true;
                    }
                }
                System.out.println("length = " + dictionary.length);
                System.out.println("word.getDictionaries().size() = " + word.getDictionaries().size());
                System.out.println("word.getDictionaries() = " + word.getDictionaries().toString());
                for (int i = 0; i < dictionary.length; i++) {

                    if (dictionary[i].isSelected()) {
                        for (int j = 0; j < word.getDictionaries().size(); j++) {
                            if (!word.getDictionaries().get(j).getName().equals(dictionary[i].getText())) {
                                word.getDictionaries().add((DictionariesEntity) dictionaries.get(i));
                                changed = true;
                            }
                        }
                    }
                    if (!dictionary[i].isSelected()) {
                        for (int j = 0; j < word.getDictionaries().size(); j++) {
                            if (word.getDictionaries().get(j).getName().equals(dictionary[i].getText())) {
                                word.getDictionaries().remove(dictionaries.get(i));
                                changed = true;
                            }
                        }
                    }

                }
                System.out.println(word.toString());
                if (changed) {
                    Transaction transaction = session.beginTransaction();
                    session.saveOrUpdate(word);
                    transaction.commit();
                    session.flush();
                    model.fireTableDataChanged();
                }
                setVisible(false);
            }
        });


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
