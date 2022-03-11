import java.util.*;


/*
Вариант 18 -- bitset
Реализовать множество над заданным набором объектов.
Количество элементов в наборе задается в конструкторе.
Конкретный элемент набора идентифицируется неотрицательным целым от нуля до количества элементов - 1 (альтернатива -- уникальным именем).
Операции: пересечение, объединение, дополнение; добавление/удаление заданного элемента (массива элементов), проверка принадлежности элемента множеству.
Бонус: итератор по множеству.
*/


public class Bitset implements Iterable<Integer> {

    private final ArrayList<Boolean> elements;

    public static class Universum {

        private final Bitset set;

        public Universum(Bitset set) {
            for (int i = 0; i < set.elements.size(); i++) {
                set.elements.set(i, !set.elements.get(i));
            }
            this.set = set;
        }

    }

    public Bitset(int size) {
        elements = new ArrayList<>();
        for (int i=0; i < size; i++) {
            elements.add(i, true);
        }
    }

    public List<Integer> getSet(){
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i)) {
                res.add(i);
            }
        }
        return res;
    }


    public Bitset(List<Integer> elems) {
        elements = new ArrayList<>();
        for (int i = 0; i <= Collections.max(elems); i++) {
            if (elems.contains(i)) {
                elements.add(i, true);
            }
            else {
                elements.add(i, false);
            }
        }
    }
    public void add(int elem) {
        if (elem >= this.elements.size()) {
            for (int i = this.elements.size(); i < elem; i++) {
                elements.add(i, false);
            }
            elements.add(elem, true);
        }
        else elements.set(elem, true);
    }

    public void add(List<Integer> elems) {
        for (int elem: elems) {
            if (elem >= this.elements.size()) {
                for (int i = this.elements.size(); i < elem; i++) {
                    elements.add(i, false);
                }
                elements.add(elem, true);
            }
            else elements.set(elem, true);
        }
    }

    public void remove(int elem) {
        if (elem < this.elements.size())
        elements.set(elem, false);
    }

    public void remove(List<Integer> elems) {
        for (int elem: elems) {
            if (elem < this.elements.size())
                elements.set(elem, false);
        }
    }

    public boolean contains(int elem) {
        return elements.size() > elem && elements.get(elem);
    }

    public Bitset union(Bitset other) {
        for (int i = 0; i < other.elements.size(); i++) {
            if (this.elements.size() > i) {
                if (other.elements.get(i)) {
                    this.elements.set(i, true);
                }
            }
            else this.elements.add(i, other.elements.get(i));
        }
        return this;
    }

    public Bitset intersect(Bitset other) {
        for (int i = 0; i < this.elements.size(); i++) {
            if (other.elements.size() <= i || !other.elements.get(i)) {
                this.elements.set(i, false);
            }
        }
        return this;
    }

    public Universum complement(){
        return new Universum(this);
    }

    public Bitset intersect(Universum other) {
        for (int i = 0; i < this.elements.size(); i++) {
            if (!(this.contains(i) && (other.set.contains(i) || other.set.elements.size() <= i))) {
                this.elements.set(i, false);
            }
        }
        return this;
    }

    public Universum union(Universum other) {
        other.set.union(this);
        return other;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyIterator<Integer>(this.getSet());
    }

    public static class MyIterator<Integer> implements Iterator<Integer> {

        int curr = 0;
        List<Integer> list;

        public MyIterator(List<Integer> list) {
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