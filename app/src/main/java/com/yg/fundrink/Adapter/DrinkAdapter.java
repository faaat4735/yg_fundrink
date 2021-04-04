package com.yg.fundrink.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yg.fundrink.DataList.Response.HomeInfoResponse;
import com.yg.fundrink.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {

    private String TAG = "DrinkAdapter";
    private List<HomeInfoResponse.DrinkItem> drinkList;
    private int resource;

    public DrinkAdapter(List<HomeInfoResponse.DrinkItem> drinkList, int item_drink_info) {
        Log.e(TAG, "初始化");
        this.drinkList = drinkList;
        this.resource = item_drink_info;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder");
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(resource, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder");
        HomeInfoResponse.DrinkItem item = this.drinkList.get(position);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm") ;
        holder.tv1.setText(format.format(item.getTime()));
        holder.tv2.setText(item.getDrinkType());
        holder.tv3.setText(item.getQuantity() + " ml");
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount:" + String.valueOf(this.drinkList.size()));
        return this.drinkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv_1);
            tv2 = itemView.findViewById(R.id.tv_2);
            tv3 = itemView.findViewById(R.id.tv_3);
        }

    }
}
