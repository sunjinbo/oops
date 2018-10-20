package com.oops.render;

import android.content.Context;

import com.oops.R;

/**
 * BlurDrawer class.
 */
public class BlurDrawer extends BaseDrawer {
    public BlurDrawer(Context context, int textureID) {
        super(context, textureID, R.raw.direct_vertex_shader, R.raw.filter_blur_fragment_shader);
    }
}
