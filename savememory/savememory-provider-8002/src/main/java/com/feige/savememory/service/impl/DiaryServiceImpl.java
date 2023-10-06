package com.feige.savememory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feige.savememory.entity.Diary;
import com.feige.savememory.entity.Image;
import com.feige.savememory.mapper.DiaryMapper;
import com.feige.savememory.service.IBindingService;
import com.feige.savememory.service.IDiaryService;
import com.feige.savememory.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements IDiaryService {

    @Autowired
    private IImageService imageService;

    @Autowired
    private IBindingService bindingService;


    @Override
    public Diary addDiary(Long uid , String title, String text) {
        Diary diary = new Diary();
        diary.setDid(IdWorker.getId());
        diary.setUid(uid);
        diary.setTitle(title);
        diary.setText(text);
        diary.setDatetime(new Date());
        boolean save = save(diary);
        if (save) {
            return diary;
        } else {
            return null;
        }
    }

    public ArrayList<Map<String, Object>> getImageList(Diary diary){
        List<Image> imageList = imageService.selectByDiaryId(diary.getDid());
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        for (Image image : imageList) {
            HashMap<String, Object> diary_image = new HashMap<>();
            diary_image.put("imid", image.getImid());
            diary_image.put("image", image.getImage());
            maps.add(diary_image);
        }return maps;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteDiary(Long uid, Long did1) {
        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Diary::getDid,did1).eq(Diary::getUid,uid);
        Diary diary = this.baseMapper.selectOne(wrapper);
        ArrayList<Map<String, Object>> maps = getImageList(diary);
        HashMap<String, Object> data = new HashMap<>();
        data.put("did",diary.getDid());
        data.put("diary_images",maps);
        data.put("datetime",diary.getDatetime());
        int delete = this.baseMapper.delete(wrapper);
        if(delete!=0) {
            return data;
        }else return null;
    }

    @Override
    public Diary updateDiary(String text, String title, Long rdid) {
        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Diary::getDid,rdid);
        Diary diary = new Diary();
        diary.setText(text);
        diary.setDatetime(new Date());
        diary.setTitle(title);
        this.baseMapper.update(diary,wrapper);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public Map<String, Object> getDiary(Long rdid) {
        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Diary::getDid,rdid);
        Diary diary = this.baseMapper.selectOne(wrapper);
        ArrayList<Map<String, Object>> maps = getImageList(diary);
        HashMap<String, Object> data = new HashMap<>();
        data.put("did",diary.getDid());
        data.put("uid",diary.getUid());
        data.put("title",diary.getTitle());
        data.put("text",diary.getText());
        data.put("diary_images",maps);
        data.put("datetime",diary.getDatetime());
        return data;
    }

    @Override
    public ArrayList<Map<String, Object>> getDiaryList(Long uid) {
        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Diary::getUid,uid);
        List<Diary> diaries = this.baseMapper.selectList(wrapper);
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < diaries.size(); i++) {
            HashMap<String, Object> diary = new HashMap<>();
            diary.put("did",diaries.get(i).getDid());
            diary.put("title",diaries.get(i).getTitle());
            diary.put("datetime",diaries.get(i).getDatetime());
            data.add(diary);
        }
        return data;
    }

    @Override
    public List<Diary> selectDiaryByUid(Long uid) {
        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Diary::getUid,uid).orderByDesc(Diary::getDatetime);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public ArrayList<Map<String, Object>> childGetDiaryList(Long uid, Long pid) {
        boolean res = bindingService.selectRelationShip(uid,pid);
        if(res){
            ArrayList<Map<String, Object>> maps = new ArrayList<>();
            LambdaQueryWrapper<Diary> wr = new LambdaQueryWrapper<>();
            wr.eq(Diary::getUid,pid).orderByDesc(Diary::getDatetime);
            List<Diary> diaryList = this.baseMapper.selectList(wr);
            for (int i = 0; i < diaryList.size(); i++) {
                HashMap<String, Object> diary = new HashMap<>();
                diary.put("did",diaryList.get(i).getDid());
                diary.put("title",diaryList.get(i).getTitle());
                diary.put("datetime",diaryList.get(i).getDatetime());
                maps.add(diary);
            }return maps;
        }
        return null;
    }


}
