package com.example.truyencuoi;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class M001TopicFrg extends Fragment implements View.OnClickListener {

    private Context mContext;

    /*onCreateView(): Inflate the XML layout for the Fragment in this callback.
     The system calls this method to draw the Fragment UI for the first time.
     As a result, the Fragment is visible in the Activity.
     To draw a UI for your Fragment, you must return the root View of your Fragment layout.
     Return null if the Fragment does not have a UI.*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate activity_main layout for m001_frg_topic layout
        View rootView = inflater.inflate(R.layout.m001_frg_topic, container, false);
        // to draw item topic
        initViews(rootView);
        //To draw a UI for your Fragment, you must return the root View of your Fragment layout.
        return rootView;

    }

    /*onAttach(): Called when a Fragment is first attached to a host Activity.
    Use this method to check if the Activity has implemented the required listener callback
    for the Fragment (if a listener interface was defined in the Fragment).
    After this method, onCreate()is called.*/
    @Override
    public void onAttach(Context context) {
        // In case, override this method you must call through to the superclass implementation.
        super.onAttach(context);
        mContext = context;
    }

    //this method used to draw item topic
    private void initViews(View rootView) {
        LinearLayout lnMain = rootView.findViewById(R.id.ln_topic);

        lnMain.removeAllViews();

        try {
            //L???y danh s??ch ???nh trong th?? m???c ???main/assets/photo/ v?? t???o th??nh list topic
            String[] listItem = mContext.getAssets().list("photo");

            for (String fileName : listItem) {
                // l???y t??n file bitmap trong danh s??ch ???nh
                String name = fileName.substring(0, fileName.indexOf("."));
                // l???ng layout item_topic.xml v??o layout m001_frg_topic
                View vTopic = LayoutInflater.from(mContext).inflate(R.layout.item_topic, null);

                ImageView ivTopic = vTopic.findViewById(R.id.iv_topic);
                TextView tvTopic = vTopic.findViewById(R.id.tv_topic);

                // set image b???ng file bitmap v??o layout item_topic.xml
                ivTopic.setImageBitmap(BitmapFactory
                        .decodeStream(mContext.getAssets().open("photo/" + fileName)));
                // set text v??o layout item_topic.xml
                tvTopic.setText(name);
                //add layout item_topic v??o LinearLayout trong layout m001_frg_topic
                lnMain.addView(vTopic);
                // 3 c??u l???nh b??n d?????i d??ng ????? ch???nh s???a l???i margin bottom cho item_topic
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) vTopic.getLayoutParams();
                params.bottomMargin = 40;
                vTopic.setLayoutParams(params);
                // set tag cho item topic = t??n file bitmap
                vTopic.setTag(name);
                // s??? ki???n click on item topic
                vTopic.setOnClickListener(this);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        // go to M002Screen with a tag of item topic
        // by a way coll gotoM002Screen method in MainActivity
        ((MainActivity) getActivity()).gotoM002Screen((String) v.getTag());
    }
}