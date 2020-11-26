package com.david0926.travity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.david0926.travity.R;

public class DialogDelete extends Dialog {

    Button btn_delete, btn_cancel;

    public DialogDelete(@NonNull Context context, View.OnClickListener deletelistener, View.OnClickListener cancelistener) {
        super(context);

        setContentView(R.layout.dialog_checklist_delete);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = layoutParams.MATCH_PARENT;
        layoutParams.height = layoutParams.WRAP_CONTENT;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.5f;
        getWindow().setAttributes(layoutParams);

        btn_delete = findViewById(R.id.btn_dialog_checklist_delete);
        btn_cancel = findViewById(R.id.button3);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletelistener.onClick(view);
                dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelistener.onClick(view);
                dismiss();
            }
        });

    }

}
