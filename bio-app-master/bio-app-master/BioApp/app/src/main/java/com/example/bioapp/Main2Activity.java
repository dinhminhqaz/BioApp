package com.example.bioapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    EditText edUserName;
    EditText edPass;
    Button blogin;
    Button bregister;
    FeedReaderDbHelper DbHelper;
    SQLiteDatabase db;

    private static final String SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE IF NOT EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME_USER + " (" +
                    FeedReaderContract.FeedEntry._ID_USER + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_1_USER + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_2_USER + " TEXT)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        edUserName = findViewById(R.id.EdUserName);
        edPass = findViewById(R.id.EdPass);

        blogin = findViewById(R.id.BLogin);
        bregister = findViewById(R.id.BRegister);


        // Login Onclick
        blogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SQLiteDatabase mydatabase = openOrCreateDatabase("Tienloi1.db",MODE_PRIVATE,null);
                mydatabase.execSQL(SQL_CREATE_ENTRIES_USER);

                DbHelper = new FeedReaderDbHelper(Main2Activity.this);
                String username = edUserName.getText().toString();
                String pass = edPass.getText().toString();
                try {

                Boolean existed = DbHelper.Login(username,pass);

                if (existed) {
                    Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(Main2Activity.this,"Welcome",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Main2Activity.this,"Need to Register",Toast.LENGTH_SHORT).show();
                }


                    existed = DbHelper.Login(username,pass);
                } catch (Exception e) {
                    Toast.makeText(Main2Activity.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Register
        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper = new FeedReaderDbHelper(Main2Activity.this);
                String username = edUserName.getText().toString();
                String pass = edPass.getText().toString();

                try {
                Boolean registered = DbHelper.Register(username,pass);

                if (registered) {
                    Toast.makeText(Main2Activity.this,"Registered",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Main2Activity.this,"Error!",Toast.LENGTH_SHORT).show();
                }

                } catch (Exception e) {
                    Toast.makeText(Main2Activity.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
