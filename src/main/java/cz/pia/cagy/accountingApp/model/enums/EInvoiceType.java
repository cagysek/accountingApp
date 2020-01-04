package cz.pia.cagy.accountingApp.model.enums;

public enum EInvoiceType
{
    ACCEPTED("Přijatá"),
    PUBLISHED("Vydaná");


    private final String displayValue;

    private EInvoiceType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
