package com.example.sample.sqlitetest2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyHelper helper = new MyHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        final EditText nameText = (EditText) findViewById(R.id.editName);
        final EditText ageText = (EditText) findViewById(R.id.editAge);

        Button entryButton = (Button) findViewById(R.id.insert);
        entryButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();

                ContentValues insertValues = new ContentValues();
                insertValues.put("name", name);
                insertValues.put("age", age);
                long id = db.insert("test", name, insertValues);
            }
        });

        Button detaBaseButton = (Button) findViewById(R.id.dataBase);
        	        detaBaseButton.setOnClickListener(new OnClickListener() {

                    	            @Override
            	            public void onClick(View v) {
                	                Intent dbIntent = new Intent(MainActivity.this,
                        	                        ShowDataBase.class);
                	                startActivity(dbIntent);

                	            }
            	        });
    }
}