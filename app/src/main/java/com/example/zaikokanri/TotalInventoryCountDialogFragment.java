package com.example.zaikokanri;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TotalInventoryCountDialogFragment extends DialogFragment {

    private static final int ARG_COUNT_DEFAULT_VALUE = 0;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        final int totalInventoryCount = getArguments().getInt(
                Constants.BUNDLE_KEY_COUNT,
                ARG_COUNT_DEFAULT_VALUE
        );
        final String dialogFirstMessage = getResources().getString(R.string.dialog_first_message);
        final String dialogEndMessage = getResources().getString(R.string.dialog_end_message);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(dialogFirstMessage + totalInventoryCount + dialogEndMessage)
                .setPositiveButton(R.string.dialog_ok_button_text, null);

        return builder.create();
    }
}