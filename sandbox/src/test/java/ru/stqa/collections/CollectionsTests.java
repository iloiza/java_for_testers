package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CollectionsTests {

    @Test
    void arrayTests(){
        var array = new String[]{"a", "b", "c"};
        Assertions.assertEquals("a", array[0]);
        Assertions.assertEquals(3, array.length);
        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }

    @Test

    void listTests() {
        var list = new ArrayList<>(List.of("a", "b", "c"));

        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("a", list.get(0));

        list.set(0, "d");
        Assertions.assertEquals("d", list.get(0));
    }

    @Test
    void setTests(){
        var set = Set.copyOf(List.of("a","b","c", "a"));
        Assertions.assertEquals(3, set.size());

        set.add("g");
        Assertions.assertEquals(4, set.size());
        //var element = set.stream().findAny().get();
    }

    @Test
    void testMap(){
        var digits = new HashMap<Character, String>();
        digits.put('1', "one");
        digits.put('2', "two");
        digits.put('3', "three");
        Assertions.assertEquals("one", digits.get('1'));
    }
}
