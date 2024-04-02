package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView signUpTextView,findPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        signUpTextView = findViewById(R.id.signUpTextView);
        findPassword = findViewById(R.id.findPasswordText);

        //로그인되어 있으면 메인 화면으로 이동합니다
        if (isUserLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        //chuyển sang màn hình  sign up khi nhấn text sign up ở cuối màn hình
        //"화면 하단의 'Sign up' 텍스트를 누르면 '가입' 화면으로 전환됩니다."
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        //chuyển đến màn hình tìm mật khẩu khi nhấn "forgot your password"
        // 'forgot your password?'를 클릭하면 비밀번호 찾기 화면으로 이동합니다
        findPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindPassword.class);
                startActivity(intent);
            }
        });
    }

    // phương thức này kiểm tra xem người dùng đã đăng nhập hay chưa
    // 사용자가 이미 로그인했는지 여부를 확인하는 가상 메소드입니다.
    private boolean isUserLoggedIn() {
        // Code kiểm tra người dùng đã đăng nhập ở đây
        // Trả về true nếu đã đăng nhập, ngược lại trả về false
        // 사용자가 이미 로그인했는지 여부를 확인하는 코드를 여기에 작성합니다.
        // 이미 로그인한 경우 true를 반환하고, 그렇지 않으면 false를 반환합니다.
        return false;
    }

    // Phương thức khi người dùng nhấn nút "Đăng nhập"
    // 사용자가 "로그인" 버튼을 클릭했을 때 호출되는 메소드입니다.
    public void onLoginButtonClick(View view) {
        // Xử lý logic đăng nhập ở đây
        // 여기서 로그인 로직을 처리합니다.


        // 로그인에 성공한 후, MainActivity로 이동합니다.
        // Sau khi đăng nhập thành công, chuyển hướng sang MainActivity
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish(); // Đóng activity hiện tại để ngăn không cho người dùng quay lại màn hình đăng nhập bằng nút back
    }

    // 사용자가 "가입 img facebook ,twitter, google " 버튼을 클릭했을 때 호출되는 메소드입니다.
    public void onSignUpButtonClick(View view) {
        // Tạo Intent để chuyển sang hoạt động đăng ký

    }

}