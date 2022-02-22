import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.valueOf;

/*
Вариант 18 -- bitset
Реализовать множество над заданным набором объектов.
Количество элементов в наборе задается в конструкторе.
Конкретный элемент набора идентифицируется неотрицательным целым от нуля до количества элементов - 1 (альтернатива -- уникальным именем).
Операции: пересечение, объединение, дополнение; добавление/удаление заданного элемента (массива элементов), проверка принадлежности элемента множеству.
Бонус: итератор по множеству.
 */
public final class Bitset implements Iterable<Integer>{

    private final ArrayList<Integer> elements;

    public Bitset(int size) {
        elements = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            elements.add(i);
        }
    }

    public Bitset(ArrayList<Integer> elements) {
        this.elements = elements;
    }

    public List<Integer> getSet(){
        return elements;
    }

    public void  add(int elem) {
        if (!elements.contains(elem)) {
            elements.add(elem);
        }
    }

    public void add(List<Integer> ListOfElem) {
        for (int elem: ListOfElem) {
            if (!elements.contains(elem)){
                elements.add(elem);
            }
        }
    }

    public void remove(int elem) {
        if (elements.contains(elem)) {
            elements.remove(valueOf(elem));
        }
    }

    public void remove(List<Integer> ListOfElem) {
        for (int elem: ListOfElem) {
            if (elements.contains(elem)) {
                elements.remove(valueOf(elem));
            }
        }
    }

    public boolean contains(int elem) {
        return elements.contains(elem);
    }

    public Bitset intersect(Bitset other) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int elem: this.elements) {
            if (other.contains(elem)) {
                res.add(elem);
            }
        }
        return new Bitset(res);
    }

    public Bitset union(Bitset other) {
        other.add(this.elements);
        return other;
    }

    public Bitset complement(Bitset other) {
        for (int elem: this.elements) {
            if (other.contains(elem)) other.remove(elem);
        }
        return other;
    }

    @Override
    public Iterator <Integer> iterator() {
        return new MyIterator<>(elements);
    }

    public static class MyIterator<Integer> implements Iterator<Integer> {

        int curr = 0;
        ArrayList<Integer> list;

        public MyIterator(ArrayList<Integer> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return list.size() >= curr + 1;
        }

        @Override
        public Integer next() {
            Integer value = list.get(curr);
            curr++;
            return value;
        }
    }
}