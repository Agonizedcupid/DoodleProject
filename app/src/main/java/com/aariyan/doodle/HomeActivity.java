package com.aariyan.doodle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.aariyan.doodle.Adapter.HomeAdapter;

public class HomeActivity extends AppCompatActivity {

    //recyclerview for showing subcategory
    private RecyclerView recyclerView;

    //adapter for showing data
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //instantiate UI
        initUi();
    }

    private void initUi() {
        //setting recyclerview layout manager to horizontal
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //instantiating the recyclerview
        recyclerView = findViewById(R.id.homeRecyclerView);
        //setting the layout manager
        recyclerView.setLayoutManager(layoutManager);

        //checking if no items selected
        if (MainActivity.adapter.getSelected().size() <=0) {
            Toast.makeText(this, "No Items Selected", Toast.LENGTH_SHORT).show();
        } else {
            //if at least select one items
            homeAdapter = new HomeAdapter(HomeActivity.this,MainActivity.adapter.getSelected());
            //setting adapter to recyclerview
            recyclerView.setAdapter(homeAdapter);
            homeAdapter.notifyDataSetChanged();
        }


    }
}