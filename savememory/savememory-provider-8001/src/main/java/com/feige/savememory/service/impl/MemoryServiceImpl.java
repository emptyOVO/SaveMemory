package com.feige.savememory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.feige.savememory.entity.Memory;
import com.feige.savememory.entity.User;
import com.feige.savememory.mapper.MemoryMapper;
import com.feige.savememory.service.IImageService;
import com.feige.savememory.service.IMemoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feige.savememory.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author feige
 * @since 2023-09-29
 */
@Service
public class MemoryServiceImpl extends ServiceImpl<MemoryMapper, Memory> implements IMemoryService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IImageService imageService;

    @Override
    @Transactional
    public Memory addMemory(Long uid, String date, String detail) throws ParseException {
        User user = userService.selectById(uid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(date);
        Memory memory = new Memory();
        memory.setMeid(IdWorker.getId());
        memory.setPid(uid);
        memory.setDatetime(date1);
        memory.setUid(uid);
        memory.setProfile(user.getProfile());
        memory.setCreateTime(new Date());
        memory.setDetail(detail);
        boolean save = save(memory);
        if(save){
            return memory;
        }
        return null;
    }

    @Override
    @Transactional
    public Memory pUpdMemory(Long uid, Long meid, String date, String detail) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(date);
        LambdaQueryWrapper<Memory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Memory::getMeid,meid).eq(Memory::getUid,uid);
        Memory memory = new Memory();
        memory.setDatetime(date1);
        memory.setDetail(detail);
        this.baseMapper.update(memory,wrapper);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public boolean deleteMemoryById(Long meid, Long uid) {
        LambdaQueryWrapper<Memory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Memory::getMeid,meid).eq(Memory::getUid,uid);
        int delete = this.baseMapper.delete(wrapper);
        if(delete!=0){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Map<String, Object>> getMemoryList(Long uid) {
        LambdaQueryWrapper<Memory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Memory::getPid,uid).orderByDesc(Memory::getDatetime);
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        List<Memory> memories = this.baseMapper.selectList(wrapper);
        for (int i = 0; i < memories.size(); i++) {
            HashMap<String, Object> memory = new HashMap<>();
            ArrayList<Map<String, Object>> memory_image = imageService.selectByMemoryId(memories.get(i).getMeid());
            memory.put("meid",memories.get(i).getMeid());
            memory.put("pid",memories.get(i).getPid());
            memory.put("datetime",memories.get(i).getDatetime());
            memory.put("uid",memories.get(i).getUid());
            memory.put("profile",memories.get(i).getProfile());
            memory.put("detail",memories.get(i).getDetail());
            memory.put("memory_images",memory_image);
            maps.add(memory);
        }
        return maps;
    }

    @Override
    @Transactional
    public Memory childAddMemory(Long pid, Long uid, String date, String detail) throws ParseException {
        User user = userService.selectById(uid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(date);
        Memory memory = new Memory();
        memory.setMeid(IdWorker.getId());
        memory.setPid(pid);
        memory.setDatetime(date1);
        memory.setUid(uid);
        memory.setProfile(user.getProfile());
        memory.setCreateTime(new Date());
        memory.setDetail(detail);
        boolean save = save(memory);
        if(save){
            return memory;
        }
        return null;
    }

    @Override
    public Memory cUpdMemory(Long uid, Long pid, Long meid, String date, String detail) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(date);
        LambdaQueryWrapper<Memory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Memory::getMeid,meid).eq(Memory::getUid,uid).eq(Memory::getPid,pid);
        Memory memory = new Memory();
        memory.setDatetime(date1);
        memory.setDetail(detail);
        this.baseMapper.update(memory,wrapper);
        return this.baseMapper.selectOne(wrapper);
    }


}
