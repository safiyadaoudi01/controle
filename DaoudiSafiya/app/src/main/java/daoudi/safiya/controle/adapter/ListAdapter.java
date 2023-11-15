package daoudi.safiya.controle.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import daoudi.safiya.controle.R;
import daoudi.safiya.controle.beans.Employe;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ProfesseurViewHolder> {
    private static final String TAG = "listAdapter";
    private List<Employe> employes;
    private Context context;



    public ListAdapter(Context context, List<Employe> employes) {
        this.employes = employes;
        this.context = context;

    }


    @NonNull
    @Override
    public ProfesseurViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.list_item,
                viewGroup, false);
        final ProfesseurViewHolder holder = new ProfesseurViewHolder(v);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ProfesseurViewHolder starViewHolder, int i) {

        Log.d(TAG, "onBindView call ! "+ i);
        starViewHolder.firstname.setText(employes.get(i).getPrenom());
        starViewHolder.lastname.setText(employes.get(i).getNom());
        starViewHolder.specialite.setText(employes.get(i).getservice().getnom());


    }
    @Override
    public int getItemCount() {
        return employes.size();
    }
    public class ProfesseurViewHolder extends RecyclerView.ViewHolder {
        TextView firstname;
        TextView lastname;
        TextView specialite;


        RelativeLayout parent;
        public ProfesseurViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.firstname);
            lastname = itemView.findViewById(R.id.lastname);
            specialite = itemView.findViewById(R.id.specialite);

        }
    }
}