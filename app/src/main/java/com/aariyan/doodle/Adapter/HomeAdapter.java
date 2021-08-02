package com.aariyan.doodle.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aariyan.doodle.Model.SubCategoryModel;
import com.aariyan.doodle.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<SubCategoryModel> list;

    //instantiating constructor
    public HomeAdapter (Context context, List<SubCategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_home_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeAdapter.ViewHolder holder, int position) {
        SubCategoryModel model = list.get(position);

        //setting the subcategory name
        holder.name.setText(model.getSub_category_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.selectedName);
        }
    }
}
