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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        init();
    }

    private void init() {
//        FloatingActionButton testButton=(FloatingActionButton)findViewById(R.id.testButton_mainActivity);
//        testButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("MainActivity测试","刷新按钮被点击");
//                shuaxin();
//            }
//        });

        mRecyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        fab=(FloatingActionButton)findViewById(R.id.main_fab);
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
                startActivity(intent);
            }
        });

    }
    private List<MyCard> buildData(){
        List<MyCard> list=new ArrayList<>();
        myData=new DataHelper(this,"NoteData.db",null,1);
        SQLiteDatabase db=myData.getWritableDatabase();
        //查询CwNoteData当中的所有数据
        Cursor cursor =db.query("card",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String head=cursor.getString(cursor.getColumnIndex("head"));
                String text=cursor.getString(cursor.getColumnIndex("txt"));
                String label=cursor.getString(cursor.getColumnIndex("label"));
                MyCard p=new MyCard(head,text,label);
                list.add(p);
            }while (cursor.moveToNext());
        }
        cursor.close();
        //倒序排列list，使得最末尾的那个便签能够在最上面显示
        Collections.reverse(list);
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivivy测试","返回方法被调用");
        shuaxin();
        shuaxin();
    }

    private void shuaxin(){
        SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
        int cardNum=sp.getInt("cardNum",0);
        if(cardNum!=0 && cardNum>list.size()){

            String head=sp.getString("last_head","apple");
            String text=sp.getString("last_txt","");
            String label=sp.getString("last_label","white");
            MyCard p;
            p=new MyCard(head,text,label);
            list.add(0,p);
            mAdapter.notifyItemInserted(0);
            mAdapter.notifyItemRangeChanged(0,list.size());
        }

    }

}
