package com.oops.render;

import android.content.Context;

import com.oops.R;

/**
 * MirrorDrawer class.
 */
public class MirrorDrawer extends BaseDrawer {
    public MirrorDrawer(Context context, int textureID) {
        super(context, textureID, R.raw.direct_vertex_shader, R.raw.filter_mirror_fragment_shader);
    }
}
