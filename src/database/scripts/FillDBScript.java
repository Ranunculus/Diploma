package database.scripts;

import database.ClassifiersEntity;
import database.DictionariesEntity;
import database.LinksEntity;
import database.WordsEntity;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 27/02/13
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class FillDBScript
{
    public FillDBScript(Session session, String excelFileName) throws IOException, BiffException {


        /**
         * Turning on foreign_key;
         */
    //        Query queryForeignKeyOn = session.createQuery("");
        /**
         * For saving entities
         */
        Transaction tx;

        Workbook HSK1xls = Workbook.getWorkbook(new File(excelFileName));
        Sheet sheet = HSK1xls.getSheet(0);

    //        System.out.println(sheet.getCell(4,5).getContents());

        String word;
        String translation; // = sheet.getCell(4,5).getContents();
        String[] classifiers;
        String pinyin;
        Integer dictionary;

        for (int i = 2; i <= 5005; i++ )
        {
            /**
             *  Splitting translation itself and word classifiers
             */

            dictionary = Integer.parseInt(sheet.getCell(0,i).getContents());
            if(dictionary == 1)
            {
                /**
                 * Setting dictionary
                 */
                Query query = session.createQuery("from DictionariesEntity");
                List<DictionariesEntity> wordDictionaries = new ArrayList<DictionariesEntity>();

    //                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    if(((DictionariesEntity)o).getId() == dictionary)
                    {
                        wordDictionaries.add(((DictionariesEntity) o));
                        break;
                    }
                }

                word = sheet.getCell(1,i).getContents();
                translation = sheet.getCell(3,i).getContents();
                pinyin = sheet.getCell(2,i).getContents();

                List<ClassifiersEntity> wordClassifiers = new ArrayList<ClassifiersEntity>();
                if(translation.contains("CL:"))
                {
                    String substringWithClassifiers = translation.substring(translation.indexOf("CL") + 3);
                    translation = translation.substring(0,translation.indexOf("CL:"));

                    String pattern = "[\\[\\w\\],]";
                    classifiers = substringWithClassifiers.split(pattern);
                    for(String classifier : classifiers) {
                        if (!classifier.trim().equals("")) {
                            /**
                             *  Didn't manage to find normal regular expression to cut '?|' sequence
                             */
                            query = session.createQuery("from ClassifiersEntity");
                            System.out.println("Pure : " + classifier);
                            String changedClassifier = classifier.contains("|") ? (classifier.substring(classifier.indexOf("|") + 1)) : classifier;
                            Pattern p = Pattern.compile("\\p{Punct}++|\\p{Punct}\\p{Space}++|\\p{Punct}\\p{Space}\\p{Punct}");
                            Matcher m = p.matcher(changedClassifier.trim());
                            if(!m.matches())
                            {

                                System.out.println("Changed : " + changedClassifier);
                                boolean contains = false;
                                for (Object o : query.list()) {
                                    contains = ((ClassifiersEntity) o).getClassifier().equals(changedClassifier);
                                    if(contains)
                                    {
                                        wordClassifiers.add(((ClassifiersEntity) o));
                                        break;
                                    }
                                }
                                if(!contains)
                                {
                                    ClassifiersEntity newClassifier = new ClassifiersEntity(changedClassifier);
                                    session.save(newClassifier);
                                    wordClassifiers.add(newClassifier);
                                }
                            }
                        }
                    }
                }

                /**
                 * Checking, if word already exists
                 */
                query = session.createQuery("from WordsEntity");
    //                System.out.println("executing: " + query.getQueryString());
                boolean contains = false;
                for (Object o : query.list()) {
                    contains = ((WordsEntity) o).getWord().equals(word);

                    if(contains)
                    {
                        break;
                    }
                }
    //                System.out.println(contains);
                if(!contains)
                {
                    tx = session.beginTransaction();
                    /**
                     * Adding a word and it's translation
                     */
                    WordsEntity newTranslationWordEntity = new WordsEntity(translation,"","English");

                    session.save(newTranslationWordEntity);

                    tx.commit();
                    session.flush();

                    tx = session.beginTransaction();
    //                    System.out.println(newTranslationWordEntity.getId());
                    ArrayList<WordsEntity> translations = new ArrayList<WordsEntity>();
                    translations.add(newTranslationWordEntity);
    //                    System.out.println(translations.size());
                    WordsEntity newWordEntity = new WordsEntity(word,pinyin,"Chinese",wordClassifiers, wordDictionaries);

    //                    System.out.println(newWordEntity.toString());
                    session.save(newWordEntity);

                    tx.commit();
                    session.flush();
                    tx = session.beginTransaction();

    //                    System.out.println(newWordEntity.toString());
                    LinksEntity linksEntity = new LinksEntity(newWordEntity, newTranslationWordEntity,"Translation");
                    session.save(linksEntity);

    //                    System.out.println(linksEntity.toString());
                    tx.commit();
                    session.flush();

                    //                /**
                    //                 * Adding links between classifiers and chinese word
                    //                 */
                    //                tx = session.beginTransaction();
                    //
                    //                System.out.println(newWordEntity.toString());
                    //                for(Integer wordClassifierId : wordClassifiersIds)
                    //                {
                    //                    System.out.println(wordClassifierId);
                    //                    WordClassifierEntity wordClassifierEntity = new WordClassifierEntity(newWordEntity.getId(), wordClassifierId);
                    //                    session.save(wordClassifierEntity);
                    //                    System.out.println(wordClassifierEntity.toString());
                    //
                    //                }
                    //                tx.commit();
                    //                session.flush();

                }


                /**
                 * Parsing translation cell and adding classifiers
                 */
            }

        }
    }
}
