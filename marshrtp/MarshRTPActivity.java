package com.github.anhadmathur.marshrtp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

/**
 * Created by AnhadM on 8/2/16.
 */
public class MarshRTPActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private com.github.anhadmathur.marshrtp.OnPermissionGranted callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                callback.onResponse(processGrantResult(permissions, grantResults));

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private com.github.anhadmathur.marshrtp.PermissionResultStatus processGrantResult(String[] permissions, int[] grantResults){
        return com.github.anhadmathur.marshrtp.PermissionResultStatus.generateResultSet(permissions, grantResults);
    }

    public static boolean isPermissionGranted(Context context, String permission){
        int hasWriteContactsPermission = context.checkSelfPermission(permission);
        if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            return false;
        }
    }

    public static HashMap<String, Boolean> isPermissionGranted(Context context, String[] permission){
        HashMap<String, Boolean> map = new HashMap<>();
        for (int i=0; i < permission.length; i++) {
            int hasWriteContactsPermission = context.checkSelfPermission(permission[i]);
            if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED) {
                map.put(permission[i], true);
            } else {
                map.put(permission[i], false);
            }
        }
        return map;
    }

    public void requestPermissions(com.github.anhadmathur.marshrtp.OnPermissionGranted callback, Context context, String permission){
        this.callback = callback;
        ((Activity)context).requestPermissions(new String[]{permission}, MarshRTPActivity.REQUEST_CODE_ASK_PERMISSIONS);
    }

    public void requestPermissions(OnPermissionGranted callback, Context context, String[] permission){
        this.callback = callback;
        ((Activity)context).requestPermissions(permission, MarshRTPActivity.REQUEST_CODE_ASK_PERMISSIONS);
    }

}
