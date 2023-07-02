package com.device.system.core.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.device.common.constanst.CacheKey;
import com.device.common.exception.Checker;
import com.device.common.exception.Errors;
import com.device.common.utils.RedisUtils;
import com.device.mbg.utils.PageUtil;
import com.device.system.core.entity.Dict;
import com.device.system.core.entity.enums.DictEnum;
import com.device.system.core.mapper.DictMapper;
import com.device.system.core.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-30
 */
@Service
@CacheConfig(cacheNames = CacheKey.DICT)
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Cacheable(key = "#root.target.getKey() + #p0")
    public Map<String, Object> queryAll(DictEnum dict, boolean enable, Page page) {
        if (Objects.isNull(dict)) {
            return null;
        }
        IPage<Dict> iPage = baseMapper.selectPage(page,
            new LambdaQueryWrapper<Dict>()
                .eq(Dict::getType, dict.name())
                .eq(Dict::getStatus, enable)
                .orderByAsc(Dict::getSort)
        );
        return PageUtil.toPage(iPage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(Dict dict) {
        Checker.ifThrow(isRepeat(dict), () -> Errors.BIZ.exception("字典名称重复"));
        baseMapper.insert(dict);
        delCaches(dict.getType());
    }

    /**
     * 是否重复
     * @param dict
     * @return
     */
    private boolean isRepeat(Dict dict) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<Dict>()
            .eq(Dict::getName, dict.getName())
            .eq(Dict::getType, dict.getType().name());
        Checker.ifNullThen(dict.getId(), then -> wrapper.ne(Dict::getId, dict.getId()));
        return baseMapper.selectCount(wrapper) > 0;
    }

    private void delCaches(DictEnum dict){
        redisUtils.del(String.format("%s%s", getKey(), dict.name()));
    }
    public String getKey(){
        return  String.format("%s:", StpUtil.getLoginId());
    }

}
