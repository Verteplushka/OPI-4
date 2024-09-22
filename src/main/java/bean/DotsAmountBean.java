package bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.inject.Inject;
import server.Dot;

import java.io.Serializable;

@ManagedBean
@SessionScoped
public class DotsAmountBean implements Serializable {
    @Inject
    private ParamBean paramBean;
    private int allDotsAmount;
    private int hitDotsAmount;

    @PostConstruct
    public void init(){
        updateDots();
    }

    public void updateDots(){
        this.allDotsAmount = this.paramBean.getDotsList().size();
        this.hitDotsAmount = 0;
        for(Dot dot : this.paramBean.getDotsList()) {
            if (dot.getResult().equals("hit")) {
                this.hitDotsAmount++;
            }
        }
    }

    public String getDotsAmount() {
        return "All dots: " + this.allDotsAmount + "  Hit dots: " + this.hitDotsAmount;
    }

    public int getAllDotsAmount() {
        return allDotsAmount;
    }

    public int getHitDotsAmount() {
        return hitDotsAmount;
    }
}

