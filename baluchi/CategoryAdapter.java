package com.example.baluchi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    Context context;

    ArrayList<CategoryClassGetter> categories;

    public CategoryAdapter(Context context, ArrayList<CategoryClassGetter> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryView = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        CategoryClassGetter categoryC = categories.get(position);
        holder.category.setText(categoryC.getCategory());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryHolder extends RecyclerView.ViewHolder{

        TextView category;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.category);
        }
    }
}
