package com.example.zaikokanri;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ShowTotalStockCountDialog extends DialogFragment {

    private static final String DIALOG_FIRST_MESSAGE = "合計";
    private static final String DIALOG_END_MESSAGE = "です。";
    private static final String DIALOG_OK_BUTTON_TEXT = "OK";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        int totalStockCount = getArguments().getInt("count", 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(DIALOG_FIRST_MESSAGE + totalStockCount + DIALOG_END_MESSAGE)
                .setPositiveButton(DIALOG_OK_BUTTON_TEXT, null);

        return builder.create();
    }
}