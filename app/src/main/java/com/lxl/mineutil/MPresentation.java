package com.lxl.mineutil;

import android.app.Presentation;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Display;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class MPresentation extends Presentation {
    public MPresentation(Context outerContext, Display display) {
        super(outerContext, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
