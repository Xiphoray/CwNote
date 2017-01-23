package ericwyn.cwnote;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class EditActivity extends AppCompatActivity {
    private EditText head;
    private EditText text;
    private MyCard card;
    private Button mButton;
//    private boolean firstOpen=card.getTime_create().equals(card.getTime_lastmod());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Date createDate=new Date();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edid_layout);
        head=(EditText)findViewById(R.id.edit_head);
        text=(EditText)findViewById(R.id.edit_text);
        mButton=(Button)findViewById(R.id.saveButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(createDate);
            }
        });

        setTitle("新建记事");

    }

    //保存
    private void save(Date createDate){

        if(!head.getText().toString().equals("") && !text.getText().toString().equals("")){

            SharedPreferences userSp=getSharedPreferences("userData",MODE_PRIVATE);
            SharedPreferences.Editor userSp_editor=userSp.edit();

            String sp_name=head.getText().toString();        //不存在的情况下

            SharedPreferences cardSP=getSharedPreferences(sp_name,MODE_PRIVATE);
            SharedPreferences.Editor editor=cardSP.edit();
            //看一下是不是第一次编辑

            if (createDate.toString().equals(cardSP.getString("time_create","no createTime"))){
                editor.putString("time_create",createDate.toString());
                editor.putString("time_lastmod",cardSP.getString("time_create",createDate.toString()));
                userSp_editor.putInt("cardNum",(getSharedPreferences("userData",MODE_PRIVATE).getInt("cardNum",0)+1));
                userSp_editor.apply();
            }else{
                editor.putString("time_lastmod",createDate.toString());
            }

            editor.putString("label","white");
            editor.putString("head",head.getText().toString());
            editor.putString("text",text.getText().toString());
            editor.putBoolean("firstEdit",false);
            editor.apply();


        }

    }

}
