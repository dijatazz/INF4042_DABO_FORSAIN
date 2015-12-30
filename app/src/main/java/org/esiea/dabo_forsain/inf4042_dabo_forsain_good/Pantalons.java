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

public class Pantalons extends AppCompatActivity implements View.OnClickListener{

    private CheckBox checkPant1,checkPant2,checkPant3,checkPant4,checkPant5,checkPant6;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalons);
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

        String AllPantalons = allPantalons();

        checkPant1 = (CheckBox) findViewById(R.id.pant1);
        checkPant2 = (CheckBox) findViewById(R.id.pant2);
        checkPant3 = (CheckBox) findViewById(R.id.pant3);
        checkPant4 = (CheckBox) findViewById(R.id.pant4);
        checkPant5 = (CheckBox) findViewById(R.id.pant5);
        checkPant6 = (CheckBox) findViewById(R.id.pant6);

        majCheckBox(AllPantalons);
        btnSave = (Button) findViewById(R.id.Save);

        btnSave.setOnClickListener(this);
    }

    public void majCheckBox(String tmp){
        StringTokenizer st = new StringTokenizer(tmp,"+");
        int i=0;
        String mot[] = new String[st.countTokens()];

        while (st.hasMoreTokens()) {
            mot[i] = st.nextToken();

            if (mot[i].equals("pant1")){
                checkPant1.setChecked(true);
            }

            else if (mot[i].equals("pant2")){
                checkPant2.setChecked(true);
            }

            else if (mot[i].equals("pant3")){
                checkPant3.setChecked(true);
            }

            else if (mot[i].equals("pant4")){
                checkPant4.setChecked(true);
            }

            else if (mot[i].equals("pant5")){
                checkPant5.setChecked(true);
            }

            else if (mot[i].equals("pant6")){
                checkPant6.setChecked(true);
            }
            i++;
        }

    }

    public void onClick(View v) {
        Toast toast;
        String message="";

        if (checkPant1.isChecked())
            message+=" Pantalon 1+";
        if (checkPant2.isChecked())
            message+=" Pantalon 2+";
        if (checkPant3.isChecked())
            message+=" Pantalon 3+";
        if (checkPant4.isChecked())
            message+=" Pantalon 4+";
        if (checkPant5.isChecked())
            message+=" Pantalon 5+";
        if (checkPant6.isChecked())
            message+=" Pantalon 6+";

        if (!message.equals("")){
            String msgToast = message.replace("+", " ");
            toast = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toast.show();
        }

        writeAllPantalons(message);

        closeAllPantalons();
    }

    public void writeAllPantalons(String tmp) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("Pantalons.json", Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'écriture ...");
        }

    }

    public String allPantalons() {
        FileInputStream fis;
        String  data="";
        try {
            fis= openFileInput("Pantalons.txt");
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
    public void closeAllPantalons(){
        this.finish();
    }

}
