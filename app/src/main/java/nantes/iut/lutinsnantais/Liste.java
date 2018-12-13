package nantes.iut.lutinsnantais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.*;

import com.android.volley.toolbox.*;

import org.json.JSONObject;

public class Liste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String sexe = getIntent().getStringExtra("sexe");
        String prenom = getIntent().getStringExtra("prenom");
        int annee_naissance_int = getIntent().getIntExtra("annee_naissance", 0);
        String annee_naissance = String.valueOf(annee_naissance_int);

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

        /*
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        mTextView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });
        */
        setContentView(R.layout.activity_liste);
    }
}
