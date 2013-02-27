package database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 21/02/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Links", schema = "", catalog = "")
@Entity
public class LinksEntity implements Serializable {
    protected int id;

    protected int word1Id;

    protected int word2Id;

    protected String linkType;


    public LinksEntity(WordsEntity newWordEntity, WordsEntity translationWordEntity, String newLinkType)
    {
        word1Id = newWordEntity.getId();
        word2Id = translationWordEntity.getId();
        linkType = newLinkType;

    }

    public LinksEntity() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinksEntity that = (LinksEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
    @javax.persistence.Column(name = "word_1_id")
    @Basic
    public int getWord1Id() {
        return word1Id;
    }

    public void setWord1Id(int word1Id) {
        this.word1Id = word1Id;
    }
    @javax.persistence.Column(name = "word_2_id")
    @Basic
    public int getWord2Id() {
        return word2Id;
    }

    public void setWord2Id(int word2Id) {
        this.word2Id = word2Id;
    }
    @javax.persistence.Column(name = "link_type")
    @Basic
    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    @Override
    public String toString() {
        return "LinksEntity{" +
                "id=" + id +
                ", word1Id=" + word1Id +
                ", word2Id=" + word2Id +
                ", linkType='" + linkType + '\'' +
                '}';
    }
}
