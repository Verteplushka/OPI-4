package bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class DotsPercentsBean implements Serializable {
    @Inject
    private DotsAmountBean dotsAmountBean;
    private float percent;

    @PostConstruct
    public void init(){
        updatePercent();
    }

    public void updatePercent(){
        this.percent = (float) dotsAmountBean.getHitDotsAmount() / dotsAmountBean.getAllDotsAmount() * 100;
    }

    public String getDotsPercents() {
        return "Hit percent: " + this.percent + "%";
    }
}

