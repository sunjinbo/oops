package com.oops.data;

import android.content.Context;

import com.oops.R;

/**
 * EffectItemData class.
 */
public class EffectItemData {
    private EffectType mType;
    private boolean mIsSelected = false;
    private boolean mIsImplementation = false;

    public EffectItemData(EffectType type, boolean is_selected, boolean is_implementation) {
        this.mType = type;
        this.mIsSelected = is_selected;
        this.mIsImplementation = is_implementation;
    }

    public String getName(Context context) {
        switch (mType) {
            case None:
                return context.getResources().getString(R.string.none);
            case OldFilm:
                return context.getResources().getString(R.string.old_film);
            case Mirror:
                return context.getResources().getString(R.string.mirror);
            case Gauss:
                return context.getResources().getString(R.string.gauss);
            case Vignette:
                return context.getResources().getString(R.string.vignette);
            case Lightning:
                return context.getResources().getString(R.string.lightning);
            case Puppets:
                return context.getResources().getString(R.string.puppets);
            case NinePatch:
                return context.getResources().getString(R.string.nine_patch);
            case ShockWave:
                return context.getResources().getString(R.string.shock_wave);
            case NightWalk:
                return context.getResources().getString(R.string.night_walk);
            case Shake:
                return context.getResources().getString(R.string.shake);
            default:
                return "";
        }
    }

    public EffectType getType() {
        return mType;
    }

    public void setSelected(boolean is_selected) {
        mIsSelected = is_selected;
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public boolean isImplementation() {
        return mIsImplementation;
    }
}
