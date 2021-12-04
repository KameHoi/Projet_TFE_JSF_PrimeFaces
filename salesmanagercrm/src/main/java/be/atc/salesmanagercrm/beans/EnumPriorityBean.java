package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.enums.EnumPriority;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class EnumPriorityBean {

    public EnumPriority[] getEnumPriority() {
        return EnumPriority.values();
    }
}
