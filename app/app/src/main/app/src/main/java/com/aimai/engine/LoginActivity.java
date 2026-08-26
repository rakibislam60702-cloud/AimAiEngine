package com.aimai.engine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String KEY = "RTJ-Rakib-48";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        if (pref.getBoolean("active", false)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundColor(Color.parseColor("#0A0A0E"));
        layout.setPadding(60, 40, 60, 40);

        TextView title = new TextView(this);
        title.setText("⚡ AIM ENGINE VIP");
        title.setTextSize(22);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        EditText input = new EditText(this);
        input.setHint("License Key");
        input.setHintTextColor(Color.GRAY);
        input.setTextColor(Color.WHITE);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(input);

        Button btn = new Button(this);
        btn.setText("LOGIN");
        btn.setBackgroundColor(Color.parseColor("#8A2BE2"));
        btn.setTextColor(Color.WHITE);
        btn.setOnClickListener(v -> {
            if (input.getText().toString().trim().equals(KEY)) {
                pref.edit().putBoolean("active", true).apply();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Wrong Key!", Toast.LENGTH_SHORT).show();
            }
        });
        layout.addView(btn);
        setContentView(layout);
    }
          }
          
