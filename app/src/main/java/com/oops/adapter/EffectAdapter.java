package com.oops.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.oops.data.EffectCallback;
import com.oops.data.EffectItemData;
import com.oops.R;
import com.oops.data.EffectType;

import java.util.ArrayList;
import java.util.List;

/**
 * EffectAdapter class.
 */
public class EffectAdapter extends RecyclerView.Adapter<EffectAdapter.MyViewHolder>  {

    private Context mContext;
    private List<EffectItemData> mData;
    private EffectCallback mCallback;

    public EffectAdapter(Context context, EffectCallback callback) {
        mContext = context;
        mCallback = callback;
        mData = new ArrayList<>();
        initData();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_effect, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final EffectItemData itemData = mData.get(position);

        if (itemData.isSelected()) {
            holder.mContent.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        } else {
            if (itemData.isImplementation()) {
                holder.mContent.setBackgroundColor(mContext.getResources().getColor(R.color.aquamarine));
            } else {
                holder.mContent.setBackgroundColor(mContext.getResources().getColor(R.color.grey));
            }
        }

        if (itemData.isImplementation()) {
            holder.mEffectContent.setBackgroundColor(mContext.getResources().getColor(R.color.aquamarine));
        } else {
            holder.mEffectContent.setBackgroundColor(mContext.getResources().getColor(R.color.grey));
        }

        holder.mContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemData.isImplementation()) {
                    for (EffectItemData d : mData) {
                        d.setSelected(false);
                    }

                    itemData.setSelected(true);

                    mCallback.onEffectChanged(itemData.getType());

                    EffectAdapter.this.notifyDataSetChanged();
                } else {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.no_implementation), Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.mEffectTextView.setText(itemData.getName(mContext));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ViewGroup mContent;
        private ViewGroup mEffectContent;
        private TextView mEffectTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mContent = itemView.findViewById(R.id.ly_select);
            mEffectContent = itemView.findViewById(R.id.ly_effect);
            mEffectTextView = itemView.findViewById(R.id.tv_effect);
        }
    }

    private void initData() {
        mData.add(new EffectItemData(EffectType.None, true, true));
        mData.add(new EffectItemData(EffectType.OldFilm, false, true));
        mData.add(new EffectItemData(EffectType.Mirror, false, true));
        mData.add(new EffectItemData(EffectType.Gauss, false, true));
        mData.add(new EffectItemData(EffectType.Vignette, false, true));
        mData.add(new EffectItemData(EffectType.NinePatch, false, true));
        mData.add(new EffectItemData(EffectType.ShockWave, false, true));
        mData.add(new EffectItemData(EffectType.NightWalk, false, true));
        mData.add(new EffectItemData(EffectType.Lightning, false, true));
        mData.add(new EffectItemData(EffectType.Shake, false, true));
        mData.add(new EffectItemData(EffectType.Puppets, false, true));
    }
}
