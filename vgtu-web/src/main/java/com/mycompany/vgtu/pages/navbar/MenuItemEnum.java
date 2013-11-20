package com.mycompany.vgtu.pages.navbar;

public enum MenuItemEnum {

    LOGIN("Prisijungimas"),
    REGISTER("Registracija"),
    LECTURES("Paskaitos"),
    NONE("");
    private String label;

    private MenuItemEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
