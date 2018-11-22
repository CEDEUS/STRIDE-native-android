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
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.Date;

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
        View content = dialog.getHolderView();
        TextView tx = (TextView)content.findViewById(R.id.about_text_info);
        String about = (String) tx.getText();
        Date buildDate = new Date(BuildConfig.TIMESTAMP);

        about = about+'\n'+BuildConfig.VERSION_NAME +'\n'+buildDate.toString();
        tx.setText(about);

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

    public void showIdiomsOptions(final MainActivity activity) {
        final DialogPlus dialog = DialogPlus.newDialog(activity)
                .setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.content_dialogchosenidioms))
                .setGravity(Gravity.CENTER)
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        View content = dialog.getHolderView();
        RadioGroup rcategory = (RadioGroup)content.findViewById(R.id.radioidioms);
        rcategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                activity.setIdioms(nameIdioms(index));
                }
        });
        dialog.show();
        }
        private String nameIdioms(int index)
    {
        String output ="";
        switch (index){
            case 0:
                output="es";
                break;
            case 1:
                output="en";
                break;
                }
        return output;
    }
}

