package com.example.logino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistActivity extends AppCompatActivity {
    private ImageView backbtn;
    private Button signup;
    private EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_regist);

        backbtn = findViewById(R.id.backbtn);
        signup = findViewById(R.id.SignupBtn);
        inputName = findViewById(R.id.inputName);

        Spinner spinnerRegist = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegistActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegist.setAdapter(myAdapter);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valid(inputName.getText().toString());
            }
        });
    }
    private Boolean isInputEmpty(String input) {
        if (TextUtils.isEmpty(input)) {
            return true;
        }
        return false;
    }
    private void valid(String name) {
        if (isInputEmpty(name)) {
            Toast.makeText(this, "Required to Enter Your Name", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(RegistActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(RegistActivity.this, "Sign Up Success.", Toast.LENGTH_SHORT).show();
        }
    }
}
