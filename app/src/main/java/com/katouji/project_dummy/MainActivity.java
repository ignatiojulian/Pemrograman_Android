package com.katouji.project_dummy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    public EditText emailTxt;
    private EditText passTxt;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailTxt = findViewById(R.id.emailTxt);
        passTxt = findViewById(R.id.passTxt);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailTxt.getText().toString().trim().matches(emailPattern)){
                    if (passTxt.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Password Masih Kosong !",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        openArctivityHome();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Email dan Password salah !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openArctivityHome(){//ganti ke halaman HomeActivity
        Bundle bundle = new Bundle();
        bundle.putString("dataUsername", emailTxt.getText().toString());
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
