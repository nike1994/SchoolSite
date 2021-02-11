package pl.edu.wszib.school.website.model.View;

public class ButtonModel {
    String btnName;
    String subBtnName;
    String href;

    public ButtonModel(String btnName, String subBtnName, String href) {
        this.btnName = btnName;
        this.subBtnName = subBtnName;
        this.href = href;
    }

    public ButtonModel(String btnName, String href) {
        this.btnName = btnName;
        this.href = href;
        this.subBtnName=null;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public String getSubBtnName() {
        return subBtnName;
    }

    public void setSubBtnName(String subBtnName) {
        this.subBtnName = subBtnName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}