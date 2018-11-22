package com.example.oscarplaza.stride;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.oscarplaza.stride.Entidades.PuntoVotados;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class Categorymodal {
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;





    public void showDialog(final FragmentActivity activity, final String votacion, final semaforoFragment semaforoFragment)
    {
        final DialogPlus dialog = DialogPlus.newDialog(activity)
                .setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.content_dialog_category))
                .setGravity(Gravity.CENTER)
                .setExpanded(true,1100)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        View content = dialog.getHolderView();
        RadioGroup rcategory = (RadioGroup)content.findViewById(R.id.radioGroup);
        rcategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                setCategory(nameCategory(index,activity));
                semaforoFragment.Metodoenvio(getCategory(),votacion);
                dialog.dismiss();



            }
        });

        dialog.show();


    }

    private String nameCategory(int index, FragmentActivity activity) {
        String output ="";

        switch (index){
            case 0:
                output= "Inclusion";
                break;
            case 1:
                output= "Design";
                break;
            case 2:
                output="Integration";
                break;
            case 3:
                output="Planned";
                break;
            case 4:
                output="Safe";
                break;
            case 5:
                output="Security";
                break;
            case 6:
                output=activity.getString(R.string.category_category7);
                break;

        }
        return output;
    }
}

