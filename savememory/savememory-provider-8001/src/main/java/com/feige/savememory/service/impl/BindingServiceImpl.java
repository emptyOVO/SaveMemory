package com.feige.savememory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feige.savememory.entity.Binding;
import com.feige.savememory.mapper.BindingMapper;
import com.feige.savememory.service.IBindingService;
import com.feige.savememory.vo.Bind;
import com.feige.savememory.vo.BindUser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class BindingServiceImpl extends ServiceImpl<BindingMapper, Binding> implements IBindingService {

    @Override
    public List<Binding> getParentId(Long uid) {
        LambdaQueryWrapper<Binding> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Binding::getChildid,uid);
        return baseMapper.selectList(wrapper);
    }

    @Override
    @Transactional
    public boolean addBinding(Long parentId, Long childId) {
        Binding binding = new Binding();
        binding.setBid(IdWorker.getId());
        binding.setChildid(childId);
        binding.setParentid(parentId);
        boolean save = save(binding);
        if(save){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteBinding(Long uid, Long uid1) {
        LambdaQueryWrapper<Binding> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Binding::getChildid,uid).eq(Binding::getParentid,uid1);
        int delete = this.baseMapper.delete(wrapper);
        if(delete!=0) {
            return true;
        }else {return false;}
    }

    @Override
    public List<Binding> getChildList(Long uid) {
        LambdaQueryWrapper<Binding> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Binding::getParentid,uid);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public boolean selectRelationShip(Long uid, Long pid) {
        LambdaQueryWrapper<Binding> wr = new LambdaQueryWrapper<>();
        wr.eq(Binding::getParentid,pid).eq(Binding::getChildid,uid);
        Binding binding = this.baseMapper.selectOne(wr);
        if(binding!=null){
            return true;
        }
        return false;
    }


}
