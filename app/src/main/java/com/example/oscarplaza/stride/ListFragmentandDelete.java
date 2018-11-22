package com.example.oscarplaza.stride;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscarplaza.stride.Entidades.Observacion;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ListFragmentandDelete extends Fragment
{
    private View element;
    private Semaforo activity;
    private ListView lv;
    private  TextView mensaje;
    private Button save;

    public ListView getLv() {
        return lv;
    }

    public void setLv(ListView lv) {
        this.lv = lv;
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public Button getSave() {
        return save;
    }

    public void setSave(Button save) {
        this.save = save;
    }

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
                getSave().setVisibility(View.VISIBLE);





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
         setElement(element);
         setMensaje(mensaje);
         setLv(lv);
         setSave((Button) element.findViewById(R.id.button_save));
        lv.setAdapter(new adapterPoint(getActivity(),activity.getData().getData(),getSave(),getLv(),getMensaje()));
        getSave().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String observedandpoints =  makeJson(activity.getData());
                try {
                    createandsavefile(observedandpoints);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        return element;

    }

    private void createandsavefile(String observedandpoints) throws IOException {
        String baseFolder;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            baseFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        }
        else {
            baseFolder = getActivity().getFilesDir().getAbsolutePath();
        }
        Toast.makeText(getActivity(),baseFolder,Toast.LENGTH_SHORT).show();
        File file = new File(baseFolder + File.separator + "test.json");
        file.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(observedandpoints.getBytes());
        fos.flush();
        fos.close();

    }

    private String makeJson(Observacion data) {
        Gson gson = new Gson();

        String jsArray = gson.toJson(data);
        return jsArray;

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
