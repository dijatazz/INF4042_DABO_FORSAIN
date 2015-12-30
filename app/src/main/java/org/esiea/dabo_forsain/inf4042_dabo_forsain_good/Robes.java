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

public class Robes extends AppCompatActivity implements View.OnClickListener{

    private CheckBox checkRobe1,checkRobe2,checkRobe3,checkRobe4,checkRobe5;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.robes);
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

        String AllRobes = allRobes();

        checkRobe1 = (CheckBox) findViewById(R.id.robe1);
        checkRobe2 = (CheckBox) findViewById(R.id.robe2);
        checkRobe3 = (CheckBox) findViewById(R.id.robe3);
        checkRobe4 = (CheckBox) findViewById(R.id.robe4);
        checkRobe5 = (CheckBox) findViewById(R.id.robe5);


        majCheckBox(AllRobes);
        btnSave = (Button) findViewById(R.id.Save);

        btnSave.setOnClickListener(this);
    }

    public void majCheckBox(String tmp){
        StringTokenizer st = new StringTokenizer(tmp,"+");
        int i=0;
        String mot[] = new String[st.countTokens()];

        while (st.hasMoreTokens()) {
            mot[i] = st.nextToken();

            if (mot[i].equals("robe1")){
                checkRobe1.setChecked(true);
            }

            else if (mot[i].equals("robe2")){
                checkRobe2.setChecked(true);
            }

            else if (mot[i].equals("robe3")){
                checkRobe3.setChecked(true);
            }

            else if (mot[i].equals("robe4")){
                checkRobe4.setChecked(true);
            }

            else if (mot[i].equals("robe5")){
                checkRobe5.setChecked(true);
            }
            i++;
        }

    }

    public void onClick(View v) {
        Toast toast;
        String message="";

        if (checkRobe1.isChecked())
            message+=" Robe 1 +";
        if (checkRobe2.isChecked())
            message+=" Robe 2 +";
        if (checkRobe3.isChecked())
            message+=" Robe 3 +";
        if (checkRobe4.isChecked())
            message+=" Robe 4 +";
        if (checkRobe5.isChecked())
            message+=" Robe 5 +";

        if (!message.equals("")){
            String msgToast = message.replace("+", " ");
            toast = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toast.show();
        }

        writeAllRobes(message);

        closeAllRobes();
    }

    public void writeAllRobes(String tmp) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("Robes.json", Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'écriture ...");
        }

    }

    public String allRobes() {
        FileInputStream fis;
        String  data="";
        try {
            fis= openFileInput("Robes.txt");
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
    public void closeAllRobes(){
        this.finish();
    }

}
