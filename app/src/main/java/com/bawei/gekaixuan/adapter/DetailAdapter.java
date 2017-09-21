package com.bawei.gekaixuan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.gekaixuan.R;
import com.bawei.gekaixuan.bean.Detailbean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 葛凯旋 on 2017/9/8.
 */

public class DetailAdapter extends BaseAdapter {
    private List<Detailbean.DatasBean.GoodsCommendListBean> list = new ArrayList<>();
    private Context context;

    public DetailAdapter(List<Detailbean.DatasBean.GoodsCommendListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder holder;
        if (view == null) {
            holder = new Viewholder();
            view = View.inflate(context, R.layout.product_list_item, null);
            holder.image = view.findViewById(R.id.image);
            holder.title = view.findViewById(R.id.title);
            holder.price = view.findViewById(R.id.price);

            view.setTag(holder);
        } else {
            holder = (Viewholder) view.getTag();


        }
        Detailbean.DatasBean.GoodsCommendListBean bean = list.get(i);
        Glide.with(context).load(bean.getGoods_image_url()).into(holder.image);
        holder.title.setText(bean.getGoods_name());
        holder.price.setText("¥" + bean.getGoods_promotion_price());

        return view;
    }

    class Viewholder {
        ImageView image;
        TextView title;
        TextView price;

    }
}
