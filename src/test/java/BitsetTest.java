import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BitsetTest {

    @Test
    public void add() {
        Bitset set1 = new Bitset(3);
        List<Integer> expectedRes1 = Arrays.asList(0, 1, 2, 5);
        set1.add(5);
        Assertions.assertEquals(expectedRes1, set1.getSet());

        Bitset set2 = new Bitset(3);
        List<Integer> expectedRes2 = Arrays.asList(0, 1, 2, 5, 6, 7);
        set2.add(Arrays.asList(5, 6, 7));
        Assertions.assertEquals(expectedRes2, set2.getSet());
    }

    @Test
    public void contains() {
        Bitset set = new Bitset(3);
        Assertions.assertTrue(set.contains(2));
        Assertions.assertFalse(set.contains(3));
    }

    @Test
    public void size() {
        Bitset set = new Bitset(Arrays.asList(0, 2, 4, 10, 3, 53));
        Assertions.assertEquals(6, set.size());
    }

    @Test
    public void remove() {
        Bitset set = new Bitset(10);
        List<Integer> expectedRes = Arrays.asList(3, 5, 7, 9);
        set.remove(0);
        set.remove(1);
        set.remove(100);
        set.remove(Arrays.asList(0, 2, 4, 6, 8));
        Assertions.assertEquals(expectedRes, set.getSet());
    }

    @Test
    public void intersect() {
        Bitset set1 = new Bitset(5);
        Bitset set2 = new Bitset(10);
        List<Integer> expectedRes = Arrays.asList(0, 1, 2, 3, 4);
        Assertions.assertEquals(expectedRes, set1.intersect(set2).getSet());

        set1 = new Bitset(Arrays.asList(5, 6, 7));
        set2 = new Bitset(Arrays.asList(3, 4, 5, 6));
        Bitset set3 = new Bitset(Arrays.asList(0, 1));
        expectedRes = Arrays.asList(0, 1);
        Assertions.assertEquals(expectedRes, set3.intersect(set1.union(set2.complement())).getSet());

        set1 = new Bitset(Arrays.asList(0, 1, 2, 5));
        set2 = new Bitset(Arrays.asList(3, 5, 7));
        set3 = new Bitset(Arrays.asList(2, 3));
        expectedRes = List.of(2);
        Assertions.assertEquals(expectedRes, set1.intersect(set3.intersect(set2.complement())).getSet());
    }

    @Test
    public void union() {
        Bitset set1 = new Bitset(5);
        Bitset set2 = new Bitset(10);
        List<Integer> expectedRes = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assertions.assertEquals(expectedRes, set1.union(set2).getSet());

        set1 = new Bitset(Arrays.asList(0, 1, 2, 3, 5));
        set2 = new Bitset(Arrays.asList(7, 4, 2, 3));
        Bitset set3 = new Bitset(Arrays.asList(10, 3, 6, 12));
        expectedRes = Arrays.asList(0, 1, 2, 3, 5, 6, 10, 12);
        Assertions.assertEquals(expectedRes, set1.union(set3.intersect(set2.complement())).getSet());

        set1 = new Bitset(Arrays.asList(0, 2, 4, 6, 5));
        set2 = new Bitset(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        set3 = new Bitset(Arrays.asList(7, 8, 9, 10, 20));
        Bitset set4 = new Bitset(Arrays.asList(11, 21));
        expectedRes = Arrays.asList(11, 21);
        Assertions.assertEquals(expectedRes, set4.intersect(set1.union(set2.union(set3)).complement()).getSet());

        set1 = new Bitset(Arrays.asList(0, 2, 4, 6, 5));
        expectedRes = Arrays.asList(2, 4, 5, 6);
        set1.remove(0);
        Assertions.assertEquals(expectedRes, set1.union(set1).getSet());
    }

    @Test
    public void complement() {
        Bitset set1 = new Bitset(5);
        Bitset set2 = new Bitset(10);
        List<Integer> expectedRes = Arrays.asList(5, 6, 7, 8, 9);
        Assertions.assertEquals(expectedRes, set2.intersect(set1.complement()).getSet());
    }

    @Test
    public void iterator() {
        Bitset set1 = new Bitset(5);
        Bitset set2 = new Bitset(1);
        for (int elem : set1) {
            set2.add(elem);
        }
        List<Integer> expectedRes = Arrays.asList(0, 1, 2, 3, 4);
        Assertions.assertEquals(expectedRes, set2.getSet());
    }
}
