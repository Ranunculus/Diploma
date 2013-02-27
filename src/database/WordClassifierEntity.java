package database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 21/02/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Word_classifier", schema = "", catalog = "")
@Entity
public class WordClassifierEntity {
    private int id;
    protected int wordId;
    protected int classifierId;

    public WordClassifierEntity(int newWordEntityId, Integer classifierId) {
        this.wordId = newWordEntityId;
        this.classifierId = classifierId.intValue();
    }

    public WordClassifierEntity() {
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
    @Column(name = "word_id")
    @Basic
    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }
    @Column(name = "classifier_id")
    @Basic
    public int getClassifierId() {
        return classifierId;
    }

    public void setClassifierId(int classifierId) {
        this.classifierId = classifierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordClassifierEntity that = (WordClassifierEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "WordClassifierEntity{" +
                "id=" + id +
                ", wordId=" + wordId +
                ", classifierId=" + classifierId +
                '}';
    }
}
