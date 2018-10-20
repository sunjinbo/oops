package com.oops.render;

import android.content.Context;
import android.opengl.GLES20;

import com.oops.R;

/**
 * ShakeDrawer class.
 */
public class ShakeDrawer extends BaseDrawer {

    private final int uFitTimeLocation;
    private boolean mIsStart = true;
    private long mStart;

    public ShakeDrawer(Context context, int textureID) {
        super(context, textureID, R.raw.direct_vertex_shader, R.raw.filter_shake_fragment_shader);
        uFitTimeLocation = GLES20.glGetUniformLocation(mProgramId, "fit_time");
    }

    @Override
    protected void childDraw() {
        super.childDraw();
        if (mIsStart) {
            mStart = System.currentTimeMillis();
            mIsStart = false;
        }

        GLES20.glUniform1f(uFitTimeLocation, ((float) (System.currentTimeMillis() - mStart) / 2000.0f) % 0.2f);
    }
}
