public record Vector(int x, int y) {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static double crossProduct(Vector a, Vector b) {
        return a.x * b.y - b.x * a.y;
    }
}
