package com.iamasoldier6.dialogfragmentdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

/**
 * @author: Iamasoldier6
 * @date: 2017/09/16
 */
public class LoginDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog_login, null);
        builder.setView(view)
                .setPositiveButton("Sign in"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setNegativeButton("Cancel", null);
        return builder.create();
    }

}
