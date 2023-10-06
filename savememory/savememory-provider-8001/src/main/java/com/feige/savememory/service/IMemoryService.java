package com.feige.savememory.service;

import com.feige.savememory.entity.Memory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author feige
 * @since 2023-09-29
 */
public interface IMemoryService extends IService<Memory> {

    Memory addMemory(Long uid, String date, String detail) throws ParseException;


    Memory pUpdMemory(Long uid, Long meid, String date, String detail) throws ParseException;

    boolean deleteMemoryById(Long meid, Long uid);

    ArrayList<Map<String, Object>> getMemoryList(Long uid);

    Memory childAddMemory(Long pid, Long uid, String date, String detail) throws ParseException;

    Memory cUpdMemory(Long uid, Long pid, Long meid, String date, String detail) throws ParseException;
}
