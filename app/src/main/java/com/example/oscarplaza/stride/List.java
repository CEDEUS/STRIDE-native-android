package com.example.oscarplaza.stride;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oscarplaza.stride.Entidades.Observacion;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class List extends Fragment{


    public List() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            Semaforo activity = (Semaforo) getActivity();

            if(!activity.getData().getData().isEmpty() && !activity.getData().getData().contains(null) )
            {

            }
            else{
            }
        }else
         {

          }
    }
}
