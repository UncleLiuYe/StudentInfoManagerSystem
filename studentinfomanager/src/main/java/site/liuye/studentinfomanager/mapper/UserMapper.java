package site.liuye.studentinfomanager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import site.liuye.studentinfomanager.entity.Role;
import site.liuye.studentinfomanager.entity.User;

import java.util.List;

/**
 * @author LiuYe
 * @version 1.0
 * @date 2020/11/27 11:53
 */
@Mapper
@Component
public interface UserMapper {
    User loadUserByUsername(@Param("username") String username);

    List<Role> getUserRolesByUid(@Param("id") Integer id);
}
