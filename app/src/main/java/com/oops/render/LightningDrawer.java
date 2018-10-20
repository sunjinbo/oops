package com.oops.render;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import com.oops.R;
import com.oops.gles.GlUtil;

import java.nio.ByteBuffer;

/**
 * LightningDrawer class.
 */
public class LightningDrawer extends BaseDrawer {
    private final int mSrcTextureHandler;
    private final int mCacheTextureHandler;
    private final int mFitTimeHandler;
    private int mSrcTextureId = -1;
    private int mCacheTextureId = -1;
    private int mIndex;

    public LightningDrawer(Context context, int textureID) {
        super(context, textureID, R.raw.direct_vertex_shader, R.raw.filter_lightning_fragment_shader);
        mSrcTextureHandler = GLES20.glGetUniformLocation(mProgramId, "srcTexture");
        mCacheTextureHandler = GLES20.glGetUniformLocation(mProgramId, "cacheTexture");
        mFitTimeHandler = GLES20.glGetUniformLocation(mProgramId, "fit_time");

        mSrcTextureId = textureID;
    }

    public void draw(ByteBuffer byteBuffer, int width, int height, long timestamp) {

        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glUseProgram(mProgramId);

        mVertexBuffer.position(0);
        GLES20.glEnableVertexAttribArray(aPositionLocation);
        GLES20.glVertexAttribPointer(aPositionLocation, 3, GLES20.GL_FLOAT, false,
                12, mVertexBuffer);

        mTextureVertexBuffer.position(0);
        GLES20.glEnableVertexAttribArray(aTextureCoordLocation);
        GLES20.glVertexAttribPointer(aTextureCoordLocation, 2, GLES20.GL_FLOAT, false, 8, mTextureVertexBuffer);

        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, sProjectionMatrix, 0);
        GLES20.glUniformMatrix4fv(uSTMMatrixHandle, 1, false, mSTMatrix, 0);
        GLES20.glUniform1f(mFitTimeHandler, (mIndex % 10.0f) / 10.0f);

        if (mIndex % 10 == 0) {
            mCacheTextureId = GlUtil.loadTexture(byteBuffer, width, height, GLES20.GL_RGBA, mCacheTextureId);
        }
        mIndex++;

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mSrcTextureId);
        GLES20.glUniform1i(mSrcTextureHandler, 0);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE1);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mCacheTextureId);
        GLES20.glUniform1i(mCacheTextureHandler, 1);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

        GLES20.glDisableVertexAttribArray(aTextureCoordLocation);
        GLES20.glDisableVertexAttribArray(aPositionLocation);
    }
}
