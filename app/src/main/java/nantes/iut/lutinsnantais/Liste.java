package nantes.iut.lutinsnantais;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.*;

import com.android.volley.toolbox.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bean.Enfant;

public class Liste extends AppCompatActivity {

    private ArrayList<Enfant> listeEnfants;
    private ListView listViewEnfants;
    private EnfantAdapter enfantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        // Récupération des données

        String TAG = Liste.class.getSimpleName();
        String url = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=244400404_prenoms-enfants-nes-nantes";

        String sexe = getIntent().getStringExtra("sexe");
        String prenom = getIntent().getStringExtra("prenom");
        String annee_naissance = getIntent().getStringExtra("annee_naissance");

        if (!sexe.isEmpty())
            url += "&refine.sexe=" + sexe.toUpperCase();
        if (!prenom.isEmpty())
            url += "&refine.prenom=" + prenom.toUpperCase();
        if (!annee_naissance.isEmpty())
            url += "&refine.annee_naissance=" + annee_naissance;

        Log.d(TAG, "url: " + url);

        listeEnfants = new ArrayList<Enfant>();

        enfantAdapter = new EnfantAdapter(listeEnfants, getApplicationContext());

        listViewEnfants = (ListView) findViewById(R.id.listeEnfants);

        listViewEnfants.setAdapter(enfantAdapter);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String TAG = Liste.class.getSimpleName();

                    JSONArray enfants = response.getJSONArray("records");

                    ArrayList<String> listePrenoms = new ArrayList<String>();

                    for (int i = 0; i < enfants.length(); i++) {
                        int an = Integer.parseInt(enfants.getJSONObject(i).getJSONObject("fields").getString("annee_naissance"));
                        String p = enfants.getJSONObject(i).getJSONObject("fields").getString("prenom");
                        String s = enfants.getJSONObject(i).getJSONObject("fields").getString("sexe");
                        listeEnfants.add(new Enfant(p, s, an, true));
                    }

                    for (Enfant e : listeEnfants)
                        listePrenoms.add(e.getPrenom());

                    Log.d(TAG, " is array adpater empty ? : " + enfantAdapter.isEmpty());
                    Log.d(TAG, "onResponse: enfantPrenom created");
                    enfantAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onResponse: arrayAdapterUpdated created");


                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String TAG = Liste.class.getSimpleName();
                Log.e(TAG, "Error retrieving kids informations");
            }
        }
        );

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}
