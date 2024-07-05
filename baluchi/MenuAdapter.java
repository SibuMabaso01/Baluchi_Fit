package com.example.baluchi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder>{

    private List<MenuClassGetter> itemList;

    public MenuAdapter(List<MenuClassGetter> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MenuAdapter.MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item, parent, false);
        return new MenuAdapter.MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuHolder holder, int position) {
        MenuClassGetter item = itemList.get(position);
        holder.textView1.setText(item.getText1());
        holder.textView2.setText(item.getText2());
        holder.textView3.setText(item.getText3());

//        holder.textView1.setText(item.setText1());
//        holder.textView2.setText(item.setText2());
//        holder.textView3.setText(item.setText3());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MenuHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;

        public MenuHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.menuName);
            textView2 = itemView.findViewById(R.id.menuDescription);
            textView3 = itemView.findViewById(R.id.menuPrice);
        }
    }

}
