package com.bawei.gekaixuan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.gekaixuan.Greendao.DBManager;
import com.bawei.gekaixuan.Greendao.shopcartdaobean;
import com.bawei.gekaixuan.R;
import com.bawei.gekaixuan.adapter.shopcartadapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 葛凯旋 on 2017/9/1.
 */

public class ShoppingcartFragment extends Fragment  {

    private ListView lv;
    private List<shopcartdaobean> list = new ArrayList<>();
    private shopcartadapter adapter;
    private DBManager db;
    private static CheckBox all;
    private TextView zong;
    private Button pay;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopcart, container, false);
        db = new DBManager(getActivity());
        lv = view.findViewById(R.id.mylist);
        all = view.findViewById(R.id.checkall);
        zong = view.findViewById(R.id.heji);
        pay = view.findViewById(R.id.pay);
        list = db.queryUserList();
        adapter = new shopcartadapter(list, getActivity());
        lv.setAdapter(adapter);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (all.isChecked()) {
                    all.setChecked(true);
                    adapter.selectedAll();
                } else {
                    all.setChecked(false);
                    adapter.cancleAll();
                }
            }
        });
        return view;
    }

    public static void setCb(boolean bool) {
        all.setChecked(bool);
    }

}
