import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: malyutik
 * Date: 12/02/13
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
class NamedVector extends Vector {
    String name;

    public NamedVector(String name) {
        this.name = name;
    }

    public NamedVector(String name, Object elements[]) {
        this.name = name;
        for (int i = 0, n = elements.length; i < n; i++) {
            add(elements[i]);
        }
    }

    public String toString() {
        return "[" + name + "]";
    }
}