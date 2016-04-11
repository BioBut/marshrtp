package com.github.anhadmathur.marshrtp;

import android.support.annotation.NonNull;

/**
 * Created by AnhadM on 9/2/16.
 */
public class PermissionResult {
    private String mPermission;
    private boolean mGranted;

    protected PermissionResult(@NonNull String permission, int granted) {
        this.mPermission = permission;
        this.mGranted = granted == 0;
    }

    @NonNull
    public String getPermission() {
        return this.mPermission;
    }

    public boolean isGranted() {
        return this.mGranted;
    }

    public String toString() {
        return String.format("%s (%s)", new Object[]{this.mPermission, this.mGranted?"Granted":"Denied"});
    }
}
