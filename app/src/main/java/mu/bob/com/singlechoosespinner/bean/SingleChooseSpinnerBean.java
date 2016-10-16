package mu.bob.com.singlechoosespinner.bean;

import mu.bob.com.singlechoosespinner.widget.SingleChooseSpinner;

/**
 * Created by Administrator on 2016/9/8.
 */
public class SingleChooseSpinnerBean implements SingleChooseSpinner.ISpinnerGet {
    private String name;

    public SingleChooseSpinnerBean(String name) {
        this.name = name;
    }

    @Override
    public String getSelectName() {
        return name;
    }
}
