package com.aimai.engine;

import android.app.Service;
import android.content.Intent;
import android.graphics.*;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

public class AimOverlayService extends Service {
    private WindowManager wm;
    private View overlayView;

    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public void onCreate() {
        super.onCreate();
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        int flag = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) 
                ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY 
                : WindowManager.LayoutParams.TYPE_PHONE;

        overlayView = new View(this) {
            final Paint p1 = new Paint() {{ setColor(Color.parseColor("#00E676")); setStrokeWidth(6f); }};
            final Paint p2 = new Paint() {{ setColor(Color.parseColor("#8A2BE2")); setStrokeWidth(4f); setStyle(Style.STROKE); }};
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                canvas.drawLine(540, 1750, 300, 850, p1);
                canvas.drawCircle(300, 850, 35, p2);
                canvas.drawLine(300, 850, 120, 400, p1);
            }
        };

        WindowManager.LayoutParams p = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,
                flag, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        wm.addView(overlayView, p);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlayView != null) wm.removeView(overlayView);
    }
    }
