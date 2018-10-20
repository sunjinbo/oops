package com.oops;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oops.adapter.EffectAdapter;
import com.oops.data.EffectCallback;
import com.oops.data.EffectType;
import com.oops.gles.LogUtils;
import com.oops.render.VideoRenderer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ControllerView class.
 */
public class ControllerView extends FrameLayout implements EffectCallback {

    private VideoRenderer mController;
    private Timer mTimer;

    private View mRootView;

    private TextView mTitleTextView;
    private TextView mCurrentPositionTextView;
    private TextView mDurationTextView;
    private ProgressBar mProgressBar;

    private RecyclerView mRecyclerView;
    private EffectAdapter mAdapter;

    private int mDisplayTime = 5000;

    public ControllerView(@NonNull Context context) {
        super(context);
        initView();
    }

    public ControllerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ControllerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDisplayTime = 5000;
        if (mRootView.getVisibility() == INVISIBLE) {
            mRootView.setVisibility(VISIBLE);
        }

        return false;
    }

    public void setRenderer(VideoRenderer renderer) {
        mController = renderer;
    }

    public void destroy() {
        mTimer.cancel();
    }

    private void initView() {
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.media_player, this, false);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        mRootView.setLayoutParams(params);
        addView(mRootView);

        final ImageView backImageView = mRootView.findViewById(R.id.iv_back);
        backImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });

        final ImageView controlImageView = mRootView.findViewById(R.id.iv_control);
        controlImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mController != null) {
                    if (mController.isPlaying()) {
                        controlImageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_play));
                        mController.pause();
                    } else {
                        controlImageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_pause));
                        mController.play();
                    }
                }
            }
        });

        mTitleTextView = mRootView.findViewById(R.id.tv_title);
        mCurrentPositionTextView = mRootView.findViewById(R.id.tv_position);
        mDurationTextView = mRootView.findViewById(R.id.tv_duration);
        mProgressBar = mRootView.findViewById(R.id.progressbar);

        mAdapter = new EffectAdapter(getContext(), this);

        mRecyclerView = mRootView.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg;
                msg = mHandler.obtainMessage();
                msg.what = 0;
                mHandler.sendMessage(msg);
            }
        }, 0, 50);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (null != mController) {
                if (TextUtils.isEmpty(mTitleTextView.getText())) {
                    mTitleTextView.setText(mController.getUrl());
                }

                int currentPosition = (int)((float)mController.getCurrentPosition() / 1000.0f);
                int duration = (int)((float)mController.getDuration() / 1000.0f);
                if (currentPosition >= 0 && duration >= 0l) {
                    int progress = (int) ((float) currentPosition / (float) duration * 100);
                    mProgressBar.setProgress(progress);
                    mCurrentPositionTextView.setText(TimeUtils.formatNumberToHourMinuteSecond((double)currentPosition));
                    mDurationTextView.setText(TimeUtils.formatNumberToHourMinuteSecond((double)duration));
                    LogUtils.v("actual progress is " + progress + "%");
                }
            }

            mDisplayTime -= 50;
            if (mDisplayTime <= 0 && mRootView.getVisibility() == VISIBLE) {
                mRootView.setVisibility(INVISIBLE);
            }
        }
    };

    @Override
    public void onEffectChanged(EffectType effectType) {
        if (effectType == EffectType.None) {
            mController.enableEffect(false);
        } else {
            mController.enableEffect(true);
            mController.changeEffect(effectType);
        }
    }
}
