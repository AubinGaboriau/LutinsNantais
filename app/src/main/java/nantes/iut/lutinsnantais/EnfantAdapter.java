package nantes.iut.lutinsnantais;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import android.R.layout.*;

import java.util.ArrayList;

import bean.Enfant;

public class EnfantAdapter extends ArrayAdapter<Enfant> {

    private ArrayList<Enfant> listeEnfants;
    Context adapterContext;

    public EnfantAdapter(ArrayList<Enfant> enfants, Context c) {
        super(c, R.layout.enfant_item, enfants);
        this.listeEnfants = enfants;
        this.adapterContext = c;
    }

    public View getView(int position, View itemView, ViewGroup parent) {
        String TAG = Liste.class.getSimpleName();


        Enfant enfant = getItem(position);
        Log.d(TAG, "enfant: " + enfant.getPrenom());
        Log.d(TAG, "enfant: " + enfant.getSexe());
        Log.d(TAG, "enfant: " + enfant.getNaissance());

        final View result;

        if (itemView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            itemView = inflater.inflate(R.layout.enfant_item, parent, false);

            result = itemView;
        } else {
            result = itemView;
        }

        TextView enfantNom = (TextView) itemView.findViewById(R.id.enfantPrenom);
        TextView enfantDateNaissance = (TextView) itemView.findViewById(R.id.enfantAnneeNaissance);
        TextView enfantSexe = (TextView) itemView.findViewById(R.id.enfantSexe);
        TextView enfantSage = (TextView) itemView.findViewById(R.id.enfantSage);


        enfantNom.setText(enfant.getPrenom());
        String sage = enfant.isSage() ? "Sage" : "Pas sage";
        enfantSage.setText(sage);
        Log.d(TAG, "sage: " + enfant.isSage());
        Log.d(TAG, "sage: " + sage);

        enfantDateNaissance.setText(enfant.getNaissance());
        enfantSexe.setText(enfant.getSexe());
        itemView.setTag(position);


        return itemView;
    }

    public int getCount() {
        return this.listeEnfants.size();
    }
}
