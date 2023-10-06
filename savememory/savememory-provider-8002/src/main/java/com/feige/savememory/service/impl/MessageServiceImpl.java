package com.feige.savememory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feige.savememory.entity.Message;
import com.feige.savememory.mapper.MessageMapper;
import com.feige.savememory.service.IMessageService;
import com.feige.savememory.vo.MessageVo;
import org.springframework.stereotype.Service;

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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Override
    public Map<String, Object> addMessage(Long uid, MessageVo messageVo) {
        System.out.println(messageVo.getTo_user_id());
        Message message = new Message();
        message.setMsgId(IdWorker.getId());
        message.setContent(messageVo.getContent());
        message.setFromUserId(uid);
        message.setToUserId(messageVo.getTo_user_id());
        message.setCreateTime(new Date());
        boolean save = save(message);
        if(save){
            HashMap<String, Object> data = new HashMap<>();
            data.put("msg_id",message.getMsgId());
            data.put("from_user_id",message.getFromUserId());
            data.put("to_user_id",message.getToUserId());
            data.put("content",message.getContent());
            data.put("create_time",message.getCreateTime());
            return data;
        }
        return null;
    }

    @Override
    public ArrayList<Map<String, Object>> messageList(Long uid, Long toUserId) {
        LambdaQueryWrapper<Message> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Message::getFromUserId,uid).eq(Message::getToUserId,toUserId);
        List<Message> messages = this.baseMapper.selectList(wrapper);
        ArrayList<Map<String, Object>> array = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            HashMap<String, Object> msg = new HashMap<>();
            msg.put("from_user_id",messages.get(i).getFromUserId());
            msg.put("to_user_id",messages.get(i).getToUserId());
            msg.put("content",messages.get(i).getContent());
            msg.put("create_time",messages.get(i).getCreateTime());
            array.add(msg);
        }
        return array;
    }
}
