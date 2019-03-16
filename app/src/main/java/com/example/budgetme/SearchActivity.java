package com.example.budgetme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<BudgetCategory> categories;
    private ArrayList<BudgetCategory> searchList;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private RecyclerViewAdapter adapter;

    private ChildEventListener childEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        categories = new ArrayList<>();
        searchList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("wallets");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                categories.add(dataSnapshot.getValue(BudgetCategory.class));
                adapter = new RecyclerViewAdapter(searchList, SearchActivity.this);
                initRecyclerView();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        myRef.addChildEventListener(childEventListener);

    }

    public void onHome(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onSearch(View view)
    {
        searchList.clear();
        adapter = new RecyclerViewAdapter(searchList, SearchActivity.this);
        boolean found = false;
        EditText text = findViewById(R.id.categorySearch);
        String search = text.getText().toString();
        for( BudgetCategory c: categories)
        {
            if(c.getName().equalsIgnoreCase(search)) {
                // If the contact name is a match, add the result to the listAdapter for display
                searchList.add(c);
                found = true;
            }
        }
        if(!found) {
            Toast.makeText(this, text.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
        }
        initRecyclerView();
        text.setText("");

    }

    private void initRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.searchRecyclerView);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
