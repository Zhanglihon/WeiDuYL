package zhang.bw.com.common.bean;

import android.graphics.Color;

public class ShowBeans {
    public int id;
    public String pic;
    public String departmentName;
    public int textcolor=Color.BLACK;
    public boolean checked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(int textcolor) {
        this.textcolor = textcolor;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public ShowBeans(int id, String pic, String departmentName, int textcolor, boolean checked) {
        this.id = id;
        this.pic = pic;
        this.departmentName = departmentName;
        this.textcolor = textcolor;
        this.checked = checked;
    }
}
