package cn.tulingxueyuan.aspects;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
//Aspect    // 标记为切面
@Component // 标记Bean组件， 才能切入到ioc当中的bean
public class LogAspectTwo {



    // 前置通知
    // @Before("execution(* cn.tulingxueyuan.service.impl.*.*(..)) && @annotation(jdk.nashorn.internal.runtime.logging.Logger)")
    //   匹配 cn.tulingxueyuan.service.impl包下面的任意类任意方法 并且 方法带了Logger注解的
    //   并且 匹配方法上标记@jdk.nashorn.internal.runtime.logging.Logger的注解，  @annotation(logger)  对应参数上面的名字，
    // 通知上面的参数不是随便能写的 JoinPoint 所以的通知都可以有，其他参数视有效情况而定
    //@Before("pointcut() && @annotation(logger)")
    public void before(JoinPoint joinPoint, Logger logger){
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 所有的参数
        Object[] args = joinPoint.getArgs();

        System.out.println(logger.name()+"方法运行,参数是："+ Arrays.asList(args)+"注解的值是：");
    }

    // 后置通知
    //@After("pointcut()")
    public void after(JoinPoint joinPoint){
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 所有的参数
        Object[] args = joinPoint.getArgs();
        System.out.println(methodName+"方法运行,参数是："+ Arrays.asList(args));
    }
    // 后置异常通知
   // @AfterThrowing(value="pointcut()", throwing="ex")
    public void afterThrowing(Exception ex){
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw, true));
        System.out.println("后置异常通知"+sw.getBuffer().toString());
    }
    // 后置返回通知
    //@AfterReturning(value="pointcut()", returning = "returnValue")
    public void afterReturning(Object returnValue){
        System.out.println("返回值："+returnValue);
    }


    // 环绕
    //@Around("pointcut()")
    public  Object arround(ProceedingJoinPoint joinPoint){
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 所有的参数
        Object[] args = joinPoint.getArgs();

        Object returnValue = null;
        try {
            System.out.println("环绕：前置通知：" + methodName + "方法执行，参数：" + Arrays.asList(args));
            returnValue = joinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("环绕：异常通知：" + throwable);
        } finally {
            System.out.println("环绕：后置通知：" + "参数：" + Arrays.asList(args));
        }
        System.out.println("环绕：后置返回通知：" + methodName + "方法执行，返回值：" + returnValue);
        return returnValue;
    }

}
