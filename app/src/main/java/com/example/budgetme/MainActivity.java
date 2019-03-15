package com.example.budgetme;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddCategory(View view)
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
        Log.d("myTag", "message");

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

                        //Add to database here
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
