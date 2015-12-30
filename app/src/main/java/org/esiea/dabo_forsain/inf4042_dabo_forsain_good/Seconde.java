package org.esiea.dabo_forsain.inf4042_dabo_forsain_good;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.R;

public class Seconde extends AppCompatActivity implements OnClickListener {

    private static final int Panier = 0;
    private static final int Hauts = 1;
    private static final int Pantalons = 2;
    private static final int Robes = 3;
    private static final int Jupes_Shorts = 4;
    private static final int Accessoires = 5;
    private static final int Chaussures = 6;

    private Button[] Buttons = new Button[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);
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

        Buttons[Panier] = ((Button) this.findViewById(R.id.Panier));
        Buttons[Hauts] = ((Button) this.findViewById(R.id.Hauts));
        Buttons[Pantalons] = ((Button) this.findViewById(R.id.Pantalons));
        Buttons[Jupes_Shorts] = ((Button) this.findViewById(R.id.Jupes_Shorts));
        Buttons[Accessoires] = ((Button) this.findViewById(R.id.Accessoires));
        Buttons[Chaussures] = ((Button) this.findViewById(R.id.Chaussures));
        Buttons[Robes] = ((Button) this.findViewById(R.id.Robes));

        for (int i=0;i<Buttons.length; i++){
            Buttons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        String message="";

        Activity act = null;

        switch (v.getId()){
            //case R.id.Panier:
                //message="Votre panier";
                //act = new Panier();
                //break;

            case R.id.Hauts:
                message="Hauts";
                act = new Hauts();
                break;

            case R.id.Pantalons:
                message="Pantalons";
                act = new Pantalons();
                break;

            case R.id.Jupes_Shorts:
                message="Jupes et Shorts";
                act = new JupesShorts();
                break;

            case R.id.Accessoires:
                message="Accessoires";
                act = new Accessoires();
                break;

            case R.id.Robes:
                message="Robes";
                act = new Robes();
                break;

            case R.id.Chaussures:
                message="Chaussures";
                act = new Chaussures();
                break;
        }

        if(act != null) {
            Intent i= new Intent(getApplicationContext(), act.getClass());
            startActivity(i);
        }

        Toast msgToast= Toast.makeText(this, message, Toast.LENGTH_SHORT);
        msgToast.show();

    }

}
