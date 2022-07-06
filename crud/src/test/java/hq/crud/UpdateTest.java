package hq.crud;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import hq.crud.entry.User;
import hq.crud.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void updateTest(){
        User user = new User();
        user.setId(1543516933342011394L);
        user.setEmail("goudan@qq.com");
        int i = userMapper.updateById(user);
    }


    @Test
    public void  updateByWrapperTest(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("name","李艺伟").eq("age",28);
        User user = new User();
        user.setEmail("lyw@qq.com");
        user.setAge(29);
        int update = userMapper.update(user, userUpdateWrapper);
    }


    @Test
    public void updateByWrapper3(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("name","李艺伟").set("name","王艺伟").eq("age",29).set("age",30);
        int update = userMapper.update(null, userUpdateWrapper);
    }

    @Test
    public void updateByWrapperLambdaTest(){
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(User::getName,"王艺伟").set(User::getName,"李艺伟");
        int i = userMapper.update(null, lambdaUpdateWrapper);
    }




}
