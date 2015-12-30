package org.esiea.dabo_forsain.inf4042_dabo_forsain_good;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.R;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Accessoires extends AppCompatActivity implements View.OnClickListener{

    private CheckBox checkAccessoire1,checkAccessoire2,checkAccessoire3,checkAccessoire4,checkAccessoire5;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accessoires);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String AllAccessoires = allAccessoires();

        checkAccessoire1 = (CheckBox) findViewById(R.id.accessoire1);
        checkAccessoire2 = (CheckBox) findViewById(R.id.accessoire2);


        majCheckBox(AllAccessoires);
        btnSave = (Button) findViewById(R.id.Save);

        btnSave.setOnClickListener(this);
    }

    public void majCheckBox(String tmp){
        StringTokenizer st = new StringTokenizer(tmp,"+");
        int i=0;
        String mot[] = new String[st.countTokens()];

        while (st.hasMoreTokens()) {
            mot[i] = st.nextToken();

            if (mot[i].equals("Accessoire1")){
                checkAccessoire1.setChecked(true);
            }

            else if (mot[i].equals("accessoire2")){
                checkAccessoire2.setChecked(true);
            }
            i++;
        }

    }

    public void onClick(View v) {
        Toast toast;
        String message="";

        if (checkAccessoire1.isChecked())
            message+=" Accessoire 1 +";
        if (checkAccessoire2.isChecked())
            message+=" Accessoire 2 +";

        if (!message.equals("")){
            String msgToast = message.replace("+", " ");
            toast = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toast.show();
        }

        writeAllAccessoires(message);

        closeAllAccessoires();
    }

    public void writeAllAccessoires(String tmp) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("Accessoires.json", Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'écriture ...");
        }

    }

    public String allAccessoires() {
        FileInputStream fis;
        String  data="";
        try {
            fis= openFileInput("Accessoires.txt");
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
    public void closeAllAccessoires(){
        this.finish();
    }

}
