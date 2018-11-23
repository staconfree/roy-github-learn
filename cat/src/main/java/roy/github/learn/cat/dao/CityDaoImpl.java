package roy.github.learn.cat.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by roy on 2018/11/20.
 */
@Repository
public class CityDaoImpl implements CityDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> queryCitys() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from city");
        return list;
    }

}
