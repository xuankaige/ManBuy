package com.bawei.gekaixuan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.gekaixuan.Greendao.DBManager;
import com.bawei.gekaixuan.Greendao.shopcartdaobean;
import com.bawei.gekaixuan.R;
import com.bawei.gekaixuan.fragment.ShoppingcartFragment;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 葛凯旋 on 2017/9/16.
 */

public class shopcartadapter extends BaseAdapter {

    private List<shopcartdaobean> list;
    private int shu = 1;
    private Context context;
    DBManager db;
    private double v;
    private Double num2;
    private CheckBox check;


    public shopcartadapter(List<shopcartdaobean> list, Context context) {
        this.list = list;
        this.context = context;
        db = new DBManager(context);


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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final Viewholder holder;
        if (view == null) {
            holder = new Viewholder();
            view = View.inflate(context, R.layout.shoppingcart_item, null);
            holder.imageshop = view.findViewById(R.id.imageshop);
            holder.titleshop = view.findViewById(R.id.titleshop);
            holder.priceshop = view.findViewById(R.id.priceshop);
            holder.delete = view.findViewById(R.id.delete);
            holder.num = view.findViewById(R.id.numshopitem);
            holder.add = view.findViewById(R.id.addshopitem);
            holder.jian = view.findViewById(R.id.jianshopitem);
            holder.numcheng = view.findViewById(R.id.numcheng);
            holder.textView = view.findViewById(R.id.textView);
            holder.cbone = view.findViewById(R.id.checkone);
            view.setTag(holder);
        } else {
            holder = (Viewholder) view.getTag();
        }
        holder.priceshop.setText(list.get(i).getPrice());
        holder.titleshop.setText(list.get(i).getTitle());
        holder.cbone.setChecked(list.get(i).getChecked());
        Glide.with(context).load(list.get(i).getImage()).into(holder.imageshop);
        v = Double.parseDouble(list.get(i).getPrice());
        num2 = shu * v;
        //holder.textView.setText("共" + shu + "件商品，共计" + num2);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context, bean.getPrice(), Toast.LENGTH_SHORT).show();
                db.deleteUser(list.get(i));
                list.remove(i);
                notifyDataSetChanged();
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shu++;
                holder.num.setText(shu + "");
                holder.numcheng.setText("×" + shu);
                v = Double.parseDouble(list.get(i).getPrice());
                num2 = shu * v;
               // holder.textView.setText("共" + shu + "件商品，共计" + num2);

            }
        });
        holder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shu--;
                if (shu <= 0) {
                    shu = 0;
                    Toast.makeText(context, "已经最小了", Toast.LENGTH_SHORT).show();
                }
                holder.num.setText(shu + "");
                holder.numcheng.setText("×" + shu);

                v = Double.parseDouble(list.get(i).getPrice());
                num2 = shu * v;
               // holder.textView.setText("共" + shu + "件商品，共计" + num2);

            }
        });
        holder.cbone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopcartdaobean sb = list.get(i);
                boolean cb = sb.getChecked();
                sb.setChecked(!cb);
                notifyDataSetChanged();
                if (holder.cbone.isChecked()) {
                    //去判断其它按钮是否都选中
                    isAllChecked();

                } else {
                    //如果点击的按钮取消掉，即未选中状态，则去判断全选按钮是否是选中状态，如果是则取消选中
                    new ShoppingcartFragment().setCb(false);
                }


            }
        });
        return view;
    }

    class Viewholder {
        ImageView imageshop;
        TextView titleshop;
        TextView priceshop;
        Button delete;
        Button num;
        Button add;
        Button jian;
        TextView numcheng;
        TextView textView;
        CheckBox cbone;
    }

    private boolean isAllChecked() {
        for (int i = 0; i < list.size(); i++) {
            shopcartdaobean dataBeans = list.get(i);
            if (!dataBeans.getChecked()) {
                return false;
            }
        }
        return true;
    }

    public void selectedAll() {
        for (int i = 0; i < list.size(); i++) {
            shopcartdaobean dataBean = list.get(i);
            dataBean.setChecked(true);
        }
        notifyDataSetChanged();
    }

    public void cancleAll() {
        for (int i = 0; i < list.size(); i++) {
            shopcartdaobean dataBean = list.get(i);
            dataBean.setChecked(false);
        }
        notifyDataSetChanged();
    }

    public void zong() {
        num2 = +num2;
    }
}
