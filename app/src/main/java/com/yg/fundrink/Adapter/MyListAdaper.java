package com.yg.fundrink.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.yg.fundrink.DataList.Response.MyListItem;
import com.yg.fundrink.Interface.OnItemClickListener;
import com.yg.fundrink.R;

import java.util.List;

/**
 * @author go
 * time：2020/11/2
 * describe:
 */
public class MyListAdaper extends RecyclerView.Adapter<MyListAdaper.ViewHolder> {
    private List<MyListItem> mylist;
    private int resource;
    private OnItemClickListener onItemClickListener;


    public MyListAdaper(List<MyListItem> mylist, int resource) {
        this.mylist = mylist;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyListItem item = mylist.get(position);
        holder.left_icon.setImageResource(item.getLeft_iconid());
        holder.title.setText(item.getTitle());
        holder.right_img.setImageResource(item.getRight_imgid());
        if (!item.getVersion().equals("")){
            holder.right_img.setVisibility(View.INVISIBLE);
            holder.tv_version.setVisibility(View.VISIBLE);
            holder.tv_version.setText(item.getVersion());
        }

        holder.lyout.setOnClickListener(v -> {
            if (this.onItemClickListener != null) {
                this.onItemClickListener.onItemClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView left_icon;
        TextView title;
        ImageView right_img;
        TextView tv_version;
        ConstraintLayout lyout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            left_icon = itemView.findViewById(R.id.iv_icon);
            title = itemView.findViewById(R.id.title);
            right_img = itemView.findViewById(R.id.iv_flg);
            tv_version = itemView.findViewById(R.id.tv_version);
            lyout = itemView.findViewById(R.id.lyout);
        }

    }
}
