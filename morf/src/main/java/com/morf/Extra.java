package com.morf;

import androidx.annotation.Keep;

@Keep
public class Extra {

    private boolean customListener = false;

    public Extra() {
    }

    public boolean getCustomListener() {
        return customListener;
    }

    public void setCustomListener(boolean customListener) {
        this.customListener = customListener;
    }
}