package com.angelhack.hackalone.splitcash;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.AbsListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AskForMoneyActivity  extends AppCompatActivity {
    private static final String TAG = "AskForMoneyActivity";

    @Bind(R.id.activities)
    AbsListView mListView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_money);
        ButterKnife.bind(this);

        ArrayList ActivitiesList = new ArrayList<ActivityItem>();

        if(ActivitiesList.size() == 0) {
            ActivitiesList.add(new ActivityItem("No Devices", "", "", "", true));
        }

        mListView.setAdapter(new ActivityListAdapter((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)));
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

}
