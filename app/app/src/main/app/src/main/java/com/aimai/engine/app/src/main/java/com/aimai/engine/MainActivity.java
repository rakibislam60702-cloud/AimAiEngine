package com.aimai.engine;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setBackgroundColor(Color.parseColor("#0A0A0E"));

        TextView tv = new TextView(this);
        tv.setText("Engine Active (Supports FB, Google, Guest)");
        tv.setTextColor(Color.parseColor("#00E676"));
        root.addView(tv);

        Button btn = new Button(this);
        btn.setText("START OVERLAY & GAME");
        btn.setBackgroundColor(Color.parseColor("#8A2BE2"));
        btn.setTextColor(Color.WHITE);
        btn.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())));
                return;
            }
            startService(new Intent(this, AimOverlayService.class));
            PackageManager pm = getPackageManager();
            Intent intent = pm.getLaunchIntentForPackage("com.miniclip.carrom");
            if (intent != null) startActivity(intent);
            else Toast.makeText(this, "Carrom Pool ওপেন করুন", Toast.LENGTH_SHORT).show();
        });
        root.addView(btn);
        setContentView(root);
    }
}
