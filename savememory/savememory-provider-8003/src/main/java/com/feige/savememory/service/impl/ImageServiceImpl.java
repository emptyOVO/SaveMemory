package com.feige.savememory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feige.savememory.entity.Image;
import com.feige.savememory.mapper.ImageMapper;
import com.feige.savememory.service.IImageService;
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
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {

    @Override
    public Image addOwnProfilePic(String url, Long uid) {
        Image image = new Image();
        image.setImid(IdWorker.getId());
        image.setImage(url);
        image.setBelongs("6");
        image.setUid(uid);
        image.setDatetime(new Date());
        save(image);
        return image;
    }

    @Override
    public Image addDiaryPic(String picUrl, Long uid, Long did) {
        Image image = new Image();
        image.setImid(IdWorker.getId());
        image.setImage(picUrl);
        image.setBelongs("1");
        image.setUid(uid);
        image.setDatetime(new Date());
        image.setDiaryId(did);
        save(image);
        return image;
    }

    @Override
    public List<Image> selectByDiaryId(Long did) {
        LambdaQueryWrapper<Image> wr = new LambdaQueryWrapper<>();
        wr.eq(Image::getDiaryId,did);
        return this.baseMapper.selectList(wr);
    }

    @Override
    public void DeleteByDiaryId(Long did) {
        LambdaQueryWrapper<Image> wr = new LambdaQueryWrapper<>();
        wr.eq(Image::getDiaryId,did);
        this.baseMapper.delete(wr);
    }

    @Override
    public Image addAddressListProfilePic(String url, Long uid, Long aid) {
        Image image = new Image();
        image.setImid(IdWorker.getId());
        image.setImage(url);
        image.setBelongs("5");
        image.setUid(uid);
        image.setDatetime(new Date());
        image.setAdrslId(Long.valueOf(aid));
        boolean save = save(image);
        if(save){
            return image;
        }
        return null;
    }

    @Override
    public Image addMemoryPic(String picUrl, Long uid, Long meid) {
        Image image = new Image();
        image.setImid(IdWorker.getId());
        image.setImage(picUrl);
        image.setBelongs("4");
        image.setUid(uid);
        image.setDatetime(new Date());
        image.setMemoryId(meid);
        boolean save = save(image);
        if(save){
            return image;
        }
        return null;
    }

    @Override
    public void deleteMemoryPic(Long meid) {
        LambdaQueryWrapper<Image> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Image::getMemoryId,meid);
        this.baseMapper.delete(wrapper);
    }

    @Override
    public ArrayList<Map<String, Object>> selectByMemoryId(Long meid) {
        LambdaQueryWrapper<Image> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Image::getMemoryId,meid);
        List<Image> imageList = this.baseMapper.selectList(wrapper);
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        for (int i = 0; i < imageList.size(); i++) {
            HashMap<String, Object> image = new HashMap<>();
            image.put("imid",imageList.get(i).getImid());
            image.put("image",imageList.get(i).getImage());
            maps.add(image);
        }
        return maps;
    }

    @Override
    public Image childAddMemoryPic(String picUrl, Long uid, Long meid) {
        Image image = new Image();
        image.setImid(IdWorker.getId());
        image.setImage(picUrl);
        image.setBelongs("3");
        image.setUid(uid);
        image.setDatetime(new Date());
        image.setMemoryId(meid);
        boolean save = save(image);
        if(save){
            return image;
        }
        return null;
    }


}
