package com.bawei.gekaixuan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.gekaixuan.R;
import com.bawei.gekaixuan.bean.FenLeiBean1;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 葛凯旋 on 2017/9/1.
 */

public class ClassListViewAdapter extends BaseAdapter {
    private Context context;
    private int defItem;//声明默认选中的项
    private List<FenLeiBean1.DatasBean.ClassListBean> list = new ArrayList<>();

    public ClassListViewAdapter(Context context, List<FenLeiBean1.DatasBean.ClassListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return getItem(0);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public void setDefSelect(int i) {
        this.defItem = i;
       notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder holder;
        if (view == null) {
            holder = new Viewholder();
            view = View.inflate(context, R.layout.item_class_left, null);
            holder.image = view.findViewById(R.id.image);
            holder.title = view.findViewById(R.id.text);
            view.setTag(holder);
        } else {
            holder = (Viewholder) view.getTag();
        }
        Glide.with(context).load(list.get(i).getImage()).into(holder.image);

        holder.title.setText(list.get(i).getGc_name());
        return view;
    }

    class Viewholder {
        ImageView image;
        TextView title;
    }


}
