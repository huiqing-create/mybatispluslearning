package hq.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hq.crud.entry.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
