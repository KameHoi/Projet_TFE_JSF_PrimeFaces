package be.atc.salesmanagercrm.enums;

public enum EnumPriority {
    ELEVEE("Elevée"),
    MOYENNE("Moyenne"),
    FAIBLE("Faible"),
    ;

    private final String label;

    EnumPriority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
