package org.esiea.flambard_bakwa_mantigny.projet1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static org.esiea.flambard_bakwa_mantigny.projet1.MainActivity.BIERS_UPDATE;

public class SecondeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);
        GetBiersServices.startActionBiers(this,"http://binouze.fabrigli.fr/bieres.json");

    }







}
