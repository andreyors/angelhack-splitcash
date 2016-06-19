package com.angelhack.hackalone.splitcash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import butterknife.ButterKnife;
import butterknife.Bind;

public class ActivityListAdapter extends BaseAdapter {

    LayoutInflater inflater;

    public ActivityListAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        View view = inflater.inflate(R.layout.activity_item, parent, false);
        holder = new ViewHolder(view);

        Picasso.with(inflater.getContext())
                .load("https://split-cash-justhack.c9users.io/api/contacts/" + (position+1))
                .into(holder.image);

        holder.text.setText("This is a text for the image number: "+position);

        return view;
    }

    static class ViewHolder{
        @Bind(R.id.image_in_item)
        ImageView image;

        @Bind(R.id.textview_in_item)
        TextView text;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
