package com.feige.savememory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feige.savememory.entity.AddressList;
import com.feige.savememory.entity.AdrTimeLine;
import com.feige.savememory.entity.Binding;
import com.feige.savememory.entity.User;
import com.feige.savememory.mapper.AddressListMapper;
import com.feige.savememory.service.IAddressListService;
import com.feige.savememory.service.IAdrTimeLineService;
import com.feige.savememory.service.IBindingService;
import com.feige.savememory.service.IUserService;
import com.feige.savememory.vo.AddAddressList;
import com.feige.savememory.vo.UpdateAddressList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AddressListServiceImpl extends ServiceImpl<AddressListMapper, AddressList> implements IAddressListService {

    @Autowired
    private IAdrTimeLineService adrTimeLineService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBindingService bindingService;

    @Override
    public AddressList addAddressList(AddAddressList addAddressList, Long uid) {
        AddressList addressList = new AddressList();
        addressList.setAid(IdWorker.getId());
        addressList.setNickname(addAddressList.getNickname());
        addressList.setUid(uid);
        addressList.setPhone(addAddressList.getPhone());
        addressList.setProfile("https://img9.doubanio.com/view/group_topic/l/public/p570571224.webp");
        addressList.setRealname(addAddressList.getRealname());
        boolean save = save(addressList);
        if(save){
            return addressList;
        }
        return null;
    }

    @Override
    @Transactional
    public AddressList UpdateAddressList(UpdateAddressList updateAddressList, Long uid) {
        LambdaQueryWrapper<AddressList> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AddressList::getAid,updateAddressList.getAid());
        if(Objects.equals(this.baseMapper.selectOne(wrapper).getUid(), uid)){
            AddressList addressList = new AddressList();
            addressList.setRealname(updateAddressList.getRealname());
            addressList.setNickname(updateAddressList.getNickname());
            addressList.setPhone(updateAddressList.getPhone());
            baseMapper.update(addressList,wrapper);
        }
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    @Transactional
    public Map<String,Object> deleteAddressList(Long aid) {
        LambdaQueryWrapper<AddressList> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AddressList::getAid,aid);
        AddressList addressList = this.baseMapper.selectOne(wrapper);
        ArrayList<Map<String,Object>> adrTimeLineList = adrTimeLineService.DeleteByAddressListId(aid);
        this.baseMapper.delete(wrapper);
        Map<String, Object> data = Adatas(addressList, adrTimeLineList);
        return  data;
    }

    public Map<String, Object> Adatas(AddressList addressList,ArrayList<Map<String, Object>> list){
        HashMap<String, Object> data = new HashMap<>();
        data.put("aid",addressList.getAid());
        data.put("uid",addressList.getUid());
        data.put("realname",addressList.getRealname());
        data.put("nickname",addressList.getNickname());
        data.put("phone",addressList.getPhone());
        data.put("profile",addressList.getProfile());
        data.put("timeline",list);
        return data;
    }

    public Map<String, Object> Bdatas(User user,ArrayList<Map<String, Object>> list){
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",user.getUid());
        data.put("username",user.getUsername());
        data.put("nickname",user.getNickname());
        data.put("phone",user.getPhone());
        data.put("realname",user.getRealname());
        data.put("profile",user.getProfile());
        data.put("identity",user.getIdentity());
        data.put("timeline",list);
        return data;
    }



    @Override
    public Map<String,Object> getAInfo(Long raid, Long uid) {
        LambdaQueryWrapper<AddressList> wr = new LambdaQueryWrapper<>();
        wr.eq(AddressList::getAid,raid);
        AddressList addressList = this.baseMapper.selectOne(wr);
        ArrayList<Map<String, Object>> adrLines = adrTimeLineService.adrTimeLines(adrTimeLineService.selectTimeLineByAid(raid));
        return Adatas(addressList, adrLines);
    }

    @Override
    public Map<String, Object> getBInfo(Long rbid, Long uid) {
        User user = userService.selectById(rbid);
        ArrayList<Map<String, Object>> adrLines = adrTimeLineService.adrTimeLines(adrTimeLineService.selectTimeLineByUid(user.getUid()));
        return Bdatas(user,adrLines);
    }

    @Override
    public Map<String, ArrayList<Map<String, Object>>> getAdsListInfo(Long uid) {
        List<Binding> bindings = bindingService.getChildList(uid);
        ArrayList<Map<String, Object>> Amaps = new ArrayList<>();
        ArrayList<Map<String, Object>> Bmaps = new ArrayList<>();
        for (int i = 0; i < bindings.size(); i++) {
            User user = userService.selectById(uid);
            HashMap<String, Object> binding = new HashMap<>();
            binding.put("uid",user.getUid());
            binding.put("username",user.getUsername());
            binding.put("nickname",user.getNickname());
            binding.put("phone",user.getPhone());
            binding.put("realname",user.getRealname());
            binding.put("identity",user.getIdentity());
            binding.put("profile",user.getProfile());
            Bmaps.add(binding);
        }
        LambdaQueryWrapper<AddressList> wr = new LambdaQueryWrapper<>();
        wr.eq(AddressList::getUid,uid);
        List<AddressList> addressLists = this.baseMapper.selectList(wr);
        for (int i = 0; i < addressLists.size(); i++) {
            HashMap<String, Object> adds = new HashMap<>();
            adds.put("aid",addressLists.get(i).getAid());
            adds.put("realname",addressLists.get(i).getRealname());
            adds.put("nickname",addressLists.get(i).getNickname());
            adds.put("phone",addressLists.get(i).getPhone());
            adds.put("profile",addressLists.get(i).getProfile());
            Amaps.add(adds);
        }
        HashMap<String, ArrayList<Map<String, Object>>> data = new HashMap<>();
        data.put("bind",Bmaps);
        data.put("add",Amaps);
        return data;
    }

    @Override
    public AddressList updProfile(String url, String aid) {
        LambdaQueryWrapper<AddressList> wr = new LambdaQueryWrapper<>();
        wr.eq(AddressList::getAid,aid);
        AddressList addressList = new AddressList();
        addressList.setProfile(url);
        this.baseMapper.update(addressList,wr);
        return this.baseMapper.selectOne(wr);
    }

    @Override
    public AddressList selectRandomAddressList(Long uid) {
        LambdaQueryWrapper<AddressList> wr = new LambdaQueryWrapper<>();
        wr.eq(AddressList::getUid,uid);
        List<AddressList> addressLists = this.baseMapper.selectList(wr);
        if(addressLists.isEmpty()){
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(addressLists.size());
        return addressLists.get(randomIndex);
    }

    @Override
    public Map<String, Object> selectAddressListInfo(Long uid,Long aid) {
        LambdaQueryWrapper<AddressList> wr = new LambdaQueryWrapper<>();
        wr.eq(AddressList::getAid,aid);
        AddressList addressList = this.baseMapper.selectOne(wr);
        HashMap<String, Object> data = new HashMap<>();
        data.put("aid",addressList.getAid());
        data.put("uid",addressList.getUid());
        data.put("realname",addressList.getRealname());
        data.put("nickname",addressList.getNickname());
        data.put("phone",addressList.getPhone());
        data.put("profile",addressList.getProfile());
        List<AdrTimeLine> adrTimeLineList = adrTimeLineService.selectTimeLineByAid(aid);
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        for (int i = 0; i < adrTimeLineList.size(); i++) {
            HashMap<String, Object> adrTimeLine = new HashMap<>();
            adrTimeLine.put("adrtlid",adrTimeLineList.get(i).getAdrtlid());
            adrTimeLine.put("updateAt",adrTimeLineList.get(i).getUpdateAt());
            adrTimeLine.put("remark",adrTimeLineList.get(i).getRemark());
            maps.add(adrTimeLine);
        }data.put("timeline",maps);
        return data;
    }


}
