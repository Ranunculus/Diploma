package database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 21/02/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Classifiers", schema = "", catalog = "")
@Entity
public class ClassifiersEntity {

    public ClassifiersEntity(String classifier) {
        this.classifier = classifier;
    }

    public ClassifiersEntity()
    {

    }

    private int id;

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

    private String classifier;

    @javax.persistence.Column(name = "classifier")
    @Basic
    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassifiersEntity that = (ClassifiersEntity) o;

        if (id != that.id) return false;
        if (classifier != null ? !classifier.equals(that.classifier) : that.classifier != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (classifier != null ? classifier.hashCode() : 0);
        return result;
    }


}
