package hq.mybatispluslearning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hq.mybatispluslearning.entry.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
