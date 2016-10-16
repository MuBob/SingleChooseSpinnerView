package mu.bob.com.singlechoosespinner.demo.ui;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import mu.bob.com.singlechoosespinner.R;
import mu.bob.com.singlechoosespinner.demo.bean.DemoItemBean;
import mu.bob.com.singlechoosespinner.widget.SingleChooseSpinner;

public class MainActivity extends Activity {

    private SingleChooseSpinner singleChooseSpinner;
    private List<DemoItemBean> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singleChooseSpinner = (SingleChooseSpinner) findViewById(R.id.singleChooseSpinner);
        data=new ArrayList<>();
        for (int i = 0; i < 140; i++) {
            DemoItemBean bean=new DemoItemBean();
            bean.setName("姓名"+i);
            data.add(bean);
        }
        singleChooseSpinner.setData(data);
        singleChooseSpinner.setSelectItem(2);
    }
}
