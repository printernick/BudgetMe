package com.example.budgetme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class RemoveActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    private ArrayList<BudgetCategory> categories;
    private ArrayList<BudgetCategory> searchResults;

    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("wallets");

        categories = new ArrayList<BudgetCategory>();
        searchResults = new ArrayList<BudgetCategory>();

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                categories.add( dataSnapshot.getValue(BudgetCategory.class) );
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

        myRef.addChildEventListener( childEventListener );
        recyclerViewAdapter = new RecyclerViewAdapter(searchResults, this);
        
        RecyclerView results = findViewById( R.id.recyclerView );
        results.setAdapter( recyclerViewAdapter );

        results.onItemClickListener( recyclerViewAdapter );



    }

    public void refresh ( String update ){
        recyclerViewAdapter.clear();
        for (BudgetCategory c : categories) {
            if (c.getName().equalsIgnoreCase(update)) {
                recyclerViewAdapter.add(c);
            }
        }

    }

    public void removeCategory( View view ){
        recyclerViewAdapter.clear();
        boolean found = false;
        EditText text = (EditText) findViewById( R.id.recyclerView );

        String search = text.getText().toString();
        for ( BudgetCategory c : categories ) {
            if (c.getName().equalsIgnoreCase(search)) {
                recyclerViewAdapter.add(c);
                found = true;
            }
        }
        if (!found) {
            Toast.makeText(this, text.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
        }
        text.setText("");
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
