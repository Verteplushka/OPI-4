package mbeans;

public interface MissPercentageMBean {
    double updatePercent(int totalPoints, int pointsInsideArea);
    double getHitPercent();
}