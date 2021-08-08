package com.example.bioapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.InputType;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText edPlantName ;
    EditText edPlantGood ;
    EditText edPlantBad ;
    EditText edDescription ;
    EditText edURL;
    SQLiteDatabase db;

    Button button1;
    Button button2;
    Button button3;

    ListView lv;
    Integer[] imgid = {R.drawable.bok, R.drawable.brocoli, R.drawable.cabbage, R.drawable.cauliflower, R.drawable.lettuce, R.drawable.spi, R.drawable.swiss};

    FeedReaderDbHelper DbHelper;

    TreeInfo SelectedTree;

    Intent myFileIntent;

    //private TreeAdapter adapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 123:
                if (resultCode == RESULT_OK) {
                    String path = data.getData().getPath();
                    edURL.setText(path);
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPlantName = findViewById(R.id.PlantName);
        edPlantGood = findViewById(R.id.Good);
        edPlantBad = findViewById(R.id.Bad);
        edDescription = findViewById(R.id.Description);
        edURL = findViewById(R.id.URL);
        lv = findViewById(R.id.ListView);

        button1 = findViewById(R.id.SearchButton);
        button2 = findViewById(R.id.InsertButton);
        button3 = findViewById(R.id.EditButton);

        edURL.setInputType(InputType.TYPE_NULL);



        // SearchButton
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHelper = new FeedReaderDbHelper(MainActivity.this);
                List<TreeInfo> alltree;
                try {
                    alltree = DbHelper.getAllTree(edPlantName.getText().toString());
                    int length = alltree.size();

                    TreeAdapter adapter = new TreeAdapter(MainActivity.this, android.R.layout.simple_list_item_1,alltree,imgid);

                    lv.setAdapter(adapter);
                    TreeInfo treeshow = alltree.get(length-1);
                    //Show
                    edPlantName.setText(treeshow.getName());
                    edDescription.setText(treeshow.getDescription());
                    edPlantGood.setText(treeshow.getGood());
                    edPlantBad.setText(treeshow.getBad());

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });

        // InsertButton
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TreeInfo treeInfo;
                DbHelper = new FeedReaderDbHelper(MainActivity.this);
                try {
                    treeInfo = new TreeInfo(0,-1,edPlantName.getText().toString(),edDescription.getText().toString(),edPlantGood.getText().toString(),edPlantBad.getText().toString());
                    Toast.makeText(MainActivity.this,"PlantName:"+edPlantName.getText().toString(),Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(MainActivity.this,"Error Creating Tree", Toast.LENGTH_SHORT).show();
                    treeInfo = new TreeInfo(0,-1,"error","error","error","error");
                }



                boolean b = DbHelper.addOne(treeInfo);

                Toast.makeText(MainActivity.this,"Success "+b,Toast.LENGTH_SHORT).show();


            }
        });

        // EditButton
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DbHelper = new FeedReaderDbHelper(MainActivity.this);

                String PlantName = edPlantName.getText().toString();
                String Description = edDescription.getText().toString();
                String Good = edPlantGood.getText().toString();
                String Bad = edPlantBad.getText().toString();

                TreeInfo treeInfo = new TreeInfo(0,SelectedTree.getId(),PlantName,Description,Good,Bad);

                DbHelper = new FeedReaderDbHelper(MainActivity.this);
                try {
                    DbHelper.editTree(treeInfo);
                    //edPlantName.setText(sql);
                    Toast.makeText(MainActivity.this,"success", Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    Toast.makeText(MainActivity.this,e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Listview on Click

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                TreeInfo treeInfo = (TreeInfo) parent.getItemAtPosition(position);

                SelectedTree = new TreeInfo(0,treeInfo.getId(),treeInfo.getName(),treeInfo.getDescription(),treeInfo.getGood(),treeInfo.getBad());
                Toast.makeText(MainActivity.this,"Index Selected:"+Integer.toString(SelectedTree.getId()), Toast.LENGTH_SHORT).show();

                // Display the selected item text on TextView
                edPlantName.setText(treeInfo.getName());
                edDescription.setText(treeInfo.getDescription());
                edPlantGood.setText(treeInfo.getGood());
                edPlantBad.setText(treeInfo.getBad());

            }
        });
        // EdURL onclick
       edURL.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
               myFileIntent.setType("*/*");
               startActivityForResult(myFileIntent,123);
           }
       });
        };
    }

