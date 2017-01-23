package ericwyn.cwnote;

/**
 * 作为water数据适配器的数据类
 * Created by Ericwyn on 2017/1/20.
 */

public class MyCard {
    private String mhead;
    private String mText;
    private String label;
    private String time_create;
    private String time_lastmod;

    public MyCard(){
        mhead="";
        mText="";
        label="";
        time_create="";
        time_lastmod="";
    }

    public MyCard(String head,String text,String label){
        this.mhead=head;
        this.mText=text;
        this.label="white";
    }

    public String gethead() {
        return mhead;
    }

    public void sethead(String mhead) {
        this.mhead = mhead;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTime_create() {
        return time_create;
    }

    public void setTime_create(String time_create) {
        this.time_create = time_create;
    }

    public String getTime_lastmod() {
        return time_lastmod;
    }

    public void setTime_lastmod(String time_lastmod) {
        this.time_lastmod = time_lastmod;
    }
}
