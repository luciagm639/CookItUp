package CookItUpWeb.auxiliary;

import java.util.LinkedList;
import java.util.List;

public class ListAuxiliary {

    public static <E> List<E> fromIterableToList(Iterable<E> iterable) {
        List<E> list = new LinkedList<>();
        for (E e : iterable) {
            list.add(e);
        }
        return list;
    }
}
