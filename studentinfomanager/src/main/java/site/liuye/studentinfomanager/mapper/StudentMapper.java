package site.liuye.studentinfomanager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import site.liuye.studentinfomanager.entity.Student;

import java.util.List;

/**
 * @author LiuYe
 * @version 1.0
 * @date 2020/11/27 18:31
 */
@Mapper
@Component
public interface StudentMapper {
    List<Student> getAllStudent();

    int addStudent(@Param("student") Student student);

    int deleteStudent(@Param("id") Integer id);

    int updateStudent(@Param("student") Student student);

    Student getStudentById(@Param("id") Integer id);
}
