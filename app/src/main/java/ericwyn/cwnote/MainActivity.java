package ericwyn.cwnote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private WaterFullAdapter mAdapter;
    private FloatingActionButton fab;
    List<MyCard> list=new ArrayList<>();

    private DataHelper myData;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private FloatingActionButton fabTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        init();
    }

    private void init() {
        mRecyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        fab=(FloatingActionButton)findViewById(R.id.main_fab);

        fabTest=(FloatingActionButton)findViewById(R.id.testButton_mainActivity);
        fabTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data=getIntent();
                String id=           data.getStringExtra("id");
                String head=         data.getStringExtra("head");
                String text=         data.getStringExtra("txt");
                String label=        data.getStringExtra("label");
                String create_time=  data.getStringExtra("create_time");
                String lastmod_time= data.getStringExtra("lastmod_time");
                String alarm=        data.getStringExtra("alarm");
                MyCard p=new MyCard(id,head,text,label,create_time,lastmod_time,alarm);
                list.add(0,p);
                mAdapter.notifyItemInserted(0);
                mAdapter.notifyItemRangeChanged(0,list.size());
            }
        });

        mLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);

        list=buildData();
        mAdapter=new WaterFullAdapter(this,list);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //首次启动时候,设置
        SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
        if(!sp.getBoolean("firstOpen",false)){
            SharedPreferences.Editor editor=sp.edit();
            editor.putInt("cardNum",0);
//            editor.putString("last_head","欢迎使用");
//            editor.putString("last_txt","点击右下角，即刻新建便签");
//            editor.putString("last_label","white");
            editor.apply();
        }


        //新建笔记按钮
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,EditActivity.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean("xianjianbianqian",true);
                intent.putExtras(bundle);
                int requestCode = 1;  //请求码
                startActivityForResult(intent, requestCode);  //开启活动，并传递请求码
            }
        });

    }
    private List<MyCard> buildData(){
        List<MyCard> list222=new ArrayList<>();
        myData=new DataHelper(this,"NoteData.db",null,1);
        SQLiteDatabase db=myData.getWritableDatabase();
        //查询CwNoteData当中的所有数据
        Cursor cursor =db.query("card",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String id=cursor.getString(cursor.getColumnIndex("id"));
                String head=cursor.getString(cursor.getColumnIndex("head"));
                String text=cursor.getString(cursor.getColumnIndex("txt"));
                String label=cursor.getString(cursor.getColumnIndex("label"));
                String create_time=cursor.getString(cursor.getColumnIndex("create_time"));
                String lastmod_time=cursor.getString(cursor.getColumnIndex("lastmod_time"));
                String alarm=cursor.getString(cursor.getColumnIndex("alarm"));
                MyCard p=new MyCard(id,head,text,label,create_time,lastmod_time,alarm);
                list222.add(p);
            }while (cursor.moveToNext());
        }
        cursor.close();
        //倒序排列list，使得最末尾的那个便签能够在最上面显示
        Collections.reverse(list222);
        return list222;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        shuaxin();
    }

    /**
     * 解析来自EditActivity的Bundle数据
     */
    private void shuaxin(){
//        SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
//        int cardNum=sp.getInt("cardNum",0);
//        if(cardNum!=0 && cardNum>list.size()){
//
//            String head=sp.getString("last_head","apple");
//            String text=sp.getString("last_txt","");
//            String label=sp.getString("last_label","white");
//            MyCard p;
//            p=new MyCard(head,text,label);
//            list.add(0,p);
//            mAdapter.notifyItemInserted(0);
//            mAdapter.notifyItemRangeChanged(0,list.size());
//        }
//        if(intent.getBooleanExtra("xinjian",false)){
            SharedPreferences sp222=getSharedPreferences("user",MODE_PRIVATE);
//            if(sp222.getBoolean("weiShuaXin",false)){
                SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                Log.i("MainActivity","shuaxin()被调用了");
                String id=           sp.getString("id","");             //intent.getStringExtra("id");
                String head=         sp.getString("head","");           //intent.getStringExtra("head");
                String text=         sp.getString("txt","");            //intent.getStringExtra("txt");
                String label=        sp.getString("label","");          //intent.getStringExtra("label");
                String create_time=  sp.getString("create_time","");    //intent.getStringExtra("create_time");
                String lastmod_time= sp.getString("lastmod_time","");   //intent.getStringExtra("lastmod_time");
                String alarm=        sp.getString("alarm","");          //intent.getStringExtra("alarm")
                editor.putBoolean("weiShuaXin",false);
                editor.apply();
                MyCard p=new MyCard(id,head,text,label,create_time,lastmod_time,alarm);
                list.add(0,p);
                mAdapter.notifyItemInserted(0);
                mAdapter.notifyItemRangeChanged(0,list.size());
//            }

//        }else {
//
//        }

    }

    /**
     * requestCode:请求码，启动活动时传入的请求码
     * resultCode：处理结果，返回数据时传入的处理结果
     * data:携带返回数据的Intent对象
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Log.i("MainActivity","数据刷新成功");
                    String id=           data.getStringExtra("id");
                    String head=         data.getStringExtra("head");
                    String text=         data.getStringExtra("txt");
                    String label=        data.getStringExtra("label");
                    String create_time=  data.getStringExtra("create_time");
                    String lastmod_time= data.getStringExtra("lastmod_time");
                    String alarm=        data.getStringExtra("alarm");
                    MyCard p=new MyCard(id,head,text,label,create_time,lastmod_time,alarm);
                    list.add(0,p);
                    mAdapter.notifyItemInserted(0);
                    mAdapter.notifyItemRangeChanged(0,list.size());
                    Log.i("MainActivity","数据刷新成功");
                }
                break;
            default:
                break;
        }
    }

}
