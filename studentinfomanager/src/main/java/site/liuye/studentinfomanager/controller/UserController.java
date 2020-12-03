package site.liuye.studentinfomanager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.liuye.studentinfomanager.entity.Student;
import site.liuye.studentinfomanager.mapper.ClassInfoMapper;
import site.liuye.studentinfomanager.mapper.StudentMapper;

import java.util.List;

/**
 * @author LiuYe
 * @version 1.0
 * @date 2020/11/27 21:37
 */
@Api(tags = "用户API")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ClassInfoMapper classInfoMapper;

    @ApiOperation("查询所有学生的信息")
    @GetMapping("getAllStudent")
    public List<Student> getAllStudent() {
        return studentMapper.getAllStudent();
    }

    @ApiOperation("添加学生")
    @PostMapping("addStudent")
    public String addStudent(@RequestBody Student student) {
        if (student == null) {
            return "添加失败";
        }
        int res;
        try {
            res = studentMapper.addStudent(student);
        } catch (Exception e) {
            return "添加失败!原因：学号已存在！";
        }
        return (res == 1) ? "添加成功" : "添加失败";
    }

    @ApiOperation("删除学生")
    @PostMapping("deleteStudent")
    public String deleteStudent(Integer id) {
        if (id <= 0) {
            return "删除失败";
        }
        int res;
        try {
            res = studentMapper.deleteStudent(id);
        } catch (Exception e) {
            return "删除失败!";
        }
        return (res == 1) ? "删除成功" : "删除失败";
    }

    @ApiOperation("更新学生")
    @PostMapping("updateStudent")
    public String updateStudent(@RequestBody Student student) {
        if (student == null) {
            return "更新失败";
        }
        int res = studentMapper.updateStudent(student);
        return (res == 1) ? "更新成功" : "更新失败";
    }

    @ApiOperation("通过id获取某一学生信息")
    @PostMapping("getStudentById")
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }
}
