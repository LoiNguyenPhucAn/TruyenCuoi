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
        // Inflate activity_main layout for m001_frg_splash fragment
        View rootView = inflater.inflate(R.layout.m001_frg_topic, container, false);
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
        super.onAttach(context);
        mContext = context;
    }

    private void initViews(View rootView) {
        LinearLayout lnMain = rootView.findViewById(R.id.ln_topic);

        lnMain.removeAllViews();

        try {
            //Lấy danh sách ảnh trong thư mục …main/assets/photo/ và tạo thành list topic

            String[] listItem = mContext.getAssets().list("photo");

            for (String fileName : listItem) {

                String name = fileName.substring(0, fileName.indexOf("."));

                View vTopic = LayoutInflater.from(mContext).inflate(R.layout.item_topic, null);

                ImageView ivTopic = vTopic.findViewById(R.id.iv_topic);

                TextView tvTopic = vTopic.findViewById(R.id.tv_topic);

                ivTopic.setImageBitmap(BitmapFactory
                        .decodeStream(mContext.getAssets().open("photo/" + fileName)));

                tvTopic.setText(name);

                lnMain.addView(vTopic);

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) vTopic.getLayoutParams();

                params.bottomMargin = 40;

                vTopic.setLayoutParams(params);

                vTopic.setTag(name);//set tag for item topic

                vTopic.setOnClickListener(this);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Override
    public void onClick(View v) {
        // go to M002Screen with parameter is tag of item topic
        ((MainActivity) getActivity()).gotoM002Screen((String)v.getTag());
    }
}