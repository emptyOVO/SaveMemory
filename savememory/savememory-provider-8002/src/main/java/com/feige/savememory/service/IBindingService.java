package com.feige.savememory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feige.savememory.entity.Binding;

import java.util.List;

public interface IBindingService extends IService<Binding> {
    List<Binding> getParentId(Long uid);

    boolean addBinding(Long uid, Long uid1);

    boolean deleteBinding(Long uid, Long uid1);

    List<Binding> getChildList(Long uid);

    boolean selectRelationShip(Long uid, Long pid);
}
