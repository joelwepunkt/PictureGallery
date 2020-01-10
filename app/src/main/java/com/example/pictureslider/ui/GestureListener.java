package com.example.pictureslider.ui;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

import androidx.lifecycle.ViewModel;

import com.example.pictureslider.ui.gallerycamera.GallerycameraViewModel;
import com.example.pictureslider.ui.galleryhome.GalleryhomeViewModel;
import com.example.pictureslider.ui.galleryscreenshot.GalleryscreenshotViewModel;

public class GestureListener extends SimpleOnGestureListener {

    private final int SWIPE_MIN_DISTANCE = 120;
    private ViewModel viewModel;

    public GestureListener(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {
                action(0);
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {
                action(1);
            }
        } catch (Exception e) {
            // nothing
        }
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    private void action(int direction) {
        if (viewModel instanceof GallerycameraViewModel) {
            GallerycameraViewModel vm = (GallerycameraViewModel) viewModel;
            vm.next(direction);
        } else if (viewModel instanceof GalleryscreenshotViewModel) {
            GalleryscreenshotViewModel vm = (GalleryscreenshotViewModel) viewModel;
            vm.next(direction);
        } else {
            GalleryhomeViewModel vm = (GalleryhomeViewModel) viewModel;
            vm.next(direction);
        }
    }
}
