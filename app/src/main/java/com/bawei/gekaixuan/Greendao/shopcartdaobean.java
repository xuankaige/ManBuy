package com.bawei.gekaixuan.Greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 葛凯旋 on 2017/9/15.
 */
@Entity
public class shopcartdaobean {
    @Id(autoincrement = true)
    private   Long id;
    private   String title;
    private  String  price;
    private   String  image;
    private   int  count;
    private  boolean  checked;
    public boolean getChecked() {
        return this.checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 32668006)
    public shopcartdaobean(Long id, String title, String price, String image,
            int count, boolean checked) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.count = count;
        this.checked = checked;
    }
    @Generated(hash = 1977924507)
    public shopcartdaobean() {
    }



}
