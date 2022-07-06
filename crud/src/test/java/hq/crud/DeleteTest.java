package hq.crud;

import hq.crud.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteByIdTest(){
        int i = userMapper.deleteById(1543516933342011394L);
        System.out.println("删除条数" + i);
    }



}
