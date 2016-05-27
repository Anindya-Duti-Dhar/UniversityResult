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

public class academic extends AppCompatActivity {

    private ListView obj;
    DBHelper mydb;

    EditText etCourseMarks;
    EditText etCourseTerms;
    EditText etCourseName;
    EditText etCourseRating;
    EditText etRoll;
    EditText etDepartment;

    TextInputLayout cTermsLayout,cMarksLayout,cNameLayout,cRatingLayout,rollLayout,deptLayout;

    Button btView;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic);

        cNameLayout = (TextInputLayout)findViewById(R.id.cNameLayout);
        cNameLayout.setErrorEnabled(true);
        etCourseName = (EditText)findViewById(R.id.etCourseName);
        etCourseName.setError("Required");


        cRatingLayout = (TextInputLayout)findViewById(R.id.cRatingLayout);
        cRatingLayout.setErrorEnabled(true);
        etCourseRating = (EditText)findViewById(R.id.etCourseRating);
        etCourseRating.setError("Required");


        cTermsLayout = (TextInputLayout)findViewById(R.id.cTermsLayout);
        cTermsLayout.setErrorEnabled(true);
        etCourseTerms = (EditText)findViewById(R.id.etCourseTerms);
        etCourseTerms.setError("Required");


        cMarksLayout = (TextInputLayout)findViewById(R.id.cMarksLayout);
        cMarksLayout.setErrorEnabled(true);
        etCourseMarks = (EditText)findViewById(R.id.etCourseMarks);
        etCourseMarks.setError("Required");


        rollLayout = (TextInputLayout)findViewById(R.id.rollLayout);
        rollLayout.setErrorEnabled(true);
        etRoll = (EditText)findViewById(R.id.etRoll);
        etRoll.setError("Required");


        deptLayout = (TextInputLayout)findViewById(R.id.deptLayout);
        deptLayout.setErrorEnabled(true);
        etDepartment = (EditText)findViewById(R.id.etDept);
        etDepartment.setError("Required");


        btSubmit = (Button)findViewById(R.id.btSubmit);
        btView = (Button)findViewById(R.id.btView);


        mydb = new DBHelper(this);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseTerms   = etCourseTerms.getText().toString();
                String courseMarks = etCourseMarks.getText().toString();
                String courseName   = etCourseName.getText().toString();
                String courseRating = etCourseRating.getText().toString();
                String roll         = etRoll.getText().toString();
                String department   = etDepartment.getText().toString();

                if((mydb.insertCourse(courseName,courseRating,courseTerms,courseMarks,roll)) && (mydb.insertAcademic(roll, department)) == true
                        )
                {
                    Toast.makeText(getApplicationContext(), "data successfully inserted", Toast.LENGTH_SHORT).show();

                    etCourseMarks.setText("");
                    etCourseTerms.setText("");
                    etCourseName.setText("");
                    etCourseRating.setText("");
                    etRoll.setText("");
                    etDepartment.setText("");


                }else {
                    Toast.makeText(getApplicationContext(), "data insertion is faild", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(academic.this,DisplayResult.class);
                startActivity(i);
            }
        });

        Button dd = (Button)findViewById(R.id.btDelete);
        dd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(academic.this,DeleteItem.class);
                startActivity(i);


//                if (mydb.deleteAll(roll)) {
//                    Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
//
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
//
//                }
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
