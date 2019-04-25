package com.example.AndroidProject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class BookingConfirmedAlert extends DialogFragment {

    Helper theHelper;

    public interface Helper {
        void callmain();
        void callReached();
        void callstop();
    }
    public void onAttach(Activity act){
        super.onAttach(act);
        theHelper = (Helper)act;
    }

    public Dialog onCreateDialog(Bundle sis) {
        super.onCreateDialog(sis);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // Factory for an AlertDialog.
        builder.setTitle("Thank you")
                .setMessage("Your Booking is confirmed, You'r Car will arrive shortly")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override // is triggered when the button is clicked.
                    public void onClick(DialogInterface dialog, int which) {
                        theHelper.callReached();
                    }
                });

        return builder.create(); // Tell
    }
}
