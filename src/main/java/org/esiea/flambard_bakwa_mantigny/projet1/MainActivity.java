package org.esiea.flambard_bakwa_mantigny.projet1;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.*;
import java.text.*;


public class MainActivity extends AppCompatActivity {


    DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(), intentFilter);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_hw = (TextView) findViewById(R.id.tv_hello_world);
        // getString(R.string.hello_world);
        String now = DateUtils.formatDateTime(getApplicationContext(), (new Date()).getTime(), DateFormat.FULL);
        tv_hw.setText(now);
        RecyclerView rv_b = (RecyclerView) findViewById(R.id.rv_biere);
        BiersAdapter bAdapter = new BiersAdapter(getBiersFromFile());

        rv_b.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Button btn_hw = (Button) findViewById(R.id.btn_id);
        Button firstButton = (Button) findViewById(R.id.btn_id2);
        btn_hw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.msg), Toast.LENGTH_LONG).show();
                notification_test("salut les notifs fonctionnent");

            }
        });


        DatePickerDialog.OnDateSetListener odsl = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                TextView tv_hw = (TextView) findViewById(R.id.tv_hello_world);
                tv_hw.setText(day + "/" + (month + 1) + "/" + year);
            }
        };
        Calendar n = Calendar.getInstance();
        dpd = new DatePickerDialog(this, odsl, n.get(Calendar.YEAR), n.get(Calendar.MONTH), n.get(Calendar.DAY_OF_MONTH));


        Button btn_act = (Button) findViewById(R.id.btn_id3);
        btn_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Londre")));


            }
        });

    }

    ;

    public void firstButton(View v) {
        Toast.makeText(getApplicationContext(), getString(R.string.msg), Toast.LENGTH_LONG).show();
        dpd.show();
    }

    public void notification_test(String phrase1) {
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder)
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Mon application de bg")
                        .setContentText(phrase1);
        int id = 001;
        NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notif.notify(id, mBuilder.build());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        int id2 = item.getItemId();
        if (id == R.id.marecherche) {
            Toast.makeText(getApplicationContext(), "Recherche depuis l'action bar !", Toast.LENGTH_LONG).show();
        }
        if (id2 == R.id.uneentrée) {
            Toast.makeText(getApplicationContext(), "Bienvenue dans l'activité 2", Toast.LENGTH_LONG).show();
            Intent login = new Intent(this, SecondeActivity.class);
            startActivity(login);
        }
        return super.onOptionsItemSelected(item);
    }

    public static final String BIERS_UPDATE = "com.octip.cours.inf4042_11.BIERS_UPDATE";

    public class BierUpdate extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("param1", getIntent().getAction());
            notification_test("mise à jour effectuée");
        }
    }

    public JSONArray getBiersFromFile() {
        try {
            InputStream is = new FileInputStream(getCacheDir() + "/" + "bieres.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }


    private class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder> {
        private JSONArray biers;








        public int getItemCount() {
            return biers.length();
        }

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class BierHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            //public RecyclerView mTextView;
            public TextView name;

            public BierHolder(View v) {
                super(v);
                name=(TextView)v.findViewById(R.id.rv_biere_element_name);

            }


        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public BiersAdapter(JSONArray myDataset) {
            biers = myDataset;
        }



        ;

        // Create new views (invoked by the layout manager)
        @Override
        public BiersAdapter.BierHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rv_bier_element, parent, false);
            // set the view's size, margins, paddings and layout parameters
            BierHolder bh = new BierHolder(v);
            return bh;
        }


        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(BierHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            try {
                JSONObject obj=biers.getJSONObject(position);
                String b = obj.getString(name);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                holder.name.setText((Integer) biers.get(position));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }
}






