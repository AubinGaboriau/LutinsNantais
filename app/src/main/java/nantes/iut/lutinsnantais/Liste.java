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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String sexe = getIntent().getStringExtra("sexe");
        String prenom = getIntent().getStringExtra("prenom");
        String annee_naissance = getIntent().getStringExtra("annee_naissance");

        // Récupération des données

        String url = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=244400404_prenoms-enfants-nes-nantes";


        if (!sexe.isEmpty())
            url += "&refine.sexe=" + sexe;
        if (!prenom.isEmpty())
            url += "&refine.prenom=" + prenom;
        if (!annee_naissance.isEmpty())
            url += "&refine.annee_naissance=" + annee_naissance;

        String TAG = Liste.class.getSimpleName();
        Log.d(TAG, "URL : " + url);


        final ArrayList<Enfant> listeEnfants = new ArrayList<Enfant>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String TAG = Liste.class.getSimpleName();

                            JSONArray enfants = response.getJSONArray("records");

                            Log.d(TAG, "array enfants retrieved");

                            for (int i = 0; i < enfants.length(); i++) {
                                int an = Integer.parseInt(enfants.getJSONObject(i).getJSONObject("fields").getString("annee_naissance"));
                                String p = enfants.getJSONObject(i).getJSONObject("fields").getString("prenom");
                                String s = enfants.getJSONObject(i).getJSONObject("fields").getString("sexe");
                                listeEnfants.add(new Enfant(p, s, an, true));
                            }

                            Log.d(TAG, "liste enfants created");

                            List<String> enfantsPrenom = new ArrayList<String>();

                            for (Enfant e : listeEnfants)
                                enfantsPrenom.add(e.getPrenom());

                            Log.d(TAG, "liste enfantPrenom created");

                            ListView lv = (ListView) findViewById(R.id.listEnfant);

                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                    getApplicationContext(),
                                    android.R.layout.simple_list_item_1,
                                    enfantsPrenom
                            );

                            Log.d(TAG, "array adapter created");
                            setContentView(R.layout.activity_liste);

                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String TAG = Liste.class.getSimpleName();
                        Log.e(TAG, "Error retrieving kids informations");
                    }
                });
    }
}
