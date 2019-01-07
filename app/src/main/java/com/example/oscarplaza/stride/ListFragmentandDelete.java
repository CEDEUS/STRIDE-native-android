package com.example.oscarplaza.stride;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
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
import com.orhanobut.dialogplus.DialogPlus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
            { getSave().setVisibility(View.VISIBLE);}
            else{Log.d("TAG", "setUserVisibleHint:no ayuda divina");}}
        else{}
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
                createandsavefile(observedandpoints);
            }
        });


        return element;

    }

    private void createandsavefile(String observedandpoints)  {
        String baseFolder = Environment.getDataDirectory().getAbsolutePath();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            baseFolder = Environment.DIRECTORY_DOCUMENTS;
        }File yourAppDir = new File(Environment.getExternalStorageDirectory()+File.separator+Environment.DIRECTORY_DOCUMENTS+File.separator+"Stride");
        if(!yourAppDir.exists() && !yourAppDir.isDirectory())
        {if (yourAppDir.mkdirs()){ CreateFile(observedandpoints,yourAppDir);} else{ }
        }
        else
        {
            CreateFile(observedandpoints,yourAppDir);
        }
        }

    private void CreateFile(String observedandpoints, File yourAppDir) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        File file = new File(yourAppDir.getAbsolutePath() + File.separator + timeStamp +".json");
        file.getParentFile().mkdirs();
        try{
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(observedandpoints.getBytes());
        fos.flush();
        fos.close();
        showposition(file.getAbsolutePath());}
        catch (Exception ex){
            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data",observedandpoints);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getActivity(),getActivity().getText(R.string._aviso_copyblipboard),Toast.LENGTH_LONG).show();
        }
    }

    private void showposition(String absolutePath) {
        ViewDialog vd = new ViewDialog();
        vd.ConfirmYou(getActivity(),absolutePath);

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
