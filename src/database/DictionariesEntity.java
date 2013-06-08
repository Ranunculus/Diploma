package database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 21/02/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Dictionaries", schema = "", catalog = "")
@Entity
public class DictionariesEntity {
    private int id;

    @javax.persistence.Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    @javax.persistence.Column(name = "name")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        boolean value = false;
        if (this == o)
        {
            value = true;
        }
        if (o == null || getClass() != o.getClass())
        {
            value =  false;
        }

        DictionariesEntity that = (DictionariesEntity) o;
        System.out.println("Here");
        if (id != that.id)
        {
            value = false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null)
        {
            value = false;
        }
        System.out.println(name + " <> " + that.name);
        System.out.println(value);
        return value;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DictionariesEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
