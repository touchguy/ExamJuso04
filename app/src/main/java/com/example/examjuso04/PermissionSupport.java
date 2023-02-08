package com.example.examjuso04;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionSupport {
    private Context context;
    private Activity activity;

    //  요청권한을 배열로 저장
    private String[] permissions = {
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.READ_PHONE_NUMBERS,
            Manifest.permission.READ_CONTACTS
    };

    private List permissionList;

    //  권한 요청을 할 때 발생하는 창에 대한 결과값을 받기 위해 저장해주는 int 형입니다.
    //  본인에 맞게 숫자를 지정하시면 됩니다.
    private final int MULTIPLE_PERMISSIONS = 1023;

    //  생성자에서 Activity와 Context를 파라미터로 받습니다.
    public PermissionSupport(Context _context, Activity _activity) {
        this.context = _context;
        this.activity = _activity;
    }

    //  허용 받아야할 권한이 남았는지 체크
    public boolean checkPermission() {
        int result;
        permissionList = new ArrayList<>();

        //  위에서 배열로 선언한 권한 중 허용되지 않은 권한이 있는지 체크
        for(String pm : permissions) {
            result = ContextCompat.checkSelfPermission(context, pm);
            if(result != PackageManager.PERMISSION_GRANTED)
                permissionList.add(pm);
        }

        if(!permissionList.isEmpty())
            return  false;

        return true;
    }

    //    권한 허용 요청
    public void requestPermission() {
        ActivityCompat.requestPermissions(activity, (String[]) permissionList.toArray(new String [permissionList.size()]), MULTIPLE_PERMISSIONS);
    }

    //  권한 요청에 대한 결과 처리
    public boolean permissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MULTIPLE_PERMISSIONS && (grantResults.length>0)) {
            for (int i = 0; i < grantResults.length; i++) {
                //  grantResults 가 0이면 사용자가 혀용한 것이고, -1이면 거부
                //  -1 이 하나라도 나오면 false 리턴.
                if(grantResults[i] == -1) {
                    return  false;
                }
            }
        }
        return  true;
    }
}
