package mbeans;


public interface PointCounterMBean {
    void addPoint(double x, double y, boolean isInsideArea);
    void resetPoints();
    void notification(double x, double y, double r);
    int getTotalPoints();
    int getPointsInsideArea();
}
