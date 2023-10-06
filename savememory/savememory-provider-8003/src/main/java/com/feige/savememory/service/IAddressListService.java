package com.feige.savememory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feige.savememory.entity.AddressList;
import com.feige.savememory.vo.AddAddressList;
import com.feige.savememory.vo.UpdateAddressList;

import java.util.ArrayList;
import java.util.Map;

public interface IAddressListService extends IService<AddressList> {
    AddressList addAddressList(AddAddressList addAddressList, Long uid);

    AddressList UpdateAddressList(UpdateAddressList updateAddressList, Long uid);

    Map<String,Object> deleteAddressList(Long aid1);

    Map<String,Object> getAInfo(Long raid, Long uid);

    Map<String, Object> getBInfo(Long rbid, Long uid);

    Map<String, ArrayList<Map<String, Object>>> getAdsListInfo(Long uid);

    AddressList updProfile(String url, String aid);

    AddressList selectRandomAddressList(Long uid);

    Map<String, Object> selectAddressListInfo(Long uid,Long aid);

    //List<AddressList> getlist();


}
