package ericwyn.cwnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private WaterFullAdapter mAdapter;
    private FloatingActionButton fab;

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
        mRecyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        fab=(FloatingActionButton)findViewById(R.id.main_fab);
        mLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);

        mAdapter=new WaterFullAdapter(this,buildData());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,EditActivity.class);
                startActivity(intent);
            }
        });


    }
    private List<MyCard> buildData(){
        String[] name={
                "浪淘沙",
                "天净沙",
                "破阵子",
                "烛影摇红",
                "蝶恋花",
                "浪淘沙",
                "天净沙",
                "破阵子",
                "烛影摇红",
                "蝶恋花",
                "浪淘沙",
                "天净沙",
                "破阵子",
                "烛影摇红",
                "蝶恋花",
        };
        String[] text={
                "浪淘沙浪淘沙浪淘沙浪淘沙浪淘沙浪淘沙浪淘沙",
                "天净沙天净沙天净沙天净沙天净沙天净沙天净沙",
                "破阵子破阵子破阵子破阵子破阵子",
                "烛影摇红烛影摇红烛影摇红烛影摇红烛影摇红烛影摇红烛影摇红",
                "蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花",
                "浪淘沙浪淘沙浪淘沙浪淘沙浪淘沙浪淘沙浪淘沙",
                "天净沙天净沙天净沙天净沙天净沙天净沙天净沙",
                "破阵子破阵子破阵子破阵子破阵子",
                "烛影摇红烛影摇红烛影摇红烛影摇红烛影摇红烛影摇红烛影摇红",
                "蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花",
                "浪淘沙浪淘沙浪淘沙浪淘沙浪淘沙浪淘沙浪淘沙",
                "天净沙天净沙天净沙天净沙天净沙天净沙天净沙",
                "破阵子破阵子破阵子破阵子破阵子",
                "烛影摇红烛影摇红烛影摇红烛影摇红烛影摇红烛影摇红烛影摇红",
                "蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花蝶恋花",
        };
        List<MyCard> list=new ArrayList<>();
        for (int i=0;i<15;i++){
            MyCard p=new MyCard();
            p.setName(name[i]);
            p.setText(text[i]);
            list.add(p);
        }

        return list;


    }
}
