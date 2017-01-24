package ericwyn.cwnote;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class EditActivity extends AppCompatActivity {
    private EditText head;
    private EditText text;
    private MyCard card;
    private Button mButton;
    private String mLabel="white";      //便签的标签的默认值
    private final Date createDate=new Date();
    private DataHelper mDataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //固定下来的时间

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edid_layout);
        head=(EditText)findViewById(R.id.edit_head);
        text=(EditText)findViewById(R.id.edit_text);
//        mButton=(Button)findViewById(R.id.saveButton);

        //数据库获取/或者新建
        mDataHelper=new DataHelper(this,"NoteData.db",null,1);

//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                save(createDate);
//            }
//        });

        setTitle("新建记事");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save(createDate);
    }

    //保存
    private void save(Date createDate){
        boolean flag=false;             //是否已经存在，默认是不存在的
        int flag_num=0;                 //当前编辑便签的id，保存到一个SP里面，方便Main视图的更新
        if(!head.getText().toString().equals("") && !text.getText().toString().equals("")){

            SQLiteDatabase db=mDataHelper.getWritableDatabase();
            Cursor cursor=db.query("card",null,null,null,null,null,null);
            if(cursor.moveToFirst()){
                do{
                    String dataFlag=cursor.getString(cursor.getColumnIndex("create_time"));
                    flag_num++;
                    if(dataFlag.equals(createDate.toString())){
                        flag=true;
                        break;
                    }
                }while (cursor.moveToNext());
            }
            if(!flag){
                ContentValues values=new ContentValues();
                values.put("create_time",createDate.toString());
                values.put("lastmod_time",createDate.toString());
                values.put("label",mLabel);
                values.put("head",head.getText().toString());
                values.put("txt",text.getText().toString());
                values.put("alarm",createDate.toString());
                db.insert("card",null,values);

                SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putInt("lastCardNum",flag_num+1);        //flag_num总是比最后的那个卡片少1
                editor.putInt("cardNum",sp.getInt("cardNum",0)+1);
                editor.putString("last_head",head.getText().toString());
                editor.putString("last_txt",text.getText().toString());
                editor.putString("last_label",mLabel);
                editor.apply();
            }

        }

    }

}
