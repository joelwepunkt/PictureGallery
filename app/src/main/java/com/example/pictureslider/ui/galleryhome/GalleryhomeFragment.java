package com.example.pictureslider.ui.galleryhome;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pictureslider.ui.GestureListener;

import com.example.pictureslider.R;

import java.io.File;

public class GalleryhomeFragment extends Fragment {

    private GalleryhomeViewModel galleryhomeViewModel;
    private GestureDetectorCompat mDetector;
    private View root;
    private ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryhomeViewModel =
                ViewModelProviders.of(this).get(GalleryhomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_galleryhome, container, false);
        imageView = root.findViewById(R.id.imageViewGalleryhome);

        addTouchListener();
        linkViewModel();

        return root;
    }
    private void addTouchListener() {
        mDetector = new GestureDetectorCompat(this.getActivity(), new GestureListener(galleryhomeViewModel));
        root.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    private void linkViewModel() {
        // Permission has already been granted
        galleryhomeViewModel = ViewModelProviders.of(this).get(GalleryhomeViewModel.class);
        galleryhomeViewModel.getImage().observe(this, new Observer<File>() {
            @Override
            public void onChanged(@Nullable File file) {
                if (file != null) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
    }

}