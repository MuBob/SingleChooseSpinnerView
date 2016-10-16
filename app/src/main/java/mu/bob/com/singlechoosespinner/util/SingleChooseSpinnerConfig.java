package mu.bob.com.singlechoosespinner.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import mu.bob.com.singlechoosespinner.R;

/**
 * Created by Administrator on 2016/8/31.
 */
public class SingleChooseSpinnerConfig {
    private boolean titleShow;
    private int line1Height;  //显示的单行高度
    private int listRaw;  //显示的最大行数
    private int listPerHeight;
    private int titleWidth;
    private int titleLeftMargin;
    private int titleRightMargin;
    private float titleTextSize;
    private float contentTextSize;
    private int titleTextColor;
    private int contentTextColor;
    private String titleText;
    private String contentText;
    private int contentLineMargin;
    private boolean contentLineShow;

    public SingleChooseSpinnerConfig(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SingleChooseSpinner);
        try {
            titleShow=a.getBoolean(R.styleable.SingleChooseSpinner_titleShow_SingleChooseSpinner, true);
            line1Height=a.getLayoutDimension(R.styleable.SingleChooseSpinner_line1Height_SingleChooseSpinner, context.getResources().getDimensionPixelOffset(R.dimen.line1_height));
            listRaw=a.getInteger(R.styleable.SingleChooseSpinner_listRaw_SingleChooseSpinner, context.getResources().getInteger(R.integer.list_raw));
            listPerHeight=a.getLayoutDimension(R.styleable.SingleChooseSpinner_listPerHeight_SingleChooseSpinner, context.getResources().getDimensionPixelOffset(R.dimen.list_per_height));
            titleWidth=a.getLayoutDimension(R.styleable.SingleChooseSpinner_titleWidth_SingleChooseSpinner, context.getResources().getDimensionPixelOffset(R.dimen.content_margin_left));
            titleLeftMargin=a.getDimensionPixelOffset(R.styleable.SingleChooseSpinner_titleLeftMargin_SingleChooseSpinner, context.getResources().getDimensionPixelOffset(R.dimen.item_margin_horizontal));
            titleRightMargin=a.getDimensionPixelOffset(R.styleable.SingleChooseSpinner_titleRightMargin_SingleChooseSpinner, context.getResources().getDimensionPixelOffset(R.dimen.item_margin_horizontal));
            titleTextSize=a.getDimensionPixelSize(R.styleable.SingleChooseSpinner_titleTextSize_SingleChooseSpinner, context.getResources().getDimensionPixelSize(R.dimen.title_text_size));
            contentTextSize=a.getDimensionPixelSize(R.styleable.SingleChooseSpinner_contentTextSize_SingleChooseSpinner, context.getResources().getDimensionPixelSize(R.dimen.content_text_size));
            titleTextColor=a.getColor(R.styleable.SingleChooseSpinner_titleTextColor_SingleChooseSpinner, context.getResources().getColor(R.color.title_text_color));
            contentTextColor=a.getColor(R.styleable.SingleChooseSpinner_contentTextColor_SingleChooseSpinner, context.getResources().getColor(R.color.content_text_color));
            titleText=a.getString(R.styleable.SingleChooseSpinner_titleText_SingleChooseSpinner);
            contentText=a.getString(R.styleable.SingleChooseSpinner_contentText_SingleChooseSpinner);
            contentLineMargin=a.getDimensionPixelOffset(R.styleable.SingleChooseSpinner_contentLineMargin_SingleChooseSpinner, context.getResources().getDimensionPixelOffset(R.dimen.content_line_margin));
            contentLineShow=a.getBoolean(R.styleable.SingleChooseSpinner_contentLineShow_SingleChooseSpinner, true);
        } finally {
            a.recycle();
        }
    }

    public int getListRaw() {
        return listRaw;
    }

    public void setListRaw(int listRaw) {
        this.listRaw = listRaw;
    }

    public int getListPerHeight() {
        return listPerHeight;
    }

    public void setListPerHeight(int listPerHeight) {
        this.listPerHeight = listPerHeight;
    }

    public int getContentLineMargin() {
        return contentLineMargin;
    }

    public void setContentLineMargin(int contentLineMargin) {
        this.contentLineMargin = contentLineMargin;
    }

    public boolean isContentLineShow() {
        return contentLineShow;
    }

    public void setContentLineShow(boolean contentLineShow) {
        this.contentLineShow = contentLineShow;
    }

    public int getLine1Height() {
        return line1Height;
    }

    public void setLine1Height(int line1Height) {
        this.line1Height = line1Height;
    }

    public boolean isTitleShow() {
        return titleShow;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public void setTitleShow(boolean titleShow) {
        this.titleShow = titleShow;
    }

    public int getTitleWidth() {
        return titleWidth;
    }

    public void setTitleWidth(int titleWidth) {
        this.titleWidth = titleWidth;
    }

    public int getTitleLeftMargin() {
        return titleLeftMargin;
    }

    public void setTitleLeftMargin(int titleLeftMargin) {
        this.titleLeftMargin = titleLeftMargin;
    }

    public int getTitleRightMargin() {
        return titleRightMargin;
    }

    public void setTitleRightMargin(int titleRightMargin) {
        this.titleRightMargin = titleRightMargin;
    }

    public float getTitleTextSize() {
        return titleTextSize;
    }

    public void setTitleTextSize(float titleTextSize) {
        this.titleTextSize = titleTextSize;
    }

    public float getContentTextSize() {
        return contentTextSize;
    }

    public void setContentTextSize(float contentTextSize) {
        this.contentTextSize = contentTextSize;
    }
    public int getTitleTextColor() {
        return titleTextColor;
    }

    public void setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    public int getContentTextColor() {
        return contentTextColor;
    }

    public void setContentTextColor(int contentTextColor) {
        this.contentTextColor = contentTextColor;
    }
}
