package com.example.baluchi;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder>{

    private List<SearchClassGetter> itemList;

    public SearchAdapter(List<SearchClassGetter> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item, parent, false);
        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        SearchClassGetter item = itemList.get(position);
        holder.textView1.setText(item.getText1());
        holder.textView2.setText(item.getText2());
        holder.textView3.setText(item.getText3());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class SearchHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.searchName);
            textView2 = itemView.findViewById(R.id.searchDescription);
            textView3 = itemView.findViewById(R.id.searchPrice);
        }
    }
}
