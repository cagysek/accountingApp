package cz.pia.cagy.accountingApp.model.enums;

public enum EFlashMessageType
{
    SUCCESS,
    ERROR;

    @Override
    public String toString()
    {
        switch (this)
        {
            case ERROR:
                return "error";
            case SUCCESS:
                return "success";
        }
        return "";
    }
}
