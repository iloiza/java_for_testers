package ru.stqa.geometry.figures;

import java.util.Objects;

public class Triangle {
    double a;
    double b;
    double c;

    public Triangle(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;

        if (a < 0 || b < 0 || c < 0 ){
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if ((a+b) < c || (b+c) < a || (c+a) < b)
            throw new IllegalArgumentException("Sum of two triangle sides should be > the third");
    }
    public static void printTrianglePerimeter(Triangle t) {
        String text = String.format("Периметр треугольника со сторонами %f, %f и %f = %f", t.a, t.b, t.c, t.perimeter());
        System.out.println(text);
    }
    public double perimeter() {
        return this.a + this.b + this.c;
    }

        public double area () {
        double semiPerimeter = perimeter() / 2;
            //S = √(p * (p — a) * (p — b) * (p — c)) -- формула Герона
        return Math.sqrt(semiPerimeter *
                (semiPerimeter - this.a) *
                (semiPerimeter - this.b) *
                (semiPerimeter - this.c));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return  (Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.c) == 0)
               ||  (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.b) == 0)
                ||  (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.a) == 0)
                || (Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.b) == 0)
                ||  (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.a) == 0)
                ||  (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.c) == 0);


    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
