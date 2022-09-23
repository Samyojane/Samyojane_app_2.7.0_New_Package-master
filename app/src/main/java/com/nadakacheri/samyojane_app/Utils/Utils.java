package com.nadakacheri.samyojane_app.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.nadakacheri.samyojane_app.R;

public class Utils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void buildAlertMessageConnection(Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.no_internet))
                .setMessage(context.getString(R.string.enable_internet))
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.yes), (dialog, id) -> {
                    Intent dialogIntent = new Intent(Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(dialogIntent);
                })
                .setNegativeButton(context.getString(R.string.no), (dialog, id) -> {
                    Toast.makeText(context, context.getString(R.string.internet_not_avail),Toast.LENGTH_LONG).show();
                    dialog.cancel();
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

}
