package com.leeeeo.mydict;

import android.app.Application;
import android.content.Context;

public class InternetStatus extends Application {
    private int status;
    public InternetStatus(int nstatus) {
        status=nstatus;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
