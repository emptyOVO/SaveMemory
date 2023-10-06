package com.feige.savememory.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feige.savememory.entity.GameImage;
import com.feige.savememory.entity.Memory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IGameImageService extends IService<GameImage> {
    List<GameImage> AddGameImages(MultipartFile[] files, Long uid, Long uid1, String nickname, String realname);

    List<GameImage> selectGameImages(Long uid);

    GameImage updGameImage(String realname, String nickname, MultipartFile file, Long gid);

    GameImage deleteGameImage(Long gid);

    Map<String, Object> getGameImage(Long uid);
}
