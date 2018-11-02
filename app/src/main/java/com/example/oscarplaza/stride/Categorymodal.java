package com.example.oscarplaza.stride;

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
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        View content = dialog.getHolderView();
        RadioGroup rcategory = (RadioGroup)content.findViewById(R.id.radioGroup);
        rcategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                setCategory(nameCategory(index));
                semaforoFragment.Metodoenvio(getCategory(),votacion);
                dialog.dismiss();



            }
        });

        dialog.show();


    }

    private String nameCategory(int index) {
        String output ="";
        switch (index){
            case 0:
                output="Dangerous traffic";
                break;
            case 1:
                output="Feeling unsafe (people)";
                break;
            case 2:
                output="Bad quality sidewalk";
                break;
            case 3:
                output="Not attractive";
                break;
            case 4:
                output="All is bad!";
                break;
            case 5:
                output="Other";
                break;

        }
        return output;
    }
}

