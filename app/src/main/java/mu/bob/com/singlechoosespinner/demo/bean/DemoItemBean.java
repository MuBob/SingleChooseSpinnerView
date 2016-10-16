package mu.bob.com.singlechoosespinner.demo.bean;

import mu.bob.com.singlechoosespinner.widget.SingleChooseSpinner;

/**
 * Created by Administrator on 2016/9/1.
 */
public class DemoItemBean implements SingleChooseSpinner.ISpinnerGet {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSelectName() {
        return getName();
    }
}
