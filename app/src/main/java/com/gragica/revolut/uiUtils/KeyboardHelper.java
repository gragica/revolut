package com.gragica.revolut.uiUtils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class KeyboardHelper {

    public static void openKeyboard(EditText view, Activity a){
        view.requestFocus();
        if (!isKeyboardOpen(a)) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        }
    }

    static boolean isKeyboardOpen(Activity a){
        Rect visibleBounds = new Rect();
        a.findViewById(android.R.id.content).getRootView().getWindowVisibleDisplayFrame(visibleBounds);
        int heightDiff = a.findViewById(android.R.id.content).getRootView().getHeight() - visibleBounds.height();
        int marginOfError = Math.round( TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50, a.getResources().getDisplayMetrics()));
        return heightDiff > marginOfError;
    }

}
