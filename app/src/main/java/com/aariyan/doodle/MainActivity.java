package com.aariyan.doodle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aariyan.doodle.Adapter.SubCategoryAdapter;
import com.aariyan.doodle.Model.SubCategoryModel;
import com.aariyan.doodle.Utility.Common;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //Volley member variable
    private RequestQueue requestQueue;

    //progressbar member variable:
    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    private List<SubCategoryModel> list = new ArrayList<>();

    public static SubCategoryAdapter adapter;

    private TextView saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate volley:
        requestQueue = Volley.newRequestQueue(this);

        //instantiate the UI variable:
        initUI();

        //parsing JSON from JSON API:
        parsingDataFromJsonAPI();
    }

    private void initUI() {
        //instantiate:
        recyclerView = findViewById(R.id.recyclerView);
        //setting the type of view by the layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //instantiating the progressbar:
        progressBar = findViewById(R.id.progressbar);

        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
            }
        });
    }

    //parsing JSON from JSON API:
    private void parsingDataFromJsonAPI() {
        String url = "https://www.test.api.liker.com/get_categories/";

        if (!Common.isInternetConnected(MainActivity.this)) {
            Toast.makeText(this, "Please check your internet connection!", Toast.LENGTH_LONG).show();

            //setting progressbar invisible:
            progressBar.setVisibility(View.GONE);
        } else {
            JsonObjectRequest categories = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //clearing the list first before adding data:
                    list.clear();
                    //if everything is ok (Perfect response)
                    try {
                        //getting the parent array:
                        JSONArray categoriesArray = response.getJSONArray("categories");
                        //running a loop for traversing all the JSON Object:
                        for (int i = 0; i < categoriesArray.length(); i++) {
                            JSONObject particularObject = categoriesArray.getJSONObject(i);
                            JSONArray subCategory = particularObject.getJSONArray("subcatg");

                            if (subCategory.length() > 0) {
                                for (int j = 0; j < subCategory.length(); j++) {
                                    JSONObject object = subCategory.getJSONObject(j);

                                    //populating data
                                    SubCategoryModel model = new SubCategoryModel(
                                            object.getString("sub_category_name")
                                    );
                                    list.add(model);
                                }
                            }
                        }
                        //calling the adapter to show data on recyclerview
                        adapter = new SubCategoryAdapter(MainActivity.this, list);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        //setting progressbar invisible:
                        progressBar.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //If something error is happen
                    Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

                    //setting progressbar invisible:
                    progressBar.setVisibility(View.GONE);
                }
            });

            requestQueue.add(categories);
        }
    }


}