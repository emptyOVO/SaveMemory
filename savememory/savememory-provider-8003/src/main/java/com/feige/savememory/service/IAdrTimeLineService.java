package com.feige.savememory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feige.savememory.entity.AdrTimeLine;
import com.feige.savememory.vo.AdrTimeLineUpdVo;
import com.feige.savememory.vo.AdrTimeLineVo;
import com.feige.savememory.vo.BindAdrTimeLineVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IAdrTimeLineService extends IService<AdrTimeLine> {
    ArrayList<Map<String, Object>> DeleteByAddressListId(Long aid);

    List<AdrTimeLine> selectTimeLineByAid(Long raid);
    ArrayList<Map<String,Object>> adrTimeLines(List<AdrTimeLine> adrTimeLineList);

    List<AdrTimeLine> selectTimeLineByUid(Long uid);

    Map<String, Object> addAdrTimeLine(Long uid, AdrTimeLineVo adrTimeLineVo);

    Map<String, Object> bindAdrTimeLine(Long uid, BindAdrTimeLineVo bindAdrTimeLineVo);

    Map<String, Object> addAdrTimeLineDelete(Long adrtlid1);

    Map<String, Object> addAdrTimeLineUpd(AdrTimeLineUpdVo adrTimeLineUpdVo);

    boolean selectTimeLine(Long aid);
}
