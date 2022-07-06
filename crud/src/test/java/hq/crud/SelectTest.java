package hq.crud;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import hq.crud.entry.User;
import hq.crud.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 本测试都为 select 查询
 */
@SpringBootTest
class SelectTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertTest() {
        User user = new User();
        user.setName("狗蛋");
        user.setAge(27);
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());

        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void selectTest() {
        User user = userMapper.selectById(1087982257332887553L);
        System.out.println(user);
    }

    @Test
    public void selectBatchIdsTest() {
        List<Long> ids = Arrays.asList(1088248166370832385L, 1088250446457389058L, 1543507560599302145L);
        List<User> users = userMapper.selectBatchIds(ids);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByMapTest() {
        /*
        map.put("name",小明);
        map.put("age",12);
        select * from table_name where name = '小明' and age = 12;
         */
        HashMap<String, Object> map = new HashMap<>();
//        map.put("name", "张三");
        map.put("age", 27);
        List<User> users = userMapper.selectByMap(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 根据条件构造器查询
     */
    @Test
    public void selectByWrapper() {
        /*
        1.名字中含有 '张'
        2.年龄小于 30
         */
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /**
         * 链式调用
         */
        QueryWrapper<User> wrapper = queryWrapper.like("name", "张").lt("age", 30);
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * 根据条件构造器查询
     */
    @Test
    public void selectByWrapper2() {
        /*
          名字中含有雨，年龄大于20，小于40，邮箱非空

         */
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 根据条件构造器查询
     */
    @Test
    public void selectByWrapper3() {
        /*
            名字为王姓，或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
         */
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name", "王").or().ge("age", 25).orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(userQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 根据条件构造器查询
     */
    @Test
    public void selectByWrapper4() {
        /*
          4. 创建日期为2019年2月14日，并且直属上级为名字为王姓
          date_format(create_time, '%Y-%m-%d') and manager_id in (select id from user where name like '王%')
         */
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14");
        userQueryWrapper
                .inSql("manager_id", "select id from user where name like '王%'");
        List<User> users = userMapper.selectList(userQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 根据条件构造器查询
     */
    @Test
    public void selectByWrapper5() {
        /*
        名字为王姓并且（年龄小于40或邮箱为空）
        name = '王%' and (age < 40 or email is not null)
         */
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.likeRight("name", "王").and(i -> i.lt("age", 40).isNotNull("email"));
        List<User> users = userMapper.selectList(userQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 根据条件构造器查询
     */
    @Test
    public void selectByWrapper6() {
        /*
        名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空
        name like '王%' or (age between 20 and 40 and email is not null)
         */
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.likeRight("name", "王").or(i -> i.between("age", 20, 40).isNotNull("email"));
        List<User> users = userMapper.selectList(userQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void selectByWrapper8() {
        /*
        年龄为30,31,34,35
        age in (30,31,34,35)
         */
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("name", "id").in("age", 30, 31, 34, 35);
        List<User> users = userMapper.selectList(userQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testCondition() {
        String name = "王";
        String email = "";
        condition(name, email);

    }

    public void condition(String name, String email) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like(StringUtils.isNotEmpty(name), "name", name).gt(StringUtils.isNotEmpty(email), "age", email);
        List<User> users = userMapper.selectList(userQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * lambda条件构造器
     *
     * 使用好处就是防止字段名书写错误，Lambda使用  User::getName   , 之前是 name ，name可能会写错
     */
    @Test
    public void selectLambdaTest() {
        //创建Lambda条件构造器的三种方式
//        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
        lambdaQuery.like(User::getName, "雨");
        List<User> users = userMapper.selectList(lambdaQuery);
        for (User user : users) {
            System.out.println(user);
        }
    }


}
