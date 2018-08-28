package com.example.oscarplaza.stride;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ListFragmentandDelete extends Fragment
{
    private View element;
    private Semaforo activity;
    private ListView lv;
    private  TextView mensaje;
    public ListFragmentandDelete(){
        // empty
    }

    @Override

    public void setUserVisibleHint(boolean isVisibleToUser) {


        Log.d("TAG", "setUserVisibleHint:no aca divina");


        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && element != null)
        {
            Semaforo activity = (Semaforo) getActivity();

            if(!activity.getData().getData().isEmpty() && !activity.getData().getData().contains(null) )
            {





            }
            else{
                Log.d("TAG", "setUserVisibleHint:no ayuda divina");



            }
        }
        else{

        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("TAG", "onCreateView: 1");
        element = layoutInflater.inflate(R.layout.list_fragmen, viewGroup, false);
        activity = (Semaforo) getActivity();
         mensaje =(TextView) element.findViewById(R.id.textspecial);
         lv= (ListView) element.findViewById(R.id.listP);
        lv.setAdapter(new adapterPoint(getActivity(),activity.getData().getData()));


        return element;

    }

    @Override
    public void onResume() {
        super.onResume();   
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public View getElement() {
        return element;
    }

    public void setElement(View element) {
        this.element = element;
    }
}
