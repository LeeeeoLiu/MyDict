package com.leeeeo.mydict.views;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.leeeeo.mydict.R;

/**
 * Created by Jacob on 16/5/17.
 * Email:Jacob.Deng@about-bob.com
 */
public class XNFileLongClickDialog extends Dialog {
    public TextView tvChatto;
    public View sendOther,deleteMsg;
    public XNFileLongClickDialog(Context context) {
        super(context, R.style.WinDialog);
        setContentView(R.layout.dialog_xnfilelongclick);
        //sendOther = findViewById(R.id.send_other);
        //deleteMsg = findViewById(R.id.delete_msg);
        tvChatto = (TextView) findViewById(R.id.chat_to);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            dismiss();
            return false;
        }
        return super.onTouchEvent(event);
    }


}
