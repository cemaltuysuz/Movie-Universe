package com.thic.marvelmovies.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thic.marvelmovies.Model.models.CategoryModel;
import com.thic.marvelmovies.R;

import java.util.List;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.VerticalHolder> {

    private Context context;
    private List<CategoryModel> categoryList;

    public VerticalAdapter(Context context, List<CategoryModel> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public VerticalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.priv_layout_vertical,parent,false);
        return new VerticalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalHolder holder, int position) {
        CategoryModel currentItem = categoryList.get(position);

        holder.categoryTitle.setText(currentItem.getCategoryTitle());
        HorizontalAdapter adapter = new HorizontalAdapter(context,categoryList.get(position).getMovieList());
        holder.horizontal.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.horizontal.setAdapter(adapter);

        if (position == categoryList.size()-1) holder.layout.setPadding(0,0,0, 130);
        else holder.layout.setPadding(0,0,0, 0);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class VerticalHolder extends RecyclerView.ViewHolder {
        TextView categoryTitle;
        RecyclerView horizontal;
        LinearLayout layout;
        public VerticalHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.categoryText);
            horizontal = itemView.findViewById(R.id.horizontalRecyclerview);
            layout = itemView.findViewById(R.id.Linear);
        }
    }
}
