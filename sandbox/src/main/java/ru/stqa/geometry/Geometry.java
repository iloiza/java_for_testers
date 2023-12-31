package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(4.5));
        Square.printSquareArea(new Square(9.0));
        Square.printSquareArea(new Square(2));

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(5.0, 2.0);
    }

}
