package com.aariyan.doodle.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aariyan.doodle.R;
import com.aariyan.doodle.Model.SubCategoryModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    //Member variable for the context:
    private Context context;
    //list for catching the coming images:
    private List<SubCategoryModel> list;

    //Constructor for instantiating the data:
    public SubCategoryAdapter(Context context, List<SubCategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull SubCategoryAdapter.ViewHolder holder, int position) {
        SubCategoryModel model = list.get(position);
        holder.name.setText(model.getSub_category_name());

        //checking if already selected from the past
        if (model.isSelected()) {
            holder.selection.setImageResource(R.drawable.check);

        } else {
            holder.selection.setImageResource(R.drawable.add_icon);
        }

        //select, unselect action
        holder.selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setSelected(!model.isSelected());
                if (model.isSelected()) {
                    holder.selection.setImageResource(R.drawable.check);

                } else {
                    holder.selection.setImageResource(R.drawable.add_icon);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView selection;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            //instantiating data
            name = itemView.findViewById(R.id.subCategoryName);
            selection = itemView.findViewById(R.id.selectSubCategory);
        }
    }

    //getting all the selected items
    public List<SubCategoryModel>  getSelected() {
        List<SubCategoryModel> selected = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelected()) {
                selected.add(list.get(i));
            }
        }
        return selected;

    }
}
