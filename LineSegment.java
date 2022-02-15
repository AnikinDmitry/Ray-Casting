import java.util.ArrayList;

public record LineSegment(Point start, Point stop) {
    private static final double EPSILON = 0.000001;

    public Point getStart() {
        return start;
    }

    public Point getStop() {
        return stop;
    }

    public static Point getIntersection(LineSegment ray, LineSegment segment) {
        Vector r = new Vector(
                ray.getStop().getX() - ray.getStart().getX(),
                ray.getStop().getY() - ray.getStart().getY());
        Vector s = new Vector(
                segment.getStop().getX() - segment.getStart().getX(),
                segment.getStop().getY() - segment.getStart().getY()
        );
        double rxs = Vector.crossProduct(r, s);

        Vector qp = new Vector(
                segment.getStart().getX() - ray.getStart().getX(),
                segment.getStart().getY() - ray.getStart().getY()
        );

        if (rxs < EPSILON) return null;

        double t = Vector.crossProduct(qp, s) / rxs;
        double u = Vector.crossProduct(qp, r) / rxs;
        if (rxs >= EPSILON && (0 <= t && t <= 1) && (0 <= u && u <= 1)) {
            return new Point(
                    (int) Math.floor(ray.getStart().getX() + t * r.getX()),
                    (int) Math.floor(ray.getStart().getY() + t * r.getY())
            );
        }

        return null;
    }

    public static Point getClosestIntersection(LineSegment ray, ArrayList<LineSegment> segments) {
        Point closestIntersect = null;
        double closestDistance = Double.MAX_VALUE;

        for (LineSegment l : segments) {
            Point intersect = getIntersection(ray, l);
            if (intersect == null) continue;
            if (
                    closestIntersect == null
                            || Point.distance(ray.getStart(), intersect) < closestDistance
            ) {
                closestIntersect = intersect;
                closestDistance = Point.distance(ray.getStart(), intersect);
            }
        }
        for (LineSegment l : segments) {
            Point intersect = getIntersection(l, ray);
            if (intersect == null) continue;
            if (
                    closestIntersect == null
                            || Point.distance(ray.getStart(), intersect) < closestDistance
            ) {
                closestIntersect = intersect;
                closestDistance = Point.distance(ray.getStart(), intersect);
            }
        }

        return closestIntersect;
    }
}
