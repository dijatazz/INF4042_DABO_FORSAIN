package org.esiea.dabo_forsain.inf4042_dabo_forsain_good;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JupesShorts extends AppCompatActivity implements View.OnClickListener{

    private CheckBox checkJS1,checkJS2,checkJS3,checkJS4,checkJS5;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jupes_shorts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String AllJS = allJS();

        checkJS1 = (CheckBox) findViewById(R.id.Jupes_Shorts1);
        checkJS2 = (CheckBox) findViewById(R.id.Jupes_Shorts2);
        checkJS3 = (CheckBox) findViewById(R.id.Jupes_Shorts3);
        checkJS4 = (CheckBox) findViewById(R.id.Jupes_Shorts4);
        checkJS5 = (CheckBox) findViewById(R.id.Jupes_Shorts5);


        majCheckBox(AllJS);
        btnSave = (Button) findViewById(R.id.Save);

        btnSave.setOnClickListener(this);
    }

    public void majCheckBox(String tmp){
        StringTokenizer st = new StringTokenizer(tmp,"+");
        int i=0;
        String mot[] = new String[st.countTokens()];

        while (st.hasMoreTokens()) {
            mot[i] = st.nextToken();

            if (mot[i].equals("JS1")){
                checkJS1.setChecked(true);
            }

            else if (mot[i].equals("JS2")){
                checkJS2.setChecked(true);
            }

            else if (mot[i].equals("JS3")){
                checkJS3.setChecked(true);
            }

            else if (mot[i].equals("JS4")){
                checkJS4.setChecked(true);
            }

            else if (mot[i].equals("JS5")){
                checkJS5.setChecked(true);
            }
            i++;
        }

    }

    public void onClick(View v) {
        Toast toast;
        String message="";

        if (checkJS1.isChecked())
            message+=" JS 1 +";
        if (checkJS2.isChecked())
            message+=" JS 2 +";
        if (checkJS3.isChecked())
            message+=" JS 3 +";
        if (checkJS4.isChecked())
            message+=" JS 4 +";
        if (checkJS5.isChecked())
            message+=" JS 5 +";

        if (!message.equals("")){
            String msgToast = message.replace("+", " ");
            toast = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toast.show();
        }

        writeAllJS(message);

        closeAllJS();
    }

    public void writeAllJS(String tmp) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("JS.json", Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'écriture ...");
        }

    }

    public String allJS() {
        FileInputStream fis;
        String  data="";
        try {
            fis= openFileInput("JS.txt");
            char[] charLus = new char[345];
            InputStreamReader isr = new InputStreamReader(fis);
            isr.read(charLus);
            data = new String(charLus);
            fis.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur de lecture ...");
        }
        return data;
    }
    public void closeAllJS(){
        this.finish();
    }

}
