import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BitsetTest {

    @Test
    public void add() {
        Bitset set1 = new Bitset(3);
        ArrayList<Integer> expectedRes1 = new ArrayList<>(Arrays.asList(0,1,2,5));
        set1.add(5);
        Assertions.assertEquals(expectedRes1, set1.getSet());

        Bitset set2 = new Bitset(3);
        ArrayList<Integer> expectedRes2 = new ArrayList<>(Arrays.asList(0,1,2,5,6,7));
        set2.add(Arrays.asList(5,6,7));
        Assertions.assertEquals(expectedRes2, set2.getSet());
    }

    @Test
    public void contains() {
        Bitset set = new Bitset(3);
        Assertions.assertTrue(set.contains(2));
        Assertions.assertFalse(set.contains(3));
    }

    @Test
    public void remove() {
        Bitset set = new Bitset(10);
        ArrayList<Integer> expectedRes = new ArrayList<>(Arrays.asList(3,5,7,9));
        set.remove(0);
        set.remove(1);
        set.remove(100);
        set.remove(Arrays.asList(0,2,4,6,8));
        Assertions.assertEquals(expectedRes, set.getSet());
    }

    @Test
    public void intersect() {
        Bitset set1 = new Bitset(5);
        Bitset set2 = new Bitset(10);
        ArrayList<Integer> expectedRes = new ArrayList<>(Arrays.asList(0,1,2,3,4));
        Assertions.assertEquals(expectedRes, set1.intersect(set2).getSet());

    }

    @Test
    public void union() {
        Bitset set1 = new Bitset(5);
        Bitset set2 = new Bitset(10);
        ArrayList<Integer> expectedRes = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        Assertions.assertEquals(expectedRes, set1.union(set2).getSet());
    }

    @Test
    public void complement() {
        Bitset set1 = new Bitset(5);
        Bitset set2 = new Bitset(10);
        ArrayList<Integer> expectedRes = new ArrayList<>(Arrays.asList(5,6,7,8,9));
        Assertions.assertEquals(expectedRes, set1.complement(set2).getSet());
    }

    @Test
    public void iterator(){
        Bitset set1 = new Bitset(5);
        Bitset set2 = new Bitset(0);
        for (int elem: set1) {
            set2.add(elem);
        }
        ArrayList<Integer> expectedRes = new ArrayList<>(Arrays.asList(0,1,2,3,4));
        Assertions.assertEquals(expectedRes, set2.getSet());

    }
}
