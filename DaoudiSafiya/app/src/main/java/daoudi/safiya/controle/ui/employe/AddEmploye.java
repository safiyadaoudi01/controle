package daoudi.safiya.controle.ui.employe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import daoudi.safiya.controle.R;
import daoudi.safiya.controle.beans.Employe;
import daoudi.safiya.controle.beans.Service;

public class AddEmploye extends AppCompatActivity {
    RequestQueue requestQueue;
    private List<Service> services = new ArrayList<>();
    private List<Employe> employes = new ArrayList<>();
    private Service selectedService;
    private Employe selectedChef;
    private EditText firstname, lastName, date;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employe);
        getservices();
        getemployes();
        firstname = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        date = findViewById(R.id.date);
        submit = findViewById(R.id.add);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitProfesseur();
            }
        });
    }



    private void getservices() {
        String getFUrl = "http://172.20.10.5:8084/api/v1/services";
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                getFUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                Log.d("services",response.toString());
                TypeToken<List<Service>> token = new TypeToken<List<Service>>() {};
                services = gson.fromJson(response.toString(), token.getType());

                // Créer un HashMap pour associer les noms des filières à leurs objets correspondants
                final HashMap<String, Service> serviceMap = new HashMap<>();
                for (Service service : services) {
                    serviceMap.put(service.getnom(), service);
                }


                List<String> nomservices = new ArrayList<>(serviceMap.keySet());


                ArrayAdapter<String> adapter = new ArrayAdapter<>(AddEmploye.this, android.R.layout.simple_spinner_item, nomservices);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner spinner = findViewById(R.id.spinner);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                        String selectedserviceNom = (String) parentView.getItemAtPosition(position);


                        selectedService = serviceMap.get(selectedserviceNom);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {

                    }
                });
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Erreur", error.toString());
            }
        });
        requestQueue.add(request);
    }

    private void getemployes() {
        String getFUrl = "http://172.20.10.5:8084/api/v1/employes";
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                getFUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                Log.d("employes",response.toString());
                TypeToken<List<Employe>> token = new TypeToken<List<Employe>>() {};
                employes = gson.fromJson(response.toString(), token.getType());

                // Créer un HashMap pour associer les noms des filières à leurs objets correspondants
                final HashMap<String, Employe> employeMap = new HashMap<>();
                for (Employe employe : employes) {
                    employeMap.put(employe.getNom(), employe);
                }


                List<String> nomemployes = new ArrayList<>(employeMap.keySet());


                ArrayAdapter<String> adapter = new ArrayAdapter<>(AddEmploye.this, android.R.layout.simple_spinner_item, nomemployes);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner spinner = findViewById(R.id.spinner1);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                        String selectedemployeNom = (String) parentView.getItemAtPosition(position);


                        selectedChef = employeMap.get(selectedemployeNom);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {

                    }
                });
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Erreur", error.toString());
            }
        });
        requestQueue.add(request);
    }


    public void submitProfesseur() {
        String insertUrl = "http://172.20.10.5:8084/api/v1/employes";
        JSONObject jsonBody = new JSONObject();
        try {

            jsonBody.put("id", "");
            jsonBody.put("nom", firstname.getText().toString());
            jsonBody.put("prenom", lastName.getText().toString());
            JSONObject serviceObject = new JSONObject();
            JSONObject chefObject = new JSONObject();

            // Ajoutez la date au format souhaité
            String dateString=date.getText().toString();
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date parsedDate= inputFormat.parse(dateString);
            //String currentDate = dateFormat.format(new Date());
            jsonBody.put("dateNaissance", parsedDate.getTime());

            serviceObject.put("id", selectedService.getId());
            serviceObject.put("nom", selectedService.getnom());
            jsonBody.put("service", serviceObject);

            //chefObject.put("id", selectedChef.getId());
            //chefObject.put("nom", selectedChef.getNom());
            //chefObject.put("dateNaissance", selectedChef.getDateNaissance());
            //chefObject.put("service", selectedChef.getservice());
            //chefObject.put("prenom", selectedChef.getPrenom());

            jsonBody.put("chef", selectedChef.getNom());

            Log.d("employe", jsonBody.toString());
            Toast.makeText(AddEmploye.this, "Employe Added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddEmploye.this, EmployeList.class);
            startActivity(intent);
            AddEmploye.this.finish();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                insertUrl, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("resultat", response+"");
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
