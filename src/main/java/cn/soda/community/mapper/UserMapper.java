package cn.soda.community.mapper;

import cn.soda.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified, avatar_url) values (#{name}, #{accountID}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);
}
