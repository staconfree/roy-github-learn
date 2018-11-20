package roy.github.learn.cat.controller;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roy.github.learn.cat.dao.CityDao;
import roy.github.learn.cat.dao.mapper.CityMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by roy on 2018/11/13.
 */
@RestController
public class CatController {

    @Autowired
    private CityDao cityDao;
    @Autowired
    private CityMapper cityMapper;

    @RequestMapping("/cat/hello")
    public String hello() {
        Transaction t = Cat.newTransaction("URL", "/cat/hello");
        try {
            Cat.logEvent("URL.Server", "10.11.29.20", Event.SUCCESS, "ip=10.11.29.20");
            Cat.logMetricForCount("PayCount");
            Cat.logMetricForDuration("PayAmount", 5);
            Thread.sleep(3000L);
            t.setStatus(Transaction.SUCCESS);
        } catch (Exception e) {
            t.setStatus(e);
            Cat.logError(e);
        } finally {
            t.complete();
        }
        return "hello cat";
    }

    @RequestMapping("/cat/hello2")
    public String hello2() {
        List<Map<String, Object>> list = cityDao.queryCitys();
        System.out.println("查询的数量："+list.size());
        return "hello2 cat";
    }

    @RequestMapping("/cat/hello3")
    public String hello3() {
        List<Map<String, Object>> list = cityMapper.queryCitys();
        System.out.println("查询的数量："+list.size());
        return "hello3 cat";
    }

}
