package com.mycompany.vgtu.pages.navbar;

public enum MenuItemEnum {

    LOGIN("Prisijungimas"),
    REGISTER("Registracija"),
    LECTURES("Paskaitos"),
    CREATE_LECTURE("Pridėti"),
    CREATE_CATEGORY("Pridėti kategoriją"),
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
