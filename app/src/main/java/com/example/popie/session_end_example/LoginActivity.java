package com.example.popie.session_end_example;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;
    EditText etName, etPass;
    Button btnLogin;
    String name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName = findViewById(R.id.etName);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences("MyPreferneces", MODE_PRIVATE);


        name = sharedPreferences.getString("name", "");
        password = sharedPreferences.getString("password", "");


        if (name!="" && password!="") {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().equals("") && etPass.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Empty Fields", Toast.LENGTH_SHORT).show();
                } else if (etName.getText().toString().equals(etPass.getText().toString())) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", etName.getText().toString());
                    editor.putString("password", etPass.getText().toString());
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
