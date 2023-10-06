package com.feige.savememory.service;

import com.auth0.jwt.interfaces.Claim;
import com.feige.savememory.entity.Image;
import com.baomidou.mybatisplus.extension.service.IService;

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
public interface IImageService extends IService<Image> {

    Image addOwnProfilePic(String url, Long uid);

    Image addDiaryPic(String picUrl, Long uid, Long did);

    List<Image> selectByDiaryId(Long did);

    void DeleteByDiaryId(Long valueOf);

    Image addAddressListProfilePic(String url, Long uid, Long aid);

    Image addMemoryPic(String picUrl, Long uid, Long meid);

    void deleteMemoryPic(Long meid);

    ArrayList<Map<String, Object>> selectByMemoryId(Long meid);


    Image childAddMemoryPic(String picUrl, Long uid, Long meid);
}
