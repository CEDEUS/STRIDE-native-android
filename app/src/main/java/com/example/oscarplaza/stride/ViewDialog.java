package com.example.oscarplaza.stride;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    public void  ConfirmYou(Context activity,String direccion)
    {
        final DialogPlus dialog = DialogPlus.newDialog(activity).setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.content_dialog_confirm))
                .setFooter(R.layout.footer)
                .setGravity(Gravity.CENTER)
                .setExpanded(true)
                .create();
        View content = dialog.getHolderView();
        View footer = dialog.getFooterView();

        dialog.show();
        ((TextView)content.findViewById(R.id.mensaje)).setText(direccion);
        ((Button)footer.findViewById(R.id.footer_close_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

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

    public void ShowSelectedpoints(final MainActivity activity, int count) {
        DialogPlus dialog = DialogPlus.newDialog(activity)
                .setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.content_changecount))
                .setGravity(Gravity.CENTER)

                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        View content = dialog.getHolderView();
        RadioGroup rcategory = (RadioGroup)content.findViewById(R.id.radiolast);
        rcategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                activity.setLastPointRequest(counta(index));
            }
        });
        dialog.show();


    }

    private String counta(int index) {
        String output ="";
        switch (index){
            case 0:
                output="10";
                break;
            case 1:
                output="100";
                break;
            case 2:
                output="200";
                break;
            case 3:
                output="400";
                break;
        }
        return output;
    }

    public void nohaveinternet(Activity loginActivity) {
        new AlertDialog.Builder(loginActivity)
                .setTitle(loginActivity.getString(R.string.title_no_conection))
                .setMessage(loginActivity.getString(R.string.content_no_conection))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                    }}).show();


    }
}

