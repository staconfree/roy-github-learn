package roy.github.learn.cat.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by roy on 2018/11/20.
 */
@Mapper
public interface CityMapper {

    @Select("select * from city")
    List<Map<String, Object>> queryCitys();

}
