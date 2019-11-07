package com.example.logino;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private EditText editText2;
    private Button button;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        button = findViewById(R.id.login_btn);
        signup = findViewById(R.id.signup);

        /* Memasangkan OnClickListener ke loginButton */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText.getText().toString();
                String password = editText2.getText().toString();
                if (isInputEmpty(email) || isInputEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Input tidak boleh kosong.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isEmailValid(email)) {
                    Toast.makeText(MainActivity.this, "Email tidak valid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isPasswordValid(password)) {
                    Toast.makeText(MainActivity.this, "Password tidak boleh < 6", Toast.LENGTH_SHORT).show();
                    return;
                }
                validate(editText.getText().toString(), editText2.getText().toString());
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });

        /* Memasangkan OnLongClickListener ke loginButton */
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String email = editText.getText().toString();
                if (isInputEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Input tidak boleh kosong.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (!isEmailValid(email)) {
                    Toast.makeText(MainActivity.this, "Email tidak valid", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Toast.makeText(MainActivity.this, "Halo " + email + ", Anda masuk sebagai admin. ", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    // MARK: - Helper
    private Boolean isInputEmpty(String input) {
        if (TextUtils.isEmpty(input)) {
            return true;
        }
        return false;
    }

    private static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression,
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isPasswordValid(String password) {
        if (password.toCharArray().length < 6) {
            return false;
        }
        return true;
    }

    private void validate(String email, String pass) {
        if (isEmailValid(email) && isPasswordValid(pass)) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }
}