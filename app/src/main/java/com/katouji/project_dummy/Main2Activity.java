package com.katouji.project_dummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    EditText etnama;
    Button btnsubmit;
    Spinner spinnermatkul;

    DatabaseReference database;

    ListView listViewDosen;

    List<Data> dosenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        database = FirebaseDatabase.getInstance().getReference("dosen");

        etnama = (EditText) findViewById(R.id.nama);
        btnsubmit = (Button) findViewById(R.id.submit);
        spinnermatkul = (Spinner) findViewById(R.id.spinner2);


        listViewDosen = (ListView) findViewById(R.id.listViewDosen);

        dosenList = new ArrayList<>();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });



//        listViewDosen.setOnLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView,View view, int i, long l){
//
//                Data data = dosenList.get(i);
//                showUpdateDialog(data.getId(),data.getNama());
//                return false;
//            }
//        });
        listViewDosen.setOnItemLongClickListener (new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
                Data data = dosenList.get(position);
                showUpdateDialog(data.getId(),data.getNama());
                return false;
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dosenList.clear();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Data data = dataSnapshot1.getValue(Data.class);

                    dosenList.add(data);
                }

                DosenList adapter = new DosenList(Main2Activity.this,dosenList);
                listViewDosen.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    //update
    private void showUpdateDialog(final String id, String namaDosen){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog,null);

        dialogBuilder.setView(dialogView);
        final TextView textViewNama = (TextView) dialogView.findViewById(R.id.textViewNama);
        final EditText editTextNama = (EditText) dialogView.findViewById(R.id.editTextNama);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdate);
        final Spinner spinnerUpdate = (Spinner) findViewById(R.id.spinnerUpdate);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDelete);


        dialogBuilder.setTitle("Update Data "+namaDosen);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = editTextNama.getText().toString().trim();
                String matkul = spinnermatkul.getSelectedItem().toString();

                if(TextUtils.isEmpty(nama)) {
                    editTextNama.setError("Nama harus diisi");
                    return;
                }
                updateDosenList(id,nama,matkul);
                alertDialog.dismiss();

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                deleteData(id);
            }
        });


    }


    //delete
    private void deleteData(String id) {

        DatabaseReference deleteData = FirebaseDatabase.getInstance().getReference("dosen").child(id);
        deleteData.removeValue();

        Toast.makeText(this, "Data sudah dihapus", Toast.LENGTH_SHORT).show();

    }

    //update
    private boolean updateDosenList(String id, String nama, String matkul){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("dosen").child(id);

        Data data = new Data(id,nama,matkul);

        databaseReference.setValue(data);
        Toast.makeText(this, "Data berhasil diupdate", Toast.LENGTH_LONG).show();
        return true;
    }


    //add
    private void addData(){
        String nama = etnama.getText().toString().trim();
        String matakuliah = spinnermatkul.getSelectedItem().toString();


        if(!TextUtils.isEmpty(nama)) {

            String id = database.push().getKey();

            Data data = new Data(id,nama,matakuliah);
            database.child(id).setValue(data);
            Toast.makeText(this,"Nama dan Matakuliah Berhasil diInputkan", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Anda harus memasukan nama", Toast.LENGTH_LONG).show();
        }
    }
}
