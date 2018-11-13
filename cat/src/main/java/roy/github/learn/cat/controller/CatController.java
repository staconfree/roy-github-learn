package roy.github.learn.cat.controller;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiexiaoqing1 on 2018/11/13.
 */
@RestController
public class CatController {

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

}
