package com.bawei.gekaixuan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.gekaixuan.R;
import com.bawei.gekaixuan.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 葛凯旋 on 2017/9/3.
 */

public class GridAdapter extends BaseAdapter {
    private Context context;
    private TextView title1;
    private List<Bean> been = new ArrayList<>();

    public GridAdapter(List<Bean> been, Context context) {
        this.been = been;
        this.context = context;



    }

    @Override
    public int getCount() {
        return been == null ? 0 : been.size();
    }

    @Override
    public Object getItem(int i) {
        return been.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder holder;
        if (view == null) {
            holder = new Viewholder();
            view = View.inflate(context, R.layout.item_class_right_griditem, null);
            holder.title1 = view.findViewById(R.id.product);
            view.setTag(holder);
        } else {
            holder = (Viewholder) view.getTag();
        }
        holder.title1.setText(been.get(i).name);

        return view;
    }

    class Viewholder {
        TextView title1;
    }
}
