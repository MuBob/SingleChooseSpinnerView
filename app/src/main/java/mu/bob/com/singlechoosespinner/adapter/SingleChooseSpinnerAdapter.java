package mu.bob.com.singlechoosespinner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import mu.bob.com.singlechoosespinner.R;

/**
 * Created by Administrator on 2016/9/8.
 */
public class SingleChooseSpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private int resId, textId;
    private ViewGroup viewGroup;
    private Map<Integer, Boolean> selectItems;
    public SingleChooseSpinnerAdapter(Context context, int resId, int textId, List<String> list, Map<Integer, Boolean> selectItems){
        this.context=context;
        this.list=list;
        this.resId=resId;
        this.textId=textId;
        this.selectItems=selectItems;
    }
    @Override
    public int getCount() {
//        Log.d("SingleChooseSpinnerAdapter", "getCount: "+list.size());
        return list==null?0:list.size();
    }

    @Override
    public String getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        this.viewGroup=viewGroup;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(resId, viewGroup, false);
        TextView textView = (TextView) view.findViewById(textId);
        textView.setText(list.get(i));
        boolean isChecked= selectItems.get(i);
//        Log.i("TAG", "SingleChooseSpinnerAdapter.getView: position="+i+", selected="+isChecked);
        textView.setTextColor(context.getResources().getColor(isChecked? R.color.text_color_select:R.color.text_color_unSelect));
        return view;
    }

}
