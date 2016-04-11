package com.github.anhadmathur.marshrtp;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by AnhadM on 9/2/16.
 */
public class PermissionResultStatus {
    private final com.github.anhadmathur.marshrtp.PermissionResult[] mResults;
    private HashMap<String, Boolean> mResultHashMap;

    private PermissionResultStatus(com.github.anhadmathur.marshrtp.PermissionResult[] result){
        mResults = result;
    }

    public static PermissionResultStatus generateResultSet(@NonNull String[] permissions, int[] grantResults){
        com.github.anhadmathur.marshrtp.PermissionResult[] resultSet = new com.github.anhadmathur.marshrtp.PermissionResult[permissions.length];

        for (int i = 0; i < permissions.length; i++){
            resultSet[i] = new com.github.anhadmathur.marshrtp.PermissionResult(permissions[i],grantResults[i]);
        }
        PermissionResultStatus resultStatus = new PermissionResultStatus(resultSet);
        resultStatus.setPermissionResultSet(resultSet);

        return resultStatus;
    }

    public boolean allPermissionsGranted(){
        com.github.anhadmathur.marshrtp.PermissionResult[] tempResult = this.mResults;
        synchronized (this.mResults){
            boolean bool = true;
            for (int i=0; i < tempResult.length; i++){
                if (!tempResult[i].isGranted()){
                    bool = false;
                    break;
                }
            }
            return bool;
        }
    }

    public HashMap<String, Boolean> getPermissionResultSet(){
        if (mResultHashMap != null){
            return mResultHashMap;
        }
        return null;
    }

    public void setPermissionResultSet(com.github.anhadmathur.marshrtp.PermissionResult[] mResults){
        mResultHashMap = new HashMap<>();
        for (int i = 0; i < mResults.length; i++){
            com.github.anhadmathur.marshrtp.PermissionResult result = mResults[i];
            mResultHashMap.put(result.getPermission(), result.isGranted());
        }
    }

    public boolean isPermissionGranted(String permissionName) throws IllegalStateException{
        HashMap<String, Boolean> map = getPermissionResultSet();
        for (int i = 0; i < map.size(); i++){
            if (map.containsKey(permissionName)){
                return map.get(permissionName);
            }
            else {
                throw new IllegalStateException("You have not requested the "+permissionName+" Permission");
            }
        }
        return false;
    }
}
