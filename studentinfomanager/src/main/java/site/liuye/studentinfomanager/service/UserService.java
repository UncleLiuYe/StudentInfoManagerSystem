package site.liuye.studentinfomanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.liuye.studentinfomanager.entity.User;
import site.liuye.studentinfomanager.mapper.UserMapper;

/**
 * @author LiuYe
 * @version 1.0
 * @date 2020/11/27 11:55
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("账户不存在！");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        return user;
    }
}
