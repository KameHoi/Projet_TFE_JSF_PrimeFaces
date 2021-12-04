package be.atc.salesmanagercrm.entities;

import lombok.Getter;
import lombok.Setter;

public class ObjectActivity {
    @Getter
    @Setter
    private String objectType;
    @Getter
    @Setter
    private Object object;

    public ObjectActivity(String objectType, Object object) {
        this.objectType = objectType;
        this.object = object;
    }
}
