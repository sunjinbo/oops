package com.oops.render;

import android.content.Context;

import com.oops.R;

/**
 * NinePatchDrawer class.
 */
public class NinePatchDrawer extends BaseDrawer {
    public NinePatchDrawer(Context context, int textureID) {
        super(context, textureID, R.raw.direct_vertex_shader, R.raw.filter_ninepatch_fragment_shader);
    }
}
