package com.bawei.gekaixuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bawei.gekaixuan.R;
import com.bawei.gekaixuan.adapter.ProductAdapter;
import com.bawei.gekaixuan.bean.Basebean;
import com.bawei.gekaixuan.bean.WatchBean;
import com.bawei.gekaixuan.net.Api;
import com.bawei.gekaixuan.net.HttpUtil;
import com.bawei.gekaixuan.net.OnNetListener;

import java.util.ArrayList;
import java.util.List;

public class Product_list extends AppCompatActivity implements View.OnClickListener {
    private TextView pai;
    private PopupWindow mPopWindow;
    private List<WatchBean.DatasBean.GoodsListBean> list = new ArrayList<>();
    private ListView lv;
    private ProductAdapter adapter;
    private TextView kong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        initdata();
        pai = (TextView) findViewById(R.id.paixu);
        kong = (TextView) findViewById(R.id.no);
        lv = (ListView) findViewById(R.id.productlist);
        pai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpop();
            }
        });
    }

    private void showpop() {
        //设置contentView
        View contentView = LayoutInflater.from(Product_list.this).inflate(R.layout.product_pop, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.showAsDropDown(pai);
        TextView tv1 = (TextView) contentView.findViewById(R.id.tv1);
        TextView tv2 = (TextView) contentView.findViewById(R.id.tv2);
        TextView tv3 = (TextView) contentView.findViewById(R.id.tv3);
        TextView tv4 = (TextView) contentView.findViewById(R.id.tv4);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(Product_list.this).inflate(R.layout.activity_product_list, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv1: {
                mPopWindow.dismiss();
            }
            break;
            case R.id.tv2: {
                mPopWindow.dismiss();
            }
            break;
            case R.id.tv3: {
                mPopWindow.dismiss();
            }
            break;
            case R.id.tv4: {
                mPopWindow.dismiss();
            }
            break;
        }

    }

    private void initdata() {
        Intent it = getIntent();
        final String id = it.getStringExtra("aaaa");
        HttpUtil.getHttpUtil().get(Product_list.this, Api.PRODUCT_LIST + id, WatchBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Basebean basebean) {
                WatchBean bean1 = (WatchBean) basebean;
                list.addAll(bean1.getDatas().getGoods_list());
                adapter = new ProductAdapter(Product_list.this, list);
                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String ID = list.get(i).getGoods_id();
                        Intent intent = new Intent(Product_list.this, DetailActivity.class);
                        intent.putExtra("id", ID);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}
