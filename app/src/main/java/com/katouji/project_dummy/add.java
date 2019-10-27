package com.katouji.project_dummy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class add extends AppCompatActivity {
    DatabaseHelper db;
    EditText editText_Name, editText_NIM, editText_id;
    Button button_add;
    Button button_view;
    Button btnDelete;
    Button btnviewUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);


        editText_Name = (EditText) findViewById(R.id.editText_Name);
        editText_NIM = (EditText) findViewById(R.id.editText_NIM);
        editText_id = (EditText) findViewById(R.id.editText_id);


        button_add = (Button) findViewById(R.id.button_add);
        button_view = (Button) findViewById(R.id.button_view);
        btnviewUpdate = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }



    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = db.deleteData(editText_id.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(add.this, "Data dihapus", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(add.this, "Data belum dihapus", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData() {
        button_add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = db.insertData(editText_Name.getText().toString(),
                                editText_NIM.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(add.this, "Data masuk", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(add.this, "Data belum masuk", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        button_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = db.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Tidak ada error");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {

                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("NIM :" + res.getString(2) + "\n");

                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = db.updateData(editText_id.getText().toString(),
                                editText_Name.getText().toString(),
                                editText_NIM.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(add.this, "Data Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(add.this, "Data belum Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }




}

