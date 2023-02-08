package com.example.examjuso04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GroupUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_user);

        Intent intent = getIntent();
        if(intent == null) {
            Toast.makeText(this, "읽어올 주소록이 없습니다", Toast.LENGTH_SHORT).show();
            finish();
        }

        ArrayList<GroupUserInfo> mData = new ArrayList<>();
//        mData.add(new GroupUserInfo("name", "01012341234"));

        int mUserCount = 0;

        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null );

        int id_index = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int name_index = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

        String uname = null;
        String num = null;
        String tmpuname = null;
        String id = null;

        Cursor cursor2 = null;
        int typeidx = 0;
        int numidx = 0;

        while(cursor.moveToNext()) {
            tmpuname = cursor.getString(name_index);
            tmpuname.replaceAll(" ", "");
            tmpuname.replaceAll("\n", "");
            tmpuname.replaceAll("\r", "");
            tmpuname.replaceAll("\t", "");
//            tmpuname.replaceAll("\\", "");
            tmpuname.replaceAll("<", "");
            tmpuname.replaceAll(">", "");
            tmpuname.replaceAll("'", "");
            tmpuname.replaceAll("\"", "");
            tmpuname.replaceAll("/", "");

//            String uname = lengthLimit(tmpuname, 18, null);
            uname = tmpuname;
            id = cursor.getString(id_index);
            cursor2 = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] {id}, null);

            typeidx = cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
            numidx = cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            while(cursor2.moveToNext()) {
                num = cursor2.getString(numidx);
                mUserCount++;
            }
            mData.add(new GroupUserInfo(id, uname, num));
            cursor2.close();
        }
        cursor.close();

        ListView listView = findViewById(R.id.list_view_groupuser);
        GroupUserAdapter adapter = new GroupUserAdapter(mData);

        listView.setAdapter(adapter);

    }
}