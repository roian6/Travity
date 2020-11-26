package com.david0926.travity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.david0926.travity.R;

public class DialogFinishRegister extends Dialog {

    Button btn_cancel;

    public DialogFinishRegister(@NonNull Context context, View.OnClickListener cancelistener) {
        super(context);
        setContentView(R.layout.dialog_register_finish);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = layoutParams.MATCH_PARENT;
        layoutParams.height = layoutParams.WRAP_CONTENT;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.5f;
        getWindow().setAttributes(layoutParams);

        btn_cancel = findViewById(R.id.button3);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelistener.onClick(view);
                dismiss();
            }
        });
    }

}