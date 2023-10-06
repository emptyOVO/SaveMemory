package com.feige.savememory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feige.savememory.entity.AdrTimeLine;
import com.feige.savememory.mapper.AdrTimeLineMapper;
import com.feige.savememory.service.IAdrTimeLineService;
import com.feige.savememory.vo.AdrTimeLineUpdVo;
import com.feige.savememory.vo.AdrTimeLineVo;
import com.feige.savememory.vo.BindAdrTimeLineVo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
public class AdrTimeLineServiceImpl extends ServiceImpl<AdrTimeLineMapper, AdrTimeLine> implements IAdrTimeLineService {

    public ArrayList<Map<String,Object>> adrTimeLines(List<AdrTimeLine> adrTimeLineList){
        ArrayList<Map<String, Object>> timeLineList = new ArrayList<>();
        for (int i = 0; i < adrTimeLineList.size(); i++) {
            HashMap<String, Object> timeline = new HashMap<>();
            timeline.put("adrtlid",adrTimeLineList.get(i).getAdrtlid());
            timeline.put("updateAt",adrTimeLineList.get(i).getUpdateAt());
            timeline.put("remark",adrTimeLineList.get(i).getRemark());
            timeLineList.add(timeline);
        }return timeLineList;
    }

    @Override
    public List<AdrTimeLine> selectTimeLineByUid(Long uid) {
        LambdaQueryWrapper<AdrTimeLine> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdrTimeLine::getUid,uid);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    @Transactional
    public Map<String, Object> addAdrTimeLine(Long uid, AdrTimeLineVo adrTimeLineVo) {
        AdrTimeLine adrTimeLine = new AdrTimeLine();
        adrTimeLine.setAdrtlid(IdWorker.getId());
        adrTimeLine.setAid(adrTimeLineVo.getAid());
        adrTimeLine.setUpdateAt(new Date());
        adrTimeLine.setRemark(adrTimeLineVo.getRemark());
        boolean save = save(adrTimeLine);
        if(save){
            HashMap<String, Object> data = new HashMap<>();
            data.put("adrtlid",adrTimeLine.getAdrtlid());
            data.put("updateAt",adrTimeLine.getUpdateAt());
            data.put("remark",adrTimeLine.getRemark());
            return data;
        }
        return null;
    }

    @Override
    @Transactional
    public Map<String, Object> bindAdrTimeLine(Long uid, BindAdrTimeLineVo bindAdrTimeLineVo) {
        AdrTimeLine adrTimeLine = new AdrTimeLine();
        adrTimeLine.setAdrtlid(IdWorker.getId());
        adrTimeLine.setUid(bindAdrTimeLineVo.getUid());
        adrTimeLine.setUpdateAt(new Date());
        adrTimeLine.setRemark(bindAdrTimeLineVo.getRemark());
        boolean save = save(adrTimeLine);
        if(save){
            HashMap<String, Object> data = new HashMap<>();
            data.put("adrtlid",adrTimeLine.getAdrtlid());
            data.put("updateAt",adrTimeLine.getUpdateAt());
            data.put("remark",adrTimeLine.getRemark());
            return data;
        }
        return null;
    }

    @Override
    @Transactional
    public Map<String, Object> addAdrTimeLineDelete(Long adrtlid1) {
        LambdaQueryWrapper<AdrTimeLine> wr = new LambdaQueryWrapper<>();
        wr.eq(AdrTimeLine::getAdrtlid,adrtlid1);
        AdrTimeLine adrTimeLine = this.baseMapper.selectOne(wr);
        HashMap<String, Object> data = new HashMap<>();
        data.put("adrtlid",adrTimeLine.getAdrtlid());
        data.put("updateAt",adrTimeLine.getUpdateAt());
        data.put("remark",adrTimeLine.getRemark());
        this.baseMapper.delete(wr);
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> addAdrTimeLineUpd(AdrTimeLineUpdVo adrTimeLineUpdVo) {
        LambdaQueryWrapper<AdrTimeLine> wr = new LambdaQueryWrapper<>();
        wr.eq(AdrTimeLine::getAdrtlid,adrTimeLineUpdVo.getAdrtlid());
        AdrTimeLine adrTimeLine = new AdrTimeLine();
        adrTimeLine.setRemark(adrTimeLineUpdVo.getRemark());
        adrTimeLine.setUpdateAt(new Date());
        adrTimeLine.setAdrtlid(adrTimeLineUpdVo.getAdrtlid());
        this.saveOrUpdate(adrTimeLine,wr);
        AdrTimeLine adrTimeLine1 = this.baseMapper.selectOne(wr);
        HashMap<String, Object> data = new HashMap<>();
        data.put("adrtlid",adrTimeLine1.getAdrtlid());
        data.put("updateAt",adrTimeLine1.getUpdateAt());
        data.put("remark",adrTimeLine1.getRemark());
        return data;
    }

    @Override
    public boolean selectTimeLine(Long aid) {
        LambdaQueryWrapper<AdrTimeLine> wr = new LambdaQueryWrapper<>();
        wr.eq(AdrTimeLine::getAid,aid);
        return !this.baseMapper.selectList(wr).isEmpty();
    }

    @Override
    @Transactional
    public ArrayList<Map<String, Object>> DeleteByAddressListId(Long aid) {
        LambdaQueryWrapper<AdrTimeLine> wr = new LambdaQueryWrapper<>();
        wr.eq(AdrTimeLine::getAid,aid);
        List<AdrTimeLine> adrTimeLines = this.baseMapper.selectList(wr);
        ArrayList<Map<String, Object>> maps = adrTimeLines(adrTimeLines);
        this.baseMapper.delete(wr);
        return maps;
    }

    @Override
    public List<AdrTimeLine> selectTimeLineByAid(Long raid) {
        LambdaQueryWrapper<AdrTimeLine> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdrTimeLine::getAid,raid);
        return this.baseMapper.selectList(wrapper);
    }
}
