package roy.github.learn.springbeans;

import org.springframework.beans.BeanUtils;

/**
 * Created by roy on 2018/12/21.
 */
public class Beans {

    public static void main(String[] args) {
        newInstance();
    }

    public static BeanA newInstance() {
        BeanA beanA = BeanUtils.instantiateClass(BeanA.class);
        return beanA;
    }

    class BeanA{
        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

}
