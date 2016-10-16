package mu.bob.com.singlechoosespinner.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mu.bob.com.singlechoosespinner.R;
import mu.bob.com.singlechoosespinner.adapter.SingleChooseSpinnerAdapter;
import mu.bob.com.singlechoosespinner.bean.SingleChooseSpinnerBean;
import mu.bob.com.singlechoosespinner.util.SingleChooseSpinnerConfig;

/**
 * 单选下拉列表控件
 * Created by Administrator on 2016/8/31.
 */
public class SingleChooseSpinner extends LinearLayout{
    private static final String TAG = "SingleChooseSpinner";
    private Context context;
    private TextView titleText, contentText;
    private ImageView spinnerImage;
    private ListView listView;
    private RelativeLayout line1Layout, contentLayout, line2Layout;
    private TextView lineView;
    private boolean isShowSpinner;
    private List<? extends ISpinnerGet> data;
    private List<String> list;
    private Map<Integer, Boolean> selectItems;
    private SingleChooseSpinnerAdapter adapter;
    private int selectPosition;
    private SingleChooseSpinnerConfig config;

    public SingleChooseSpinner(Context context) {
        this(context, null);
    }

    public SingleChooseSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SingleChooseSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_single_choose_spinner, this);
        config = new SingleChooseSpinnerConfig(context, attrs);
        initView(view);
        setAttrs(config);
//        initData();
        initListener();
    }

    private void setAttrs(SingleChooseSpinnerConfig config) {
        if (config == null) {
            return;
        }
        titleText.setVisibility(this.config.isTitleShow() ? VISIBLE : GONE);
        setTitleText(this.config.getTitleText());
        titleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.config.getTitleTextSize());
        titleText.setTextColor(this.config.getTitleTextColor());
        setContentText(this.config.getContentText());
        contentText.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.config.getContentTextSize());
        contentText.setTextColor(this.config.getContentTextColor());
        lineView.setVisibility(this.config.isContentLineShow() ? VISIBLE : GONE);
    }

    int listSize;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LayoutParams line1LayoutParams = (LayoutParams) line1Layout.getLayoutParams();
        line1LayoutParams.height = config.getLine1Height();
        line1Layout.setLayoutParams(line1LayoutParams);

//        LinearLayout.LayoutParams line2LayoutParams = (LinearLayout.LayoutParams) line2Layout.getLayoutParams();
//        line2LayoutParams.height = config.getListPerHeight() * Math.min(config.getListRaw(), adapter==null?config.getListRaw():adapter.getCount());
//        line2Layout.setLayoutParams(line2LayoutParams);

        setLine2Height();
        RelativeLayout.LayoutParams contentLayoutParams = (RelativeLayout.LayoutParams) contentLayout.getLayoutParams();
        contentLayoutParams.setMargins(config.getTitleWidth(), 0, getResources().getDimensionPixelOffset(R.dimen.item_margin_horizontal), 0);
        contentLayout.setLayoutParams(contentLayoutParams);

        RelativeLayout.LayoutParams lineViewParams = (RelativeLayout.LayoutParams) lineView.getLayoutParams();
        lineViewParams.setMargins(0, config.getContentLineMargin(), 0, 0);
        lineView.setLayoutParams(lineViewParams);
    }

    /**
     * 设置显示的最大行数
     * @param listRaw
     */
    public void setListListRaw(int listRaw){
        if(config!=null){
            config.setListRaw(listRaw);
            invalidate();
        }
    }


    /**
     * 设置数据
     *
     * @param data 实现ISpinnerGet接口的集合
     */
    public void setData(List<? extends ISpinnerGet> data) {
        setData(data, R.layout.item_1_single_choose, R.id.text_1);
    }

    /**
     * 设置纯数据
     *
     * @param data
     */
    public void setData(String[] data) {
        ArrayList<SingleChooseSpinnerBean> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            SingleChooseSpinnerBean bean = new SingleChooseSpinnerBean(data[i]);
            list.add(bean);
        }
        setData(list);
    }

    public void setData(List<? extends ISpinnerGet> data, int resource, int textViewResourceId) {
        initData(resource, textViewResourceId);
        if (data == null) {
            return;
        }
        this.data = data;
        if (data == null || data.size() <= 0) {
            return;
        }
        list.clear();
        selectItems.clear();
        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i).getSelectName());
            selectItems.put(i, false);
        }
        setSelectItem(selectPosition);
//        listSize=list.size();
//        com.zonetry.base.util.Log.d(TAG, "setData: listSize111:"+listSize);


        notifyDataSetChanged();
//        com.zonetry.base.util.Log.d(TAG, "setData: listSize222:"+listSize);
//        listSize=list.size();

        setLine2Height();

        invalidate();
//        com.zonetry.base.util.Log.d(TAG, "setData: listSize3333:"+listSize);
    }

    private void setLine2Height() {
        LayoutParams line2LayoutParams = (LayoutParams) line2Layout.getLayoutParams();
        line2LayoutParams.height = config.getListPerHeight() * Math.min(config.getListRaw(), adapter==null?config.getListRaw():adapter.getCount());
        line2Layout.setLayoutParams(line2LayoutParams);
    }

    /**
     * 获取右侧显示的文本内容
     *
     * @return
     */
    public CharSequence getContentText() {
        return contentText.getText();
    }

    /**
     * 设置右侧显示的文本内容
     *
     * @param text
     */
    public void setContentText(CharSequence text) {
        contentText.setText(text);
//        invalidate();
    }

    /**
     * 获取左侧显示的文本内容
     *
     * @return
     */
    public CharSequence getTitleText() {
        return titleText.getText();
    }

    /**
     * 设置左侧显示的文本内容
     *
     * @param text
     */
    public void setTitleText(CharSequence text) {
        titleText.setText(text);
//        invalidate();
    }

    /**
     * 获取选中的item项
     *
     * @return ISpinnerGet接口，可以强转为传入的类型
     */
    public ISpinnerGet getSelectItem() {
        return data.get(selectPosition >= 0 ? selectPosition : 0);
    }

    /**
     * 获取选中的item项
     *
     * @return
     */
    public int getSelectPosition() {
        return selectPosition;
    }

    /**
     * 实现该接口，在getName()中返回要显示的选项
     */
    public interface ISpinnerGet {
        String getSelectName();
    }

    private void initData(int resource, int textViewResourceId) {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (selectItems == null) {
            selectItems = new HashMap<>();
        }
        if (adapter == null) {
            adapter = new SingleChooseSpinnerAdapter(context, resource, textViewResourceId, list, selectItems);
            listView.setAdapter(adapter);
            setSelectItem(selectPosition);
        }
    }

    public void setSelectItem(int position) {
        if (position < 0) {
            Log.i("Error", "SingleChooseSpinner.setSelectItem: 设置选项越界<0，position=" + position);
            position = 0;
        } else if (position >= selectItems.size()) {
            Log.i("Error", "SingleChooseSpinner.setSelectItem: 设置选项越界>maxLength，position=" + position);
            position = selectItems.size();
        }
        selectPosition = position;
        Set<Integer> integers = selectItems.keySet();
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            selectItems.put(next, false);
        }
        selectItems.put(position, true);
        if (data != null && position < data.size()) {
            setContentText(data.get(position).getSelectName());
        }
        notifyDataSetChanged();
    }


    private void initListener() {
        contentLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isShowSpinner = !isShowSpinner;
                setSpinnerShow(isShowSpinner);
                if (contentClickListener != null) {
                    contentClickListener.onClick(view, list);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectPosition = i;
                String item = adapter.getItem(i);
                setContentText(item);
                isShowSpinner = false;
                setSpinnerShow(isShowSpinner);
                if (selectListener != null) {
                    selectListener.selectListener(selectPosition);
                }
            }
        });
    }

    private void setSpinnerShow(boolean isShow) {
        line2Layout.setVisibility(isShow ? VISIBLE : GONE);
        if (config.isContentLineShow()) {
            lineView.setVisibility(isShow ? GONE : VISIBLE);
        } else {
            lineView.setVisibility(GONE);
        }
        spinnerImage.setImageResource(getRightImageResId(isShow));
        setSelectItem(selectPosition);
        invalidate();
    }

    /**
     * 设置右侧展开和闭合时的图片资源
     *
     * @param isShow
     * @return
     */
    private int getRightImageResId(boolean isShow) {
        return isShow ? R.drawable.icon_down : R.drawable.icon_right;
    }

    private void initView(View view) {
        titleText = (TextView) view.findViewById(R.id.title_text);
        contentText = (TextView) view.findViewById(R.id.content_text);
        spinnerImage = (ImageView) view.findViewById(R.id.spinner_image);
        line1Layout = (RelativeLayout) view.findViewById(R.id.line1_layout);
        contentLayout = (RelativeLayout) view.findViewById(R.id.line_layout);
        listView = (ListView) view.findViewById(R.id.list_view);
        line2Layout = (RelativeLayout) view.findViewById(R.id.line2_layout);
        lineView = (TextView) findViewById(R.id.line_content_text);
    }

    public void notifyDataSetChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    private OnContentClickListener contentClickListener;

    public void setOnContentClickListener(OnContentClickListener contentClickListener) {
        this.contentClickListener = contentClickListener;
    }

    private OnSelectListener selectListener;

    public void setOnSelectListener(OnSelectListener listener) {
        selectListener = listener;
    }

    public interface OnSelectListener {
        void selectListener(int selectPosition);
    }

    public interface OnContentClickListener {
        void onClick(View view, List<String> list);
    }

}
