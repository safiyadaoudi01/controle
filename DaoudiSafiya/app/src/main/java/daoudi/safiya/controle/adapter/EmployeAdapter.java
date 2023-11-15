package daoudi.safiya.controle.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import daoudi.safiya.controle.R;
import daoudi.safiya.controle.beans.Employe;

public class EmployeAdapter extends BaseAdapter {
    private List<Employe> employes;
    private LayoutInflater inflater;

    public EmployeAdapter(List<Employe> employes, Activity activity) {
        this.employes = employes;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void updateEmployesList(List<Employe> newEmployes) {
        employes.clear();
        employes.addAll(newEmployes);
        notifyDataSetChanged(); // Informez l'adaptateur du changement de données
    }


    @Override
    public int getCount() {
        return employes.size();
    }

    @Override
    public Object getItem(int position) {
        return employes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return employes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.employe_item, null);
        TextView id = convertView.findViewById(R.id.id);
        TextView firstName = convertView.findViewById(R.id.firstName);
        TextView lastName = convertView.findViewById(R.id.lastName);
        TextView  date= convertView.findViewById(R.id.date);

        firstName.setText(employes.get(position).getNom());
        lastName.setText(employes.get(position).getPrenom());
        date.setText(employes.get(position).getDateNaissance()+"");

        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //String formattedDate = sdf.format(professeurs.get(position).getDateEmbauche());
        //date.setText(formattedDate);

        id.setText(employes.get(position).getId()+"");
        return convertView;
    }
}
