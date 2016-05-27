package com.creation.anindya.universityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView obj;
    DBHelper mydb;


    EditText etName;
    EditText etFatherName;
    EditText etPhone;
    EditText etRoll;


    TextInputLayout phoneLayout,nameLayout,fNameLayout,rollLayout;

    Button btView;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        nameLayout = (TextInputLayout)findViewById(R.id.nameLayout);
        nameLayout.setErrorEnabled(true);
        etName =(EditText)findViewById(R.id.etName);
        etName.setError("Required");

        fNameLayout = (TextInputLayout)findViewById(R.id.fNameLayout);
        fNameLayout.setErrorEnabled(true);
        etFatherName = (EditText)findViewById(R.id.etFatherName);
        etFatherName.setError("Required");


        phoneLayout = (TextInputLayout) findViewById(R.id.phoneLayout);
        phoneLayout.setErrorEnabled(true);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etPhone.setError("Required");

        rollLayout = (TextInputLayout)findViewById(R.id.rollLayout);
        rollLayout.setErrorEnabled(true);
        etRoll = (EditText)findViewById(R.id.etRoll);
        etRoll.setError("Required");


        btSubmit = (Button)findViewById(R.id.btSubmit);
        btView = (Button)findViewById(R.id.btView);


        mydb = new DBHelper(this);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name         = etName.getText().toString();
                String fatherName   = etFatherName.getText().toString();
                String phone        = etPhone.getText().toString();
                String roll         = etRoll.getText().toString();

                if((mydb.insertPersonalDetails(name,fatherName, phone, roll)) == true
                        )
                        {
                    Toast.makeText(getApplicationContext(), "Personal data successfully inserted", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(MainActivity.this,academic.class);
                            startActivity(i);
                }else {
                    Toast.makeText(getApplicationContext(), "data insertion is faild", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
