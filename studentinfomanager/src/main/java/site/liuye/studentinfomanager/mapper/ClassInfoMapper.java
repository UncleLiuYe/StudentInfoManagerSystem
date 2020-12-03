package site.liuye.studentinfomanager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import site.liuye.studentinfomanager.entity.ClassInfo;

import java.util.List;

/**
 * @author LiuYe
 * @version 1.0
 * @date 2020/11/27 19:10
 */
@Mapper
@Component
public interface ClassInfoMapper {
    List<ClassInfo> getAllClassInfo();

    int addClassInfo(@Param("classinfo") ClassInfo classInfo);

    int deleteClassInfo(@Param("id") Integer id);

    int updateClassInfo(@Param("ci") ClassInfo classInfo);

    ClassInfo getClassInfoById(@Param("id") Integer id);
}
