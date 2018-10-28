package com.example.oscarplaza.stride;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

public class ViewDialog {
    public void showDialog(Context activity, String msg, LatLng latLng){
        DialogPlus dialog = DialogPlus.newDialog(activity)
                .setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.content_dialog))
                .setHeader(R.layout.header)
                .setFooter(R.layout.footer)
                .setGravity(Gravity.CENTER)

                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        View header = dialog.getHeaderView();
        TextView txHeader = (TextView) header.findViewById(R.id.dialog_header_text);
        txHeader.setText(msg);
        txHeader.setBackgroundColor(Color.BLUE);

        dialog.show();

    }

    public void showAbout(Context activity) {
        DialogPlus dialog = DialogPlus.newDialog(activity)
                .setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.content_dialogabout))
                .setGravity(Gravity.CENTER)

                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        dialog.show();



    }

    public void showFeedback(Context activity) {
        DialogPlus dialog = DialogPlus.newDialog(activity)
                .setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.content_dialogfeedback))
                .setGravity(Gravity.CENTER)

                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        dialog.show();
    }

    public void showIdiomsOptions(MainActivity activity) {
        DialogPlus dialog = DialogPlus.newDialog(activity)
                .setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.content_dialogchosenidioms))
                .setGravity(Gravity.CENTER)

                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        dialog.show();

    }
}

