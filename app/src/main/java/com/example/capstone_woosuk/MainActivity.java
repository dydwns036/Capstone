package com.example.capstone_woosuk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edit_Id = findViewById(R.id.editTextEmail);
        EditText edit_Pw = findViewById(R.id.editTextPassword);
        Button btn_Login = findViewById(R.id.buttonLogin);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_Id.getText().toString();
                String password = edit_Pw.getText().toString();

                // 여기에 로그인 기능을 구현합니다. 서버로 이메일과 비밀번호를 전송하여 인증을 수행합니다.

                // 예시: 간단하게 이메일과 비밀번호를 확인하여 로그인 성공/실패 여부를 판단합니다.
                if ("user@example.com".equals(email) && "password".equals(password)) {
                    // 로그인 성공
                    Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    // 다음 화면으로 이동하거나 필요한 작업을 수행합니다.
                } else {
                    // 로그인 실패
                    Toast.makeText(MainActivity.this, "이메일 또는 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onTextViewClicked(View view) {
        // 클릭 시 실행할 동작 정의
        TextView myTextView = findViewById(R.id.textView);
        myTextView.setText("Textview clicked!");
    }
}
