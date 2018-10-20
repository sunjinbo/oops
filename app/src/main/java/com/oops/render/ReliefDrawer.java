package com.oops.render;

import android.content.Context;
import android.opengl.GLES20;

import com.oops.R;

/**
 * ShockWaveDrawer class.
 */
public class ReliefDrawer extends BaseDrawer {

//    private final int uFitTimeLocation;

    public ReliefDrawer(Context context, int textureID) {
        super(context, textureID, R.raw.direct_vertex_shader, R.raw.filter_relief_fragment_shader);
//        uFitTimeLocation = GLES20.glGetUniformLocation(mProgramId, "fit_time");
    }

//    @Override
//    protected void childDraw() {
//        super.childDraw();
//        if (mIsStart) {
//            mStart = System.currentTimeMillis();
//            mIsStart = false;
//        }
//
//        GLES20.glUniform1f(uFitTimeLocation, ((float) (System.currentTimeMillis() - mStart) / 1000.0f) % 1.0f);
//    }
}
