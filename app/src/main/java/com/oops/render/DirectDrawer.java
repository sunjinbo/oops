package com.oops.render;

import android.content.Context;

import com.oops.R;

/**
 * DirectDrawer class.
 */
public class DirectDrawer extends BaseDrawer {
    public DirectDrawer(Context context, int textureID) {
        super(context, textureID, R.raw.direct_vertex_shader, R.raw.direct_fragment_shader);
    }
}
