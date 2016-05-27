package com.creation.anindya.universityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by w3-15 on 1/26/16.
 */
public class DeleteItem extends AppCompatActivity {

    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_content);

        mydb = new DBHelper(this);

        Button dd = (Button)findViewById(R.id.btDeleteItem);
        final EditText etId= (EditText)findViewById(R.id.etRoll);
        dd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer roll = Integer.parseInt(etId.getText().toString());


                if (mydb.deleteItem(roll)) {
                    Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(DeleteItem.this,MainActivity.class);
                    startActivity(i);


                } else {
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
