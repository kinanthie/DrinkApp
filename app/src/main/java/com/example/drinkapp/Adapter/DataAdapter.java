package com.example.drinkrecipe.Adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.drinkapp.DetailActivity;
import com.example.drinkapp.Model.DataModel;
import com.example.drinkapp.R;



import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DataModel> dataModelArrayList;
    private Activity activity;

    public DataAdapter(Activity activity, ArrayList<DataModel> dataModelArrayList) {
        this.activity = activity;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 1: {
                View v = inflater.inflate(R.layout.list_item, parent, false);
                viewHolder = new ItemHolder(v);
                break;
            }
            case 2: {
                View v = inflater.inflate(R.layout.list_footer, parent, false);
                viewHolder = new FooterHolder(v);
                break;
            }

        }
        return viewHolder;
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        public TextView txtJudul;

        public ItemHolder(View view) {
            super(view);
            txtJudul = view.findViewById(R.id.txtJudul);
        }
    }


    public static class HeaderHolder extends RecyclerView.ViewHolder {
        public HeaderHolder(View view) {
            super(view);
        }
    }

    public static class FooterHolder extends RecyclerView.ViewHolder {
        public FooterHolder(View view) {
            super(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        if (getItem(position).getViewType() == 1) {
            ItemHolder holder = (ItemHolder) viewHolder;
            holder.txtJudul.setText(getItem(position).getJudul());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    Intent intent = new Intent(activity, DetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dataModel", getItem(position));
                    intent.putExtras(bundle);
                    activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataModelArrayList.get(position).getViewType();
    }

    public DataModel getItem(int position) {
        return (DataModel) dataModelArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

}
