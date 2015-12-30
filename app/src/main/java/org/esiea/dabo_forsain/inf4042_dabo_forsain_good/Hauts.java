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

public class Hauts extends AppCompatActivity implements View.OnClickListener{

    private CheckBox checkHaut1,checkHaut2,checkHaut3,checkHaut4,checkHaut5;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hauts);
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

        String AllHauts = allHauts();

        checkHaut1 = (CheckBox) findViewById(R.id.haut1);
        checkHaut2 = (CheckBox) findViewById(R.id.haut2);
        checkHaut3 = (CheckBox) findViewById(R.id.haut3);
        checkHaut4 = (CheckBox) findViewById(R.id.haut4);


        majCheckBox(AllHauts);
        btnSave = (Button) findViewById(R.id.Save);

        btnSave.setOnClickListener(this);
    }

    public void majCheckBox(String tmp){
        StringTokenizer st = new StringTokenizer(tmp,"+");
        int i=0;
        String mot[] = new String[st.countTokens()];

        while (st.hasMoreTokens()) {
            mot[i] = st.nextToken();

            if (mot[i].equals("haut1")){
                checkHaut1.setChecked(true);
            }

            else if (mot[i].equals("haut2")){
                checkHaut2.setChecked(true);
            }

            else if (mot[i].equals("haut3")){
                checkHaut3.setChecked(true);
            }

            else if (mot[i].equals("haut4")){
                checkHaut4.setChecked(true);
            }

            else if (mot[i].equals("haut5")){
                checkHaut5.setChecked(true);
            }
            i++;
        }

    }

    public void onClick(View v) {
        Toast toast;
        String message="";

        if (checkHaut1.isChecked())
            message+=" Haut 1+";
        if (checkHaut2.isChecked())
            message+=" Haut 2+";
        if (checkHaut3.isChecked())
            message+=" Haut 3+";
        if (checkHaut4.isChecked())
            message+=" Haut 4+";
        if (checkHaut5.isChecked())
            message+=" Haut 5+";

        if (!message.equals("")){
            String msgToast = message.replace("+", " ");
            toast = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toast.show();
        }

        writeAllHauts(message);

        closeAllHauts();
    }

    public void writeAllHauts(String tmp) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("Hauts.json", Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'écriture ...");
        }

    }

    public String allHauts() {
        FileInputStream fis;
        String  data="";
        try {
            fis= openFileInput("Hauts.txt");
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
    public void closeAllHauts(){
        this.finish();
    }

}
