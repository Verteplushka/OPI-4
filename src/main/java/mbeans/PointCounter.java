package mbeans;

import database.DotEntityManager;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import server.Dot;

import javax.management.*;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ApplicationScoped
public class PointCounter extends NotificationBroadcasterSupport implements Serializable, PointCounterMBean {
//    private static final Logger logger = Logger.getLogger(PointCounter.class.getName());
    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

    static private MissPercentage missPercentage;
    private int totalPoints = 0;
    private int pointsInsideArea = 0;
    private int sequenceNumber = 0;

    static {
        try {
            missPercentage = new MissPercentage();
            ObjectName missPercentageName = new ObjectName("main_page:type=missPercentage");
            missPercentage.init(missPercentageName);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
    }

    public void init(@Observes @Initialized(ApplicationScoped.class) Object unused) {
        try {
            ObjectName pointCounterName = new ObjectName("main_page:type=PointCounter");
            mbs.registerMBean(this, pointCounterName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy(@Observes @Initialized(ApplicationScoped.class) Object unused){
        try {
            ObjectName pointCounterName = new ObjectName("main_page:type=PointCounter");
            mbs.unregisterMBean(pointCounterName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void setPoints(ArrayList<Dot> dots){
        totalPoints = dots.size();
        pointsInsideArea = 0;
        for(Dot dot : dots){
            if(dot.getResult().equals("hit")){
                pointsInsideArea += 1;
            }
        }
        missPercentage.updatePercent(totalPoints, pointsInsideArea);
    }

    @Override
    public synchronized void addPoint(double x, double y, boolean isInsideArea) {
        totalPoints++;
        if (isInsideArea) {
            pointsInsideArea++;
        }
        missPercentage.updatePercent(totalPoints, pointsInsideArea);
    }

    @Override
    public synchronized void resetPoints() {
        totalPoints = 0;
        pointsInsideArea = 0;
        missPercentage.updatePercent(totalPoints, pointsInsideArea);
    }

    @Override
    public synchronized void notification(double x, double y, double r){
        Notification notification = new Notification(
                "out of area notification",
                this,
                sequenceNumber++,
                "new dot(x = " + x + ", y = " + y + ", r = " + r + ") is out of area" +
                        " (-5 < x < 3; -5 < y < 5; 1 <= r <= 5)"
        );
        sendNotification(notification);
    }

    @Override
    public synchronized int getTotalPoints() {
        return totalPoints;
    }

    @Override
    public synchronized int getPointsInsideArea() {
        return pointsInsideArea;
    }

}
