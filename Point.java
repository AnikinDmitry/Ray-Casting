public record Point(int x, int y) {
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }
}
