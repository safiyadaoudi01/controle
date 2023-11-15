package daoudi.safiya.controle.ui.employe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import daoudi.safiya.controle.adapter.EmployeAdapter;
import daoudi.safiya.controle.beans.Employe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import daoudi.safiya.controle.R;

public class EmployeList extends AppCompatActivity {
    private List<Employe> employes = new ArrayList<>();
    private ListView employesList;
    RequestQueue requestQueue;
    EmployeAdapter employeAdapter ;
    private ImageButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_list);
        employeAdapter = new EmployeAdapter(employes, this);
        getStuents();
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeList.this, AddEmploye.class);
                startActivity(intent);
                EmployeList.this.finish();
            }
        });


    }

    public void getStuents(){
        String getSUrl = "http://172.20.10.5:8084/api/v1/employes";
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                getSUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
//                Log.d("professeurs",response.toString());
                TypeToken<List<Employe>> token = new TypeToken<List<Employe>>() {};
                employes = gson.fromJson(response.toString(), token.getType());
                employesList = findViewById(R.id.employesList);
                Log.d("student", employes.toString());
                employeAdapter.updateEmployesList(employes);
                employesList.setAdapter(employeAdapter);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Erreur", error.toString());
            }
        });
        requestQueue.add(request);
    }

}