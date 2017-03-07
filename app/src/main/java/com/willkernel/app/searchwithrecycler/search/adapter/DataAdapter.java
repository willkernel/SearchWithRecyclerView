package com.willkernel.app.searchwithrecycler.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.willkernel.app.searchwithrecycler.R;
import com.willkernel.app.searchwithrecycler.model.bean.AndroidVersion;
import com.willkernel.app.searchwithrecycler.search.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by willkernel on 2017/3/7.
 * mail:willkerneljc@gmail.com
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {
    private ArrayList<AndroidVersion> mArrayList;
    private ArrayList<AndroidVersion> mFilteredList;

    public DataAdapter(ArrayList<AndroidVersion> mArrayList) {
        this.mArrayList = mArrayList;
        this.mFilteredList = mArrayList;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText(mFilteredList.get(position).getName());
        holder.tv_version.setText(mFilteredList.get(position).getVer());
        holder.tv_api_level.setText(mFilteredList.get(position).getApi());
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    mFilteredList = mArrayList;
                } else {
                    ArrayList<AndroidVersion> filterList = new ArrayList<>();
                    for (AndroidVersion androidVersion : mArrayList) {
                        if (androidVersion.getApi().toLowerCase().contains(charString) || androidVersion.getName().toLowerCase().contains(charString) || androidVersion.getVer().toLowerCase().contains(charString)) {
                            filterList.add(androidVersion);
                        }
                    }
                    mFilteredList = filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //noinspection unchecked
//                mFilteredList = (ArrayList<AndroidVersion>) results.values;
                notifyDataSetChanged();
                Log.e(MainActivity.TAG, "results=" + results);
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_version, tv_api_level;

        ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_version = (TextView) view.findViewById(R.id.tv_version);
            tv_api_level = (TextView) view.findViewById(R.id.tv_api_level);
        }
    }
}
