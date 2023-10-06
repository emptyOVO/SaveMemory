package com.feige.savememory.service;

import com.feige.savememory.entity.Diary;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author feige
 * @since 2023-09-29
 */
public interface IDiaryService extends IService<Diary> {

    Diary addDiary(Long uid,String title, String text);

    Map<String, Object> deleteDiary(Long uid, Long did1);

    Diary updateDiary(String text, String title, Long rdid);

    Map<String, Object> getDiary(Long rdid);

    ArrayList<Map<String, Object>> getDiaryList(Long uid);


    List<Diary> selectDiaryByUid(Long uid);

    List<Map<String, Object>> childGetDiaryList(Long uid, Long pid);
}
