package com.example.geo.studtrans.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.geo.studtrans.R;
import com.example.geo.studtrans.entity.Statie;
import com.example.geo.studtrans.entity.Traseu;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Geo on 1/10/2015.
 */
public class Trasee extends Activity {

    ListView listView;
    ParseObject traseu;
    Boolean isFinal = false;
    public final static List<Traseu> trasee = new ArrayList<Traseu>();
    public static int selected=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.traseu_layout);

        // Get ListView object
        listView = (ListView) findViewById(R.id.list);

        getTrasee(listView);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;
                selected = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        trasee.get(itemPosition).toString(), Toast.LENGTH_LONG)
                        .show();

                Intent hartaTraseeIntent= new Intent(Trasee.this,HartaTrasee.class);
                startActivity(hartaTraseeIntent);

            }

        });
    }



    private void getTrasee(final ListView listView) {
        Parse.initialize(this, "MxaJXEypSN95Yq2Jk5zt4Cx19JL5LVfLJTPlXFO6", "4OHqg9WWzi3qDTkHap9FjfnJSQ8rsAGvghA1VBUa");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Traseu");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> traseeParse, ParseException e) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Trasee.this,
                        android.R.layout.simple_list_item_1);
                if (e == null) {
                    if (traseeParse != null) {
                        for (ParseObject traseu : traseeParse) {
                            String numeTraseu = (String) traseu.get("NUME");

                            Integer idTraseu = (Integer) traseu.get("ID");
                            Traseu modelTraseu = new Traseu();
                            modelTraseu.setId(idTraseu);
                            modelTraseu.setName(numeTraseu);
                            fillTraseu(modelTraseu);
                            trasee.add(modelTraseu);
                            adapter.add(modelTraseu.toString());
                        }
                    }
                } else {
                    e.printStackTrace();
                }
                listView.setAdapter(adapter);
            }
        });
    }


    private void fillTraseu(final Traseu traseu){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TRASEU_TO_STATIE");
        final Map<Integer,Integer> traseuMap = new HashMap<Integer,Integer>();
        query.whereEqualTo("IDTRASEU", traseu.getId());

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> traseuEntries, ParseException e) {
                if (e == null) {
                    if (traseuEntries != null) {
                        List<Statie> statii=new ArrayList<Statie>();
                        for (ParseObject traseuEntry : traseuEntries) {
                            Integer idStatie = (Integer) traseuEntry.get("IDSTATIE");
                            Integer indexTraseu = (Integer) traseuEntry.get("INDEX_TRASEU");
                            traseuMap.put(idStatie,indexTraseu);
                            getStatie(idStatie,traseu,indexTraseu);

                        }
                       }
                }
            }
        });
    }

    private void getStatie(final Integer id,final Traseu traseu,final int order){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Statie");
        query.whereEqualTo("ID", id);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> elements, ParseException e) {
                if (e == null) {
                    if (elements != null) {
                        ParseObject statie=elements.get(0);
                        Statie modelStatie=new Statie();
                        modelStatie.setId(id);
                        modelStatie.setName((String) statie.get("NUME"));
                        modelStatie.setCx((Double) statie.get("CX"));
                        modelStatie.setCy((Double) statie.get("CY"));
                        traseu.getStatii().put(modelStatie, order);
                    }
                }else
                {
                    e.printStackTrace();
                }
            }
        });

    }
}
