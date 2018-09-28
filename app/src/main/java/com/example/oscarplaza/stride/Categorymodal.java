package com.example.oscarplaza.stride;

import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class Categorymodal {
    public void showDialog(FragmentActivity activity, String[] caca, float accuary, double latgoogle, double lngoogle, String votacion)
    {
        DialogPlus dialog = DialogPlus.newDialog(activity)
                .setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.content_dialog_category))
                .setGravity(Gravity.CENTER)
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        View content = dialog.getHolderView();


    }
}
