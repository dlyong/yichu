package com.dlyong.yichu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dlyong.yichu.domain.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<UserEntity> {
}
