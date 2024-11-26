package cn.tulingxueyuan.tests;

import cn.tulingxueyuan.entity.User;
import cn.tulingxueyuan.service.IUserService;
import cn.tulingxueyuan.service.impl.UseServiceImpl2;
import cn.tulingxueyuan.service.impl.UserServiceImpl;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class SpringTest {

    @Rule
    public final ExpectedException exceptionRule = ExpectedException.none();
    private ClassPathXmlApplicationContext ioc ;

    @Before
    public void init() {
        ioc = new ClassPathXmlApplicationContext("classpath:/spring.xml");
    }
    @Test
    public void test() throws Exception {
        IUserService bean = ioc.getBean(IUserService.class);
        System.out.println("bean.Class=" +  bean.getClass());
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Id不能为null");
        bean.select(null);
        // 在没有使用aop的情况下 ：class cn.tulingxueyuan.service.impl.UserServiceImpl
        // 当使用了aop的情况下：
        // class com.sun.proxy.$Proxy19
        //      jdk代理所产生的一个动态代理类，当被代理的类实现了接口会默认使用jdk代理
        //class cn.tulingxueyuan.service.impl.UserServiceImpl$$EnhancerBySpringCGLIB$$f281cf64
        //      cglib代理所所产生的一个动态代理类，当被代理的类没有实现接口就会使用cglib代理

    }

    @Test
    public void test02() throws Exception {
        UseServiceImpl2 bean = ioc.getBean(UseServiceImpl2.class);
        System.out.println("bean.Class=" +  bean.getClass());
        bean.select(null);
        System.out.println("----------");
        IUserService bean2 = (IUserService) ioc.getBean("userServiceImpl");
        System.out.println("bean.Class=" +  bean2.getClass());
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Id不能为null");
        bean2.select(null);
    }

    @Test
    public void test03() throws Exception {
        System.out.println("------------------");
        UseServiceImpl2 bean = ioc.getBean(UseServiceImpl2.class);
        bean.select(1);
        System.out.println("------------------");
        IUserService bean2 = ioc.getBean(IUserService.class);
        System.out.println("bean.Class=" +  bean2.getClass());
        bean2.select(1);
    }

}
