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

public class Chaussures extends AppCompatActivity implements View.OnClickListener{

    private CheckBox checkChaussure1,checkChaussure2,checkChaussure3,checkChaussure4,checkChaussure5;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chaussures);
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

        String AllChaussures = allChaussures();

        checkChaussure1 = (CheckBox) findViewById(R.id.chaussure1);
        checkChaussure2 = (CheckBox) findViewById(R.id.chaussure2);
        checkChaussure3 = (CheckBox) findViewById(R.id.chaussure3);
        checkChaussure4 = (CheckBox) findViewById(R.id.chaussure4);
        checkChaussure5 = (CheckBox) findViewById(R.id.Chaussure5);


        majCheckBox(AllChaussures);
        btnSave = (Button) findViewById(R.id.Save);

        btnSave.setOnClickListener(this);
    }

    public void majCheckBox(String tmp){
        StringTokenizer st = new StringTokenizer(tmp,"+");
        int i=0;
        String mot[] = new String[st.countTokens()];

        while (st.hasMoreTokens()) {
            mot[i] = st.nextToken();

            if (mot[i].equals("chaussure1")){
                checkChaussure1.setChecked(true);
            }

            else if (mot[i].equals("chaussure2")){
                checkChaussure2.setChecked(true);
            }

            else if (mot[i].equals("chaussure3")){
                checkChaussure3.setChecked(true);
            }

            else if (mot[i].equals("chaussure4")){
                checkChaussure4.setChecked(true);
            }

            else if (mot[i].equals("chaussure5")){
                checkChaussure5.setChecked(true);
            }
            i++;
        }

    }

    public void onClick(View v) {
        Toast toast;
        String message="";

        if (checkChaussure1.isChecked())
            message+=" Chaussure 1 +";
        if (checkChaussure2.isChecked())
            message+=" Chaussure 2 +";
        if (checkChaussure3.isChecked())
            message+=" Chaussure 3 +";
        if (checkChaussure4.isChecked())
            message+=" Chaussure 4 +";
        if (checkChaussure5.isChecked())
            message+=" Chaussure 5 +";

        if (!message.equals("")){
            String msgToast = message.replace("+", " ");
            toast = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toast.show();
        }

        writeAllChaussures(message);

        closeAllChaussures();
    }

    public void writeAllChaussures(String tmp) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("Chaussures.json", Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'écriture ...");
        }

    }

    public String allChaussures() {
        FileInputStream fis;
        String  data="";
        try {
            fis= openFileInput("Chaussures.txt");
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
    public void closeAllChaussures(){
        this.finish();
    }

}
