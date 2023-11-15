package daoudi.safiya.controle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import daoudi.safiya.controle.adapter.ListAdapter;
import daoudi.safiya.controle.beans.Employe;
import daoudi.safiya.controle.beans.Service;

public class ListPS extends AppCompatActivity {

    private List<Employe> employes = new ArrayList<>();
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;

    private Spinner specialiteSpinner;
    private ListAdapter fAdapter;

    private Button btn;

    List<Service> serviceList = new ArrayList<>();

    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        requestQueue = Volley.newRequestQueue(this);

        fetchSpinnerOptionsFromAPI();

        btn = findViewById(R.id.search);

        specialiteSpinner = findViewById(R.id.spinner);

        recyclerView = findViewById(R.id.professeurslist);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=String.valueOf(service.getId());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.1.132:8083/api/v1/specialites/"+ id,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("rep", response);
                                Type type = new TypeToken<List<Employe>>() {
                                }.getType();
                                employes = new Gson().fromJson(response, type);
                                fAdapter = new ListAdapter(ListPS.this, employes);
                                recyclerView.setAdapter(fAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(ListPS.this));
                                fAdapter.notifyDataSetChanged();


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("err", error.toString());
                    }
                });

                requestQueue.add(stringRequest);

            }
        });

        specialiteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Service selectedService = serviceList.get(position);
                Log.d("FiliereSelected", "ID: " + selectedService.getId() + " Nom: " + selectedService.getnom());
                service = new Service(selectedService.getId(), selectedService.getnom());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void fetchSpinnerOptionsFromAPI() {
        String Url = "http://192.168.1.132:8083/api/v1/specialites";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("REPONSE", response);
                        Type type = new TypeToken<List<Service>>() {
                        }.getType();
                        serviceList = new Gson().fromJson(response, type);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(ListPS.this, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        for (Service service : serviceList) {
                            adapter.add(service.getnom());
                        }
                        specialiteSpinner.setAdapter(adapter);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("err", error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }
}