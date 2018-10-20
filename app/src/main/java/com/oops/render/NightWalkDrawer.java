package com.oops.render;

import android.content.Context;
import android.opengl.GLES20;

import com.oops.R;
import com.oops.gles.GlUtil;

import java.nio.ByteBuffer;

/**
 * NightWalkDrawer class.
 */
public class NightWalkDrawer extends BaseDrawer {

    private int mUniformTexelWidthLocation;
    private int mUniformTexelHeightLocation;

    public NightWalkDrawer(Context context, int textureID) {
        super(context, textureID, R.raw.night_walk_vertex_shader, R.raw.night_walk_fragment_shader);

        mUniformTexelWidthLocation = GLES20.glGetUniformLocation(mProgramId, "texelWidth");
        GlUtil.checkLocation(mUniformTexelWidthLocation, "texelWidth");
        mUniformTexelHeightLocation = GLES20.glGetUniformLocation(mProgramId, "texelHeight");
        GlUtil.checkLocation(mUniformTexelHeightLocation, "texelHeight");
    }

    @Override
    public void draw(ByteBuffer byteBuffer, int width, int height, long timestamp) {
        if (!mRendererStarted) {
            return;
        }

        GLES20.glUseProgram(mProgramId);

        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_SAMPLER_2D, mTextureID);

        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, sProjectionMatrix, 0);
        GLES20.glUniformMatrix4fv(uSTMMatrixHandle, 1, false, mSTMatrix, 0);

        GLES20.glUniform1f(mUniformTexelWidthLocation, 1.0f / width);
        GLES20.glUniform1f(mUniformTexelHeightLocation, 1.0f / height);

        mVertexBuffer.position(0);
        GLES20.glEnableVertexAttribArray(aPositionLocation);
        GLES20.glVertexAttribPointer(aPositionLocation, 3, GLES20.GL_FLOAT, false,
                12, mVertexBuffer);

        mTextureVertexBuffer.position(0);
        GLES20.glEnableVertexAttribArray(aTextureCoordLocation);
        GLES20.glVertexAttribPointer(aTextureCoordLocation, 2, GLES20.GL_FLOAT, false, 8, mTextureVertexBuffer);

        childDraw();

        GLES20.glViewport(0, 0, mScreenWidth, mScreenHeight);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

        GLES20.glDisable(GLES20.GL_BLEND);
        GLES20.glUseProgram(0);
    }
}
