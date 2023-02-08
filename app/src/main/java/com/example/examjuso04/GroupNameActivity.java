package com.example.examjuso04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;

public class GroupNameActivity extends AppCompatActivity {
    public static final String TAG = "EXJUSO";
    TextView textView;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_name);

        getGroupName();

/*
        // 데이터 준비
        ArrayList <Weather> data =  new ArrayList<>();

        data.add(new Weather("수원", "25 도" , "맑음"));
        data.add(new Weather("서울", "26 도" , "흐림"));
        data.add(new Weather("광주", "27 도" , "우박"));
        data.add(new Weather("인천", "28 도" , "천둥"));
        data.add(new Weather("경주", "30 도" , "소나기"));
        data.add(new Weather("부산", "17 도" , "눈"));
        data.add(new Weather("부산", "17 도" , "눈"));
        data.add(new Weather("부산", "17 도" , "눈"));
        data.add(new Weather("부산", "17 도" , "눈"));

        MyFirstAdapter adapter = new MyFirstAdapter(data);

        ListView listView = findViewById(R.id.list_view);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GroupNameActivity.this, "POS : " + position, Toast.LENGTH_SHORT).show();
            }
        });

 */
    }

    public void getGroupName() {
        Cursor cr = null;
        Uri uri = ContactsContract.Groups.CONTENT_URI;

        String[] projection = new String[]{
                ContactsContract.Groups._ID,
                ContactsContract.Groups.TITLE,
                ContactsContract.Groups.ACCOUNT_NAME,
                ContactsContract.Groups.ACCOUNT_TYPE,
                ContactsContract.Groups.DELETED,
                ContactsContract.Groups.GROUP_VISIBLE
        };

        ArrayList <Weather> data =  new ArrayList<>();

        String str1, str2, str3;
        try {
            cr = getContentResolver().query(uri, projection, null, null, null );
            int mcount = cr.getCount();
            Toast.makeText(this, "COUNT : " + mcount, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "READ COUNT : " + mcount);
            while(cr.moveToNext()) {
                str1 = cr.getString(1);
                str2 = cr.getString(0);
                str3 = cr.getString(2);
                data.add(new Weather(str1, str2, str3));
                mcount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cr.close();

        MyFirstAdapter adapter = new MyFirstAdapter(data);

        ListView listView = findViewById(R.id.list_view);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strName = data.get(position).getCity().toString();
//                Toast.makeText(GroupNameActivity.this, "CLICKED : " + position + " " + strPos, Toast.LENGTH_SHORT).show();
                String strID = data.get(position).getTemp().toString();
                Toast.makeText(GroupNameActivity.this, "CLICKED : " + position + " " + strName + " " + strID, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(GroupNameActivity.this, GroupUserActivity.class);
                startActivity(intent);
            }
        });
    }
}