package ericwyn.cwnote;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class EditActivity extends AppCompatActivity {
    private EditText head;
    private EditText text;
    private MyCard card;
    private Button mButton;
    private String mLabel="white";      //便签的标签的默认值
    private final Date createDate=new Date();       //便签新建的时间
    private DataHelper mDataHelper;
    private boolean xinjian=false;         //是否已经存在，默认是不存在的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //固定下来的时间

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edid_layout);
        head=(EditText)findViewById(R.id.edit_head);
        text=(EditText)findViewById(R.id.edit_text);
//        mButton=(Button)findViewById(R.id.saveButton);

        if(getIntent()!=null){
            xinjian=getIntent().getBooleanExtra("xinjianbianqian",true);
        }
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



    //保存
    private void save(Date createDate){
        if(!head.getText().toString().equals("") && !text.getText().toString().equals("")){
//
//            Log.i("save","save方法被调用");
            SQLiteDatabase db=mDataHelper.getWritableDatabase();
            Cursor cursor=db.query("card",null,null,null,null,null,null);

            //对便签的新建
            if(xinjian){
                Log.i("save","新建的便签得到了保存");
                ContentValues values = new ContentValues();
                values.put("id", createDate.toString());
                values.put("create_time", createDate.toString());
                values.put("lastmod_time", createDate.toString());
                values.put("label", mLabel);
                values.put("head", head.getText().toString());
                values.put("txt", text.getText().toString());
                values.put("alarm", createDate.toString());
                db.insert("card", null, values);
                cursor.close();
//
//                SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
//                SharedPreferences.Editor editor=sp.edit();
//                editor.putString("id",createDate.toString());
//                editor.putString("create_time",createDate.toString());
//                editor.putString("lastmod_time",createDate.toString());
//                editor.putString("label",mLabel);
//                editor.putString("head",head.getText().toString());
//                editor.putString("txt",text.getText().toString());
//                editor.putString("alarm",createDate.toString());
//                editor.putBoolean("weiShuaXin",true);
//
//                editor.apply();
                Bundle bundleToMain=new Bundle();
                bundleToMain.putString("id",createDate.toString());
                bundleToMain.putString("head",head.getText().toString());
                bundleToMain.putString("txt",text.getText().toString());
                bundleToMain.putString("label",mLabel);
                bundleToMain.putString("alarm",createDate.toString());

                bundleToMain.putString("create_time",createDate.toString());
                bundleToMain.putString("lastmod_time",createDate.toString());
                bundleToMain.putBoolean("xinjian",true);

                Intent intent = new Intent();  //这个Intent对象的作用只是存储数据
                intent.putExtras(bundleToMain); //Intent对象存储数据
                setResult(RESULT_OK, intent); //此方法专门向上一个活动返回数据
                finish(); //销毁此活动
            }
            //对便签的修改
            if(!xinjian) {

            }




        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        save(createDate);
//        backToMain();
//        startActivity(intent);
//        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        save(createDate);
    }

    //    public void backToMain() {
//        save(createDate);
//        bundleToMain.putString("id",createDate.toString());
//        bundleToMain.putString("head",head.getText().toString());
//        bundleToMain.putString("txt",text.getText().toString());
//        bundleToMain.putString("label",mLabel);
//        bundleToMain.putString("alarm",createDate.toString());
//        if(xinjian){
//            bundleToMain.putString("create_time",createDate.toString());
//            bundleToMain.putString("lastmod_time",createDate.toString());
//            bundleToMain.putBoolean("xinjian",true);
//        }else {
//
//        }
//        Intent intent=new Intent(EditActivity.this,MainActivity.class);
//        intent.putExtras(bundleToMain);
//        setResult(RESULT_OK,intent);
//
//
//
//        finish();
//    }
}
