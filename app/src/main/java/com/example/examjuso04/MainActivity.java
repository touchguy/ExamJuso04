package com.example.examjuso04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    PermissionSupport permission;
    EditText editText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permission = new PermissionSupport(this, this);
        if(!permission.checkPermission()) {
            permission.requestPermission();
        }
    }


    public void sendMessage(View view) {
        editText = findViewById(R.id.ett_message);
        String message = editText.getText().toString();

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void readGroupName(View view) {
        Intent intent = new Intent(this, GroupNameActivity.class);
        startActivity(intent);
    }
}