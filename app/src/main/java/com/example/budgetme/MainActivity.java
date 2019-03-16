package com.example.budgetme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private double setBudget;
    private double myRemainingBudget;
    private boolean hasBudgetSet = false;

    private FirebaseDatabase database;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("wallets");


    }

    public void onSetWalletBudget(View view)
    {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        final EditText budget = new EditText(MainActivity.this);
        budget.setHint("800");
        budget.setLayoutParams(lp);
        budget.setInputType(InputType.TYPE_CLASS_NUMBER);

        alertBuilder.setView(budget);
        alertBuilder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //get new unique key
                Double newBudget = Double.parseDouble(budget.getText().toString());
                if (!hasBudgetSet)
                {
                    //key = myRef.push().getKey();
                    //Wallet wallet = new Wallet(Double.parseDouble(budget.getText().toString()));
                    //myRef.child(key).setValue(wallet);
                    hasBudgetSet = true;
                    myRemainingBudget = newBudget;
                }
                else
                {
                    double difference = setBudget - newBudget;
                    myRemainingBudget += (setBudget - difference);
                }

                setBudget = newBudget;

                Toast.makeText(MainActivity.this, setBudget + " budget set.", Toast.LENGTH_LONG).show();
                TextView initialBudget = findViewById(R.id.initialBudget);
                initialBudget.setText(Double.toString(setBudget));

                TextView remainingBudget = findViewById(R.id.remainingBudget);
                remainingBudget.setText(Double.toString(myRemainingBudget));

                dialog.cancel();
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = alertBuilder.create();
        alert.setTitle("Set maximum budget");
        alert.show();
    }

    public void onAddCategory(View view)
    {
        if (!hasBudgetSet)
        {
            Toast.makeText(MainActivity.this, "Set your budget first!", Toast.LENGTH_LONG).show();
        }
        else
        {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
            alertBuilder.setMessage("Food, rent, etc.")
                    .setCancelable(true);

            //Creates layout to add EditText to
            final LinearLayout layout = new LinearLayout(MainActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);

            final EditText categoryName = new EditText(MainActivity.this);
            categoryName.setHint("Food");
            categoryName.setLayoutParams(lp);
            layout.addView(categoryName);

            final EditText budget = new EditText(MainActivity.this);
            budget.setHint("150");
            budget.setInputType(InputType.TYPE_CLASS_NUMBER);
            budget.setLayoutParams(lp);
            layout.addView(budget);

            alertBuilder.setView(layout);

            alertBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String budgetCatName = (categoryName.getText().toString());
                    Double budgetAmount = Double.parseDouble(budget.getText().toString());

                        String key = myRef.push().getKey();
                    myRef.child(key).setValue(new BudgetCategory(budgetCatName, budgetAmount));

                    myRemainingBudget -= budgetAmount;

                    TextView remainingBudget = findViewById(R.id.remainingBudget);
                    remainingBudget.setText(Double.toString(myRemainingBudget));

                    dialog.cancel();
                }
            })
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = alertBuilder.create();
            alert.setTitle("Add budget category");
            alert.show();
        }

    }

    public void onGoSearch(View view)
    {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
