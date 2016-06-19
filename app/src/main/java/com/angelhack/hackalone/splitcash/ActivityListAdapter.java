package com.angelhack.hackalone.splitcash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivityListAdapter extends BaseAdapter {

    private static ArrayList<ActivityItem> list = new ArrayList<ActivityItem>();

    private final LayoutInflater inflater;

    public ActivityListAdapter(LayoutInflater layoutInflater) {
        inflater = layoutInflater;

        list.clear();

        list.add(new ActivityItem("John Doe", "debt", "20", "18 Jun 2016", true));
        list.add(new ActivityItem("Lennie Dark", "credit", "16", "19 Jun 2016", true));
        list.add(new ActivityItem("Lennie Dark", "debt", "16", "19 Jun 2016", true));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ActivityItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.activity_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        ActivityItem item = getItem(position);

        holder.contact_name.setText((item.getType() == "debt" ? ">" : "<") + " " + item.getContactName());
        holder.date.setText(item.getDate());
        holder.amount.setText(item.getAmount());

        return view;
    }

    static final class ViewHolder {
        @Bind(R.id.contact_name_in_item)
        TextView contact_name;
        @Bind(R.id.date_in_item)
        TextView date;
        @Bind(R.id.amount_in_item)
        TextView amount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
