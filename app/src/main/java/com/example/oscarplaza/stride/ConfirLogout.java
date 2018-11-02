package com.example.oscarplaza.stride;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import static android.content.Context.MODE_PRIVATE;

public class ConfirLogout {
    public void showAlert(final MainActivity _this) {
        final DialogPlus dialog = DialogPlus.newDialog(_this)
                .setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.confir_logout))
                .setGravity(Gravity.CENTER)
                .setExpanded(true)
                .create();
        View content = dialog.getHolderView();
        Button yes = (Button)content.findViewById(R.id.logout_yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _this.confirmlogout();

            }
        });
        Button no = (Button)content.findViewById(R.id.logout_no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();







    }
}
