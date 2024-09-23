package mbeans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jdk.jfr.Name;

import javax.management.MBeanServer;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;
import java.io.Serializable;
import java.lang.management.ManagementFactory;

@Named
@ApplicationScoped
public class MissPercentage extends NotificationBroadcasterSupport implements Serializable, MissPercentageMBean {
    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
    private double hitPercent;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object unused) {
        try {
            ObjectName missPercentageName = new ObjectName("main_page:type=MissPercentage");
            mbs.registerMBean(this, missPercentageName);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void destroy(@Observes @Initialized(ApplicationScoped.class) Object unused) {
        try {
            ObjectName missPercentageName = new ObjectName("main_page:type=MissPercentage");
            mbs.unregisterMBean(missPercentageName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double updatePercent(int totalPoints, int pointsInsideArea) {
        if (totalPoints == 0) {
            this.hitPercent = 0;
        } else {
            this.hitPercent = ((double) pointsInsideArea / totalPoints) * 100;
        }
        return this.hitPercent;
    }

    @Override
    public synchronized double getHitPercent() {
        return hitPercent;
    }
}
