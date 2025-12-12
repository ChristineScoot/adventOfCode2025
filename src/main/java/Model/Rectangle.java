package Model;

import Helper.Coord;

public class Rectangle implements Comparable<Rectangle> {
    Coord a;
    Coord b;
    long area;

    public Rectangle(Coord a, Coord b) {
        this.a = a;
        this.b = b;
        area = (long) (Math.abs(a.getX() - b.getX()) + 1) * (Math.abs(a.getY() - b.getY()) + 1);
    }

    public long getArea() {
        return area;
    }

    public Coord getA() {
        return a;
    }

    public Coord getB() {
        return b;
    }

    @Override
    public int compareTo(Rectangle o) {
        return Long.compare(o.getArea(), area);
    }
}
