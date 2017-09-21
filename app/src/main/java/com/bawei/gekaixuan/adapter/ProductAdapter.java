package com.bawei.gekaixuan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.gekaixuan.R;
import com.bawei.gekaixuan.bean.WatchBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 葛凯旋 on 2017/9/7.
 */

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<WatchBean.DatasBean.GoodsListBean> list = new ArrayList<>();

    public ProductAdapter(Context context, List<WatchBean.DatasBean.GoodsListBean> list) {
        this.context = context;
        this.list = list;
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
            holder.sale = view.findViewById(R.id.sale);
            view.setTag(holder);
        } else {
            holder = (Viewholder) view.getTag();


        }
        WatchBean.DatasBean.GoodsListBean bean = list.get(i);
        Glide.with(context).load(bean.getGoods_image_url()).into(holder.image);
        holder.title.setText(bean.getGoods_name());
        holder.price.setText("¥" + bean.getGoods_price());
        holder.sale.setText("已售：" + bean.getGoods_salenum() + "件");
        return view;
    }

    class Viewholder {
        ImageView image;
        TextView title;
        TextView price;
        TextView sale;
    }
}
