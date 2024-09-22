package bean;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class GraphBean {
    private double x = 0;
    private double y = 0;
    private int r = 3;

    public void setR(int r) {
        this.r = r;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getR() {
        return r;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}
