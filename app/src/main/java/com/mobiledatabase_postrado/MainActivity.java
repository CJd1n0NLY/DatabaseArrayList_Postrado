package com.mobiledatabase_postrado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText Fname, Mname, Lname;

    public SQLiteDatabase Conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Fname = (EditText)findViewById(R.id.txtFname);
        Mname = (EditText)findViewById(R.id.txtMname);
        Lname = (EditText)findViewById(R.id.txtLname);
        Conn = new SQLiteDatabase(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void AddRecord(View view){
        if(Fname.getText().toString().isEmpty()){
            Fname.setError("Please Enter Your First Name");
            Fname.requestFocus();
            return;
        }

        if(Mname.getText().toString().isEmpty()){
            Mname.setError("Please Enter Your Middle Name");
            Mname.requestFocus();
            return;
        }

        if(Lname.getText().toString().isEmpty()){
            Lname.setError("Please Enter Your Last Name");
            Lname.requestFocus();
            return;
        }
        String fnameString = Fname.getText().toString();
        String mnameString =Mname.getText().toString();
        String lnameString =Lname.getText().toString();

        if(Conn.AddRecords(fnameString,mnameString,lnameString))
        {
            Fname.setText("");
            Mname.setText("");
            Lname.setText("");
            Toast.makeText(getApplicationContext(), "SAVING RECORDS SUCCESS!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "ERROR ON SAVING RECORDS", Toast.LENGTH_LONG).show();
        }
    }

    public void ShowRecord (View view){
        Intent CallRecords = new Intent(".RecordsActivity");
        startActivity(CallRecords);
    }

}