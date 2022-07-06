package hq.crud.entry;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User extends Model<User> {

    //主键id
    private Long id;
    //姓名
    private String name;
    //年龄
    private int age;
    //邮箱
    private String email;
    //上司id
    private Long managerId;
    //创建时间
    private LocalDateTime createTime;

}
