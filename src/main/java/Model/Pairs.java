package Model;

import Helper.Coord3D;

public class Pairs implements Comparable<Pairs> {
    Coord3D node1;
    Coord3D node2;
    double length;

    public Pairs(Coord3D node1, Coord3D node2) {
        this.node1 = node1;
        this.node2 = node2;
        length = calculateLength();
    }

    private double calculateLength() {
        double subA = Math.pow(node1.getX() - node2.getX(), 2);
        double subB = Math.pow(node1.getY() - node2.getY(), 2);
        double subC = Math.pow(node1.getZ() - node2.getZ(), 2);
        return Math.sqrt(subA + subB + subC);
    }

    public double getLength() {
        return length;
    }

    public Coord3D getNode1() {
        return node1;
    }
    public Coord3D getNode2() {
        return node2;
    }

    @Override
    public int compareTo(Pairs o) {
        if (length - o.getLength() > 0)
            return 1;
        else if (length - o.getLength() == 0) {
            return 0;
        }
        return -1;
    }
}
