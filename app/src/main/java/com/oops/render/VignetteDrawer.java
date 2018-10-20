package com.oops.render;

import android.content.Context;
import android.opengl.GLES20;

import com.oops.R;

import java.util.Random;

/**
 * VignetteDrawer class.
 */
public class VignetteDrawer extends BaseDrawer {

    private final int uFitTimeLocationX;
    private final int uFitTimeLocationY;
    private final Random mRand;

    public VignetteDrawer(Context context, int textureID) {
        super(context, textureID, R.raw.direct_vertex_shader, R.raw.filter_vignette_fragment_shader);
        uFitTimeLocationX = GLES20.glGetUniformLocation(mProgramId, "fit_time_x");
        uFitTimeLocationY = GLES20.glGetUniformLocation(mProgramId, "fit_time_y");
        mRand = new Random();
    }

    @Override
    protected void childDraw() {
        super.childDraw();

        GLES20.glUniform1f(uFitTimeLocationX, ((float) mRand.nextInt(1000) / 1000.0f));
        GLES20.glUniform1f(uFitTimeLocationY, ((float) mRand.nextInt(1000) / 1000.0f));
    }
}
