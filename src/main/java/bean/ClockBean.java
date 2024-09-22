package bean;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean
@ViewScoped
public class ClockBean implements Serializable {

    private String currentTime;

    public ClockBean() {
        // Инициализируем текущее время при создании бина
        updateTime();
    }

    public String getCurrentTime() {
        return currentTime;
    }

    // Метод для обновления текущего времени
    public void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
        currentTime = sdf.format(new Date());
    }
}

