package com.feige.savememory.vo;

import com.feige.savememory.entity.AddressList;

import java.util.ArrayList;
import java.util.List;

public class AddressListVo {
    private ArrayList<Add> add;
    private ArrayList<Bind> bind;

    public AddressListVo(ArrayList<Add> adds, ArrayList<Bind> binds) {
        this.add = adds;
        this.bind = binds;
    }


    public ArrayList<Add> getAdd() { return add; }
    public void setAdd(ArrayList<Add> value) { this.add = value; }

    public ArrayList<Bind> getBind() { return bind; }
    public void setBind(ArrayList<Bind> value) { this.bind = value; }
}
