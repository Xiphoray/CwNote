package ericwyn.cwnote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 作为瀑布流布局RecyclerView的数据适配器
 * Created by Ericwyn on 2017/1/20.
 */

public class WaterFullAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<MyCard> mData;

    public WaterFullAdapter(Context context, List<MyCard> data){
        this.mContext=context;
        this.mData=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.main_activity_item,null);
        return new MyViewHolder(view);
    }

    //将数据源绑定到相应的控件上面
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder2=(MyViewHolder) holder;
        MyCard myCard=mData.get(position);
        holder2.cardName.setText(myCard.getName());
        holder2.cardText.setText(myCard.getText());

    }

    @Override
    public int getItemCount() {
        if(mData!=null){
            return mData.size();
        }
        return 0;
    }

    //定义自己的ViewHolder，将View的控件引用在成员变量上
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView cardName;
        public TextView cardText;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardName = (TextView) itemView.findViewById(R.id.cardName);
            cardText = (TextView) itemView.findViewById(R.id.cardText);
        }
    }

}
