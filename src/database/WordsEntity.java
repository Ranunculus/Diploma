package database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 21/02/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Words", schema = "", catalog = "")
@Entity
public class WordsEntity implements Serializable {


    public WordsEntity(String word, String pronunciation, String language)
    {
        this.word = word;
        this.pronunciation = pronunciation;
        this.language = language;

    }
    public WordsEntity(String word, String pronunciation, String language, List<ClassifiersEntity> classifiersEntities) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.language = language;
        this.classifiers = classifiersEntities;
    }
    private int id;

    public WordsEntity() {
    }

    public WordsEntity(String word, String pinyin, String chinese, List<ClassifiersEntity> wordClassifiers, List<DictionariesEntity> wordDictionaries) {
        this.word = word;
        this.pronunciation = pinyin;
        this.language = chinese;
        this.classifiers = wordClassifiers;
        this.dictionaries = wordDictionaries;
    }

    @javax.persistence.Column(name = "id")
    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String word;

    @javax.persistence.Column(name = "word")
    @Basic
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    private String pronunciation;

    @javax.persistence.Column(name = "pronunciation")
    @Basic
    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }


    private String language;


    @javax.persistence.Column(name = "language")
    @Basic
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    protected List<ClassifiersEntity> classifiers;

    @ManyToMany
    @JoinTable(name = "Word_Classifier",
            joinColumns = @JoinColumn(name = "word_id"),
            inverseJoinColumns = @JoinColumn(name = "classifier_id"))
    public List<ClassifiersEntity> getClassifiers() {
        return classifiers;
    }

    public void setClassifiers(List<ClassifiersEntity> classifiers) {
        this.classifiers = classifiers;
    }

    protected List<DictionariesEntity> dictionaries;

    @ManyToMany
    @JoinTable(name = "Word_Dictionary",
            joinColumns = @JoinColumn(name = "word_id"),
            inverseJoinColumns = @JoinColumn(name = "dictionary_id"))
    public List<DictionariesEntity> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(List<DictionariesEntity> dictionaries) {
        this.dictionaries = dictionaries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordsEntity that = (WordsEntity) o;

        if (id != that.id) return false;
        if (language != that.language) return false;
        if (pronunciation != null ? !pronunciation.equals(that.pronunciation) : that.pronunciation != null)
            return false;
        if (word != null ? !word.equals(that.word) : that.word != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (word != null ? word.hashCode() : 0);
        result = 31 * result + (pronunciation != null ? pronunciation.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WordsEntity{" +
                ", id=" + id +
                ", word='" + word + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
