package com.feige.savememory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.feige.savememory.entity.Todo;
import com.feige.savememory.entity.User;
import com.feige.savememory.mapper.TodoMapper;
import com.feige.savememory.service.ITodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feige.savememory.vo.AddTodo;
import com.feige.savememory.vo.DeleteTodo;
import com.feige.savememory.vo.UpdateTodo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author feige
 * @since 2023-09-29
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements ITodoService {

    @Override
    public Todo addTodo(Long uid, AddTodo addTodo) {
        Todo todo = new Todo();
        todo.setTodo(addTodo.getTodo());
        todo.setUid(uid);
        todo.setCreateAt(new Date());
        todo.setDone(addTodo.getDone());
        todo.setTid(IdWorker.getId());
        todo.setTitle(addTodo.getTitle());
        save(todo);
        return todo;
    }

    @Override
    public List<Todo> getListById(Long uid) {
        LambdaQueryWrapper<Todo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Todo::getUid,uid);
        List<Todo> list = baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    @Transactional
    public Todo deleteTodo(Long uid, Long tid) {
        LambdaQueryWrapper<Todo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Todo::getTid,tid);
        Todo find = this.baseMapper.selectOne(wrapper);
        this.baseMapper.delete(wrapper);
        return find;
    }

    @Override
    @Transactional
    public Todo updateTodo(Long uid, UpdateTodo updateTodo) {
        LambdaQueryWrapper<Todo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Todo::getTid,updateTodo.getTid());
        Todo todo = new Todo();
        todo.setDone(updateTodo.getDone());
        this.baseMapper.update(todo,wrapper);
        return this.baseMapper.selectOne(wrapper);
    }


}
