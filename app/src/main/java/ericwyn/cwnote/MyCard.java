package ericwyn.cwnote;

/**
 * 作为water数据适配器的数据类
 * Created by Ericwyn on 2017/1/20.
 */

public class MyCard {
    private String mName;
    private String mText;
    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
