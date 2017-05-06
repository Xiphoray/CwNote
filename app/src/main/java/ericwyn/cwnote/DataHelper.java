package ericwyn.cwnote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * 数据库操作的帮助类
 * Created by Ericwyn on 2017/1/23.
 */

public class DataHelper extends SQLiteOpenHelper{
    //创建card表，存储用户所有的卡片信息
    private static final String CREATE_CARD= "create table card(" +
            "cardNo integer primary key autoincrement," +        //卡片的编号
            "id text," +                                 //卡片的id
            "create_time text," +            //创立时间
            "lastmod_time text," +           //最后修改时间
            "label text," +                  //标签：红色？绿色？用以标识不同的颜色
            "head text," +
            "txt text," +
            "alarm text)";                     //内容：便签内容
//    private static final String CREATE_USER= "create table user(" +
//            "card_num integer)";                     //便签的数量
////    //创建用户表，存储用户相关的信息
////    private static final String CREATE_UESR="create table user(" +
////            "user_id integer";

    private Context mContext;

    public DataHelper(Context context, String name, SQLiteDatabase.CursorFactory factory , int version){
        super(context,name,factory,version);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CARD);
//        sqLiteDatabase.execSQL(CREATE_USER);
        Toast.makeText(mContext,"数据库创建成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
