package com.tokbox.android.onetoonesample.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.tokbox.android.onetoonesample.R;

public class PreviewCameraFragment extends Fragment {
    private static final String LOGTAG = PreviewCameraFragment.class.getSimpleName();

    private View mRootView;
    private ImageButton mCameraBtn;

    private PreviewCameraCallbacks mCameraCallbacks = cameraCallbacks;

    public interface PreviewCameraCallbacks {
        void onCameraSwap();
    }

    private static PreviewCameraCallbacks cameraCallbacks = new PreviewCameraCallbacks() {
        @Override
        public void onCameraSwap() {}

    };

    private View.OnClickListener mBtnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            cameraSwap();
        }
    };

    @Override
    public void onAttach(Context context) {
        Log.i(LOGTAG, "OnAttach PreviewCameraFragment");

        super.onAttach(context);
        this.mCameraCallbacks = (PreviewCameraCallbacks) context;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            this.mCameraCallbacks = (PreviewCameraCallbacks) activity;
        }
    }

    @Override
    public void onDetach() {
        Log.i(LOGTAG, "OnDetach PreviewCameraFragment");

        super.onDetach();

        mCameraCallbacks = cameraCallbacks;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(LOGTAG, "onCreate PreviewCameraFragment");

        mRootView = inflater.inflate(R.layout.preview_camera_fragment, container, false);
        mCameraBtn = (ImageButton) mRootView.findViewById(R.id.camera);
        mCameraBtn.setOnClickListener(mBtnClickListener);

        return mRootView;
    }

    public void cameraSwap() {
        mCameraCallbacks.onCameraSwap();
    }

}
