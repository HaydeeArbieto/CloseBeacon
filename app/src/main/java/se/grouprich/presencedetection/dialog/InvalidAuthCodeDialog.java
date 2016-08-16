/*
package se.grouprich.presencedetection.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import se.grouprich.presencedetection.R;

public class InvalidAuthCodeDialog extends Dialog implements android.view.View.OnClickListener {

    public InvalidAuthCodeDialog(Context context) {

        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);

        TextView textView = (TextView) findViewById(R.id.text_dialog);
        textView.setText(R.string.dialog_invalid_auth_code);

        Button ok = (Button) findViewById(R.id.button_ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        dismiss();
    }
}
*/
