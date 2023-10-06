package com.feige.savememory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feige.savememory.entity.Todo;
import com.feige.savememory.vo.AddTodo;
import com.feige.savememory.vo.UpdateTodo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author feige
 * @since 2023-09-29
 */
public interface ITodoService extends IService<Todo> {

    Todo addTodo(Long uid, AddTodo addTodo);

    List<Todo> getListById(Long uid);

    Todo deleteTodo(Long uid, Long tid);

    Todo updateTodo(Long uid, UpdateTodo updateTodo);
}
