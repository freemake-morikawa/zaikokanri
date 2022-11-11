package com.example.zaikokanri;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ShowTotalStockCountDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final int totalStockCount = getArguments().getInt(DialogConstants.KEY_COUNT, DialogConstants.ARG_COUNT_DEFAULT_VALUE);
        final String dialogFirstMessage = getResources().getString(R.string.dialog_first_message);
        final String dialogEndMessage = getResources().getString(R.string.dialog_end_message);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(dialogFirstMessage + totalStockCount + dialogEndMessage)
                .setPositiveButton(DialogConstants.OK_BUTTON_TEXT, null);

        return builder.create();
    }
}