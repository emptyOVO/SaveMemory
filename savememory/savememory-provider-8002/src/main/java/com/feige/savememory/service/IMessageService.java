package com.feige.savememory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feige.savememory.entity.Message;
import com.feige.savememory.vo.MessageVo;

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
public interface IMessageService extends IService<Message> {

    Map<String, Object> addMessage(Long uid, MessageVo messageVo);

    ArrayList<Map<String, Object>> messageList(Long uid, Long toUserId);
}
