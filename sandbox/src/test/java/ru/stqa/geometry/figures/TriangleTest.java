package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {
    @Test
    void canCalculatePerimeter(){
        double a = 3.0, b = 4.0, c = 5.0;
        Triangle.printTrianglePerimeter(new Triangle(a, b, c));
        Assertions.assertEquals(12.0, new Triangle(a, b, c).perimeter());
    }
    @Test
    void canCalculateArea(){
        double a = 3.0, b = 4.0, c = 5.0;
        var t = new Triangle(a, b, c);
        var result = t.area();
        Assertions.assertEquals(6.0, result);
    }

    @Test
    void cannotCreateTriangleWithWrongSides(){
        try{
            new Triangle(1.0, 1.5, 6.0);
            Assertions.fail();
        }catch (IllegalArgumentException ex) {
            //OK
        }
    }
    @Test
    void testEquality() {
        var t1 = new Triangle(6.0, 5.0, 4.0);
        var t2 = new Triangle(6.0, 4.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }

}
