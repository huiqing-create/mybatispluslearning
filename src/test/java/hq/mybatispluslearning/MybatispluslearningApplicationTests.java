package hq.mybatispluslearning;

import hq.mybatispluslearning.entry.User;
import hq.mybatispluslearning.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatispluslearningApplicationTests {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        System.out.println("=============");
        for (User user : userList) {
            System.out.println(user);
        }
    }


}
