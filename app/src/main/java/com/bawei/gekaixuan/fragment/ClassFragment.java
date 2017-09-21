package com.bawei.gekaixuan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.gekaixuan.R;
import com.bawei.gekaixuan.activity.Product_list;
import com.bawei.gekaixuan.adapter.GridAdapter;
import com.bawei.gekaixuan.adapter.ClassListViewAdapter;
import com.bawei.gekaixuan.bean.Basebean;
import com.bawei.gekaixuan.bean.Bean;
import com.bawei.gekaixuan.bean.FenLeiBean1;
import com.bawei.gekaixuan.bean.TitleBean;
import com.bawei.gekaixuan.net.Api;
import com.bawei.gekaixuan.net.HttpUtil;
import com.bawei.gekaixuan.net.OnNetListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 葛凯旋 on 2017/9/1.
 */

public class ClassFragment extends Fragment {
    private ListView lv;
    private List<FenLeiBean1.DatasBean.ClassListBean> list = new ArrayList<>();
    private ClassListViewAdapter adapter;
    private List<Bean> title;
    private Map<String, List<Bean>> map;
    private List<Bean> childTitle;
    private ExpandableListView expandableListView;
    private MyAdapter1 adapter1;
    List<Bean> been;
    private List<String> lastid = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fenlei, container, false);
        lv = view.findViewById(R.id.mylist1);
        initdata();
        title = new ArrayList<>();
        map = new HashMap<>();
        expandableListView = view.findViewById(R.id.expandable);
        expandableListView.setGroupIndicator(null);
        adapter1 = new MyAdapter1();
        expandableListView.setAdapter(adapter1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                title.clear();
                adapter1.notifyDataSetChanged();
                initData1(i);
            }
        });
        return view;
    }

    private void initdata() {
        HttpUtil.getHttpUtil().get(getActivity(), Api.FENLEI_left, FenLeiBean1.class, new OnNetListener() {
            @Override
            public void onSuccess(Basebean basebean) {
                FenLeiBean1 bean1 = (FenLeiBean1) basebean;
                list.addAll(bean1.getDatas().getClass_list());
                adapter = new ClassListViewAdapter(getActivity(), list);
                lv.setAdapter(adapter);
                adapter.setDefSelect(0);
                adapter.notifyDataSetChanged();

            }
        });
    }

    private void initData1(int i) {
        HttpUtil.getHttpUtil().get(getActivity(), Api.FENLEI_right + list.get(i).getGc_id(), TitleBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Basebean basebean) {
                TitleBean bean2 = (TitleBean) basebean;
                List<TitleBean.DatasBean.ClassListBean> list1 = new ArrayList<TitleBean.DatasBean.ClassListBean>();
                list1.addAll(bean2.getDatas().getClass_list());
                for (TitleBean.DatasBean.ClassListBean classListBean : list1) {
                    Bean b = new Bean();
                    b.name = classListBean.getGc_name();
                    b.id = classListBean.getGc_id();
                    title.add(b);
                }
                initData2();
            }
        });
    }

    private void initData2() {
        for (final Bean bean : title) {
            HttpUtil.getHttpUtil().get(getActivity(), Api.FENLEI_right + bean.id, TitleBean.class, new OnNetListener() {
                @Override
                public void onSuccess(Basebean basebean) {
                    TitleBean bean3 = (TitleBean) basebean;
                    List<TitleBean.DatasBean.ClassListBean> listt = new ArrayList<TitleBean.DatasBean.ClassListBean>();
                    listt.addAll(bean3.getDatas().getClass_list());
                    childTitle = new ArrayList<>();
                    for (TitleBean.DatasBean.ClassListBean classListBean : listt) {
                        Bean b = new Bean();
                        b.name = classListBean.getGc_name();
                        b.id = classListBean.getGc_id();
                        childTitle.add(b);
                    }
                    map.put(bean.name, childTitle);

                }

            });
        }
        for (int i = 0; i < adapter1.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
        adapter1.notifyDataSetChanged();

    }


    class MyAdapter1 extends BaseExpandableListAdapter {
        @Override
        public int getGroupCount() {
            return title.size();
        }

        @Override
        public int getChildrenCount(int i) {

            return 1;
        }

        @Override
        public Object getGroup(int i) {
            return null;
        }

        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return 0;
        }

        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            view = View.inflate(getActivity(), R.layout.item_class_right, null);
            TextView textView = (TextView) view.findViewById(R.id.title);
            textView.setText(title.get(i).name);
            return view;
        }

        @Override
        public View getChildView(int i, int pos, boolean b, View view, ViewGroup viewGroup) {
            view = View.inflate(getActivity(), R.layout.item_class_right_grid, null);
            GridView grid = (GridView) view.findViewById(R.id.mygrid);
            been = map.get(title.get(i).name);
            GridAdapter adapter2 = new GridAdapter(been, getActivity());
            grid.setAdapter(adapter2);
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent it = new Intent(getActivity(), Product_list.class);
                      String id=been.get(i).id;
                     it.putExtra("aaaa",id);
                    startActivity(it);
                }
            });


            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
}
