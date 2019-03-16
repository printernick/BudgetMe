package com.example.budgetme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<BudgetCategory> categories = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<BudgetCategory> categories, Context mContext) {
        this.categories = categories;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.categoryName.setText(categories.get(i).getName());
        viewHolder.budgetAmount.setText(Double.toString(categories.get(i).getBudget()));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        TextView budgetAmount;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView)
        {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
            budgetAmount = itemView.findViewById(R.id.category_budget);
            parentLayout = itemView.findViewById(R.id.parent_layout);


        }
    }
}
