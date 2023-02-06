package com.example.examjuso04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        String message = null;
        Intent intent = getIntent();
        if(intent != null) {
            message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        }

        TextView textView = findViewById(R.id.tvw_message);
        textView.setText(message);
    }

    public void closeDisplayMessage(View view) {
        Toast.makeText(this, "메시지 화면을 닫습니다", Toast.LENGTH_SHORT).show();
        finish();
    }
}