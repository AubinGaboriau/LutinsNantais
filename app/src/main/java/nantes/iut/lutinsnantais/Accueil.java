package nantes.iut.lutinsnantais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Button btnValider = findViewById(R.id.valider);
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accueil.this, Liste.class);
                EditText tPrenom = findViewById(R.id.txtPrenom);
                EditText tSexe = findViewById(R.id.txtSexe);
                EditText tNaissance = findViewById(R.id.txtNaissance);

                String prenom = tPrenom.getText().toString();
                String sexe = tSexe.getText().toString();
                String annee_naissance = tNaissance.getText().toString();

                i.putExtra("prenom", prenom);
                i.putExtra("sexe", sexe);
                i.putExtra("annee_naissance", annee_naissance);

                startActivity(i);
            }
        });
    }



}
