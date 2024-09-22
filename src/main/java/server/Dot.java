package server;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "dots", schema = "s367300")
public class Dot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double x;
    private double y;
    private int r;
    @Column(name = "currenttime")
    private LocalDate currentTime;
    @Column(name = "scripttime")
    private long scriptTime;
    private String result;

    public Dot(){}

    public Dot(double x, double y, int r, LocalDate currentTime, long scriptTime, String result){
        this.x = x;
        this.y = y;
        this.r = r;
        this.currentTime = currentTime;
        this.scriptTime = scriptTime;
        this.result = result;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public LocalDate getCurrentTime() {
        return currentTime;
    }

    public long getScriptTime() {
        return scriptTime;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "[x: " + getX() + "; y: " + getY() + "; r: " + getR() + "; currentTime: " + getCurrentTime() + "; scriptTime: " + getScriptTime() + "; result: " + getResult() + "]";
    }
}
