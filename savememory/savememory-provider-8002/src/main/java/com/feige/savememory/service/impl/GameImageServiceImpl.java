package com.feige.savememory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feige.savememory.entity.GameImage;
import com.feige.savememory.mapper.GameImageMapper;
import com.feige.savememory.service.IBindingService;
import com.feige.savememory.service.IGameImageService;
import com.feige.savememory.util.PictureUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class GameImageServiceImpl extends ServiceImpl<GameImageMapper, GameImage> implements IGameImageService {

    @Autowired
    private PictureUploadUtil pictureUploadUtil;

    @Autowired
    private IBindingService bindingService;

    @Override
    public List<GameImage> AddGameImages(MultipartFile[] files, Long uid, Long uid1, String nickname, String realname) {
        boolean res = bindingService.selectRelationShip(uid1, uid);
        ArrayList<GameImage> gameImages = new ArrayList<>();
        if(res) {
            for (int i = 0; i < files.length; i++) {
                GameImage gameImage = new GameImage();
                gameImage.setGid(IdWorker.getId());
                gameImage.setNickname(nickname);
                gameImage.setRealname(realname);
                gameImage.setUid(uid);
                String url = pictureUploadUtil.PictureUpload(files[i]);
                gameImage.setGimage(url);
                save(gameImage);
                gameImages.add(gameImage);
            }return gameImages;
        }
        return null;
    }

    @Override
    public List<GameImage> selectGameImages(Long uid) {
        LambdaQueryWrapper<GameImage> wr = new LambdaQueryWrapper<>();
        wr.eq(GameImage::getUid,uid);
        return this.baseMapper.selectList(wr);
    }

    @Override
    public GameImage updGameImage(String realname, String nickname, MultipartFile file, Long gid) {
        LambdaQueryWrapper<GameImage> wr = new LambdaQueryWrapper<>();
        wr.eq(GameImage::getGid,gid);
        String url = pictureUploadUtil.PictureUpload(file);
        GameImage gameImage = new GameImage();
        gameImage.setGimage(url);
        gameImage.setRealname(realname);
        gameImage.setNickname(nickname);
        this.saveOrUpdate(gameImage,wr);
        return this.baseMapper.selectOne(wr);
    }

    @Override
    public GameImage deleteGameImage(Long gid) {
        LambdaQueryWrapper<GameImage> wr = new LambdaQueryWrapper<>();
        wr.eq(GameImage::getGid,gid);
        GameImage gameImage = this.baseMapper.selectOne(wr);
        this.baseMapper.delete(wr);
        return gameImage;
    }

    @Override
    public Map<String, Object> getGameImage(Long uid) {
        LambdaQueryWrapper<GameImage> wr = new LambdaQueryWrapper<>();
        wr.eq(GameImage::getUid,uid);
        List<GameImage> gameImageList = this.baseMapper.selectList(wr);
        if (gameImageList.isEmpty()) {
            return null;
        }
        // 获取随机索引
        Random random = new Random();
        int randomIndex = random.nextInt(gameImageList.size());
        GameImage gameImage = gameImageList.get(randomIndex);
        HashMap<String, Object> data = new HashMap<>();
        data.put("realname",gameImage.getRealname());
        data.put("nickname",gameImage.getNickname());
        data.put("gimage",gameImage.getGimage());
        return data;
    }
}
