package Helper;

import java.util.Objects;

public class Coord3D implements Comparable<Coord3D> {
    int x;
    int y;
    int z;
    public String name;

    public Coord3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        name = "(" + x + ", " + y + ", " + z + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public Coord3D add(Coord3D coord) {
        int newX = this.x + coord.getX();
        int newY = this.y + coord.getY();
        int newZ = this.z + coord.getZ();
        return new Coord3D(newX, newY, newZ);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coord3D newCoord = (Coord3D) obj;
        return x == newCoord.getX() && y == newCoord.getY() && z == newCoord.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public boolean outOfBounds(int width, int height) {
        return !(x < height && x >= 0 && y < width && y >= 0);
    }

    @Override
    public int compareTo(Coord3D newCoord) {
        if (newCoord.getX() == x && newCoord.getY() == y && newCoord.getZ() == z)
            return 0;
        return -1;
    }
}
