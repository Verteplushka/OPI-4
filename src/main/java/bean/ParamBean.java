package bean;

import database.DotEntityManager;
import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.inject.Inject;
import mbeans.MissPercentage;
import mbeans.PointCounter;
import server.Dot;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.System.nanoTime;

@ManagedBean
@SessionScoped
public class ParamBean {
    static private PointCounter pointCounter;
    @Inject
    private DotEntityManager dotEntityManagerBean;
    private double x = 0;
    private double y = 0;
    private int r = 3;

    static {
        try {
            pointCounter = new PointCounter();
            ObjectName pointCounterName = new ObjectName("main_page:type=PointCounter");
            pointCounter.init(pointCounterName);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        try {
            pointCounter.setPoints(getDotsList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addDotEntityManager(Dot dot){
        dotEntityManagerBean.addDot(dot);
    }

    public void addDotMouse(double x, double y, int r){
        setR(r);
        addDot(x, y, r);
    }
    public void addDotButton(){
        addDot(getX(), getY(), getR());
    }

    private void addDot(double x, double y, int r){
        if(!isValid(x, y, r)){
            return;
        }
        String result;
        long startTime = nanoTime();
        if((x<=0 && y>=0 && y<=2*x+r) || (x>=0 && y>=0 && x*x+y*y<=r*r) || (x>=0 && y<=0 && x<=r && y>=-r/2.0)){
            result = "hit";
        } else{
            result = "miss";
        }
        pointCounter.addPoint(x, y, result.equals("hit"));
//        missPercentage.getMissPercentage();
        addDotEntityManager(new Dot(x, y, r, LocalDate.now(), nanoTime() - startTime, result));
    }

    private boolean isValid(double x, double y, int r){
        if(!(-5 < x && x < 3)){
            pointCounter.notification(x, y, r);
            return false;
        }
        if(!(y > -5 && y < 5)){
            pointCounter.notification(x, y, r);
            return false;
        }
        if(!(1 <= r && r <= 5)){
            pointCounter.notification(x, y, r);
            return false;
        }
        return true;
    }

    public void clear(){
        pointCounter.resetPoints();
//        missPercentage.getMissPercentage();
        dotEntityManagerBean.clear();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getR() {
        return this.r;
    }

    public boolean getR1() {
        return getR() == 1;
    }

    public boolean getR2() {
        return getR() == 2;
    }

    public boolean getR3() {
        return getR() == 3;
    }

    public boolean getR4() {
        return getR() == 4;
    }

    public boolean getR5() {
        return getR() == 5;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setR1(boolean r) {
        if (r) {
            this.r = 1;
        } else {
            this.r = 0;
        }
    }

    public void setR2(boolean r) {
        if (r) {
            this.r = 2;
        } else {
            this.r = 0;
        }
    }

    public void setR3(boolean r) {
        if (r) {
            this.r = 3;
        } else {
            this.r = 0;
        }
    }

    public void setR4(boolean r) {
        if (r) {
            this.r = 4;
        } else {
            this.r = 0;
        }
    }

    public void setR5(boolean r) {
        if (r) {
            this.r = 5;
        } else {
            this.r = 0;
        }
    }

    public ArrayList<Dot> getDotsList() {
//        pointCounter.setPoints(dotEntityManagerBean.getDotsList());
        return dotEntityManagerBean.getDotsList();
    }

}
