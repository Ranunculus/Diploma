package icons;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 14/02/13
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class WordCard
{
    private String word;
    private String pinyin;
    private List categories;
    private List dictionaries;
    private String translation; //todo: maybe - list?

    public WordCard(String word, String pinyin, List categories, List dictionaries, String translation)
    {
        this.word = word;
        this.pinyin = pinyin;
        this.categories = categories;
        this.dictionaries = dictionaries;
        this.translation = translation;
    }

    public WordCard( WordCard copy)
    {
        this.word = copy.word;
        this.pinyin = copy.pinyin;
        this.categories = copy.categories;
        this.dictionaries = copy.dictionaries;
        this.translation = copy.translation;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public List getCategories() {
        return categories;
    }

    public void setCategories(List categories) {
        this.categories = categories;
    }

    public List getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(List dictionaries) {
        this.dictionaries = dictionaries;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
