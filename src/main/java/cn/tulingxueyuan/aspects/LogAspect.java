package cn.tulingxueyuan.aspects;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Aspect    // 标记为切面
@Component // 标记Bean组件， 才能切入到ioc当中的bean
public class LogAspect {

  // 可以采用 声明切点的方式 让其他通知引用， 更重用性
  @Pointcut("execution(* cn.tulingxueyuan.service.impl.UserServiceImpl.*(..))")
  public void pointcut() {

  }

  // 前置通知
  // @Before("execution(* cn.tulingxueyuan.service.impl.*.*(..)) && @annotation(jdk.nashorn.internal.runtime.logging.Logger)")
  //   匹配 cn.tulingxueyuan.service.impl包下面的任意类任意方法 并且 方法带了Logger注解的
  //   并且 匹配方法上标记@jdk.nashorn.internal.runtime.logging.Logger的注解，  @annotation(logger)  对应参数上面的名字，
  // 通知上面的参数不是随便能写的 JoinPoint 所以的通知都可以有，其他参数视有效情况而定
  @Before("pointcut() && @annotation(logger)")
  public void before(JoinPoint joinPoint, Logger logger) {
    // 获取方法名
    String methodName = joinPoint.getSignature().getName();
    // 所有的参数
    Object[] args = joinPoint.getArgs();

    System.out.println(
        methodName + " 前置通知,参数是：" + Arrays.asList(args) + "注解的值是：" + logger.name());
  }

  // 后置通知
  @After("pointcut()")
  public void after(JoinPoint joinPoint) {
    // 获取方法名
    String methodName = joinPoint.getSignature().getName();
    // 所有的参数
    Object[] args = joinPoint.getArgs();
    System.out.println(methodName + " 后置通知,参数是：" + Arrays.asList(args));
  }

  // 后置异常通知
  @AfterThrowing(value = "pointcut()",
      throwing = "ex")
  public void afterThrowing(Exception ex) {
    StringWriter sw = new StringWriter();
    ex.printStackTrace(new PrintWriter(sw, true));
    System.out.println("后置异常通知" + sw.getBuffer().toString());
  }

  // 后置返回通知
  @AfterReturning(value = "pointcut()",
      returning = "returnValue")
  public void afterReturning(JoinPoint joinPoint, Object returnValue) {
    String methodName = joinPoint.getSignature().getName();
    System.out.println(methodName + " 后置返回通知：" + returnValue);
  }


  // 环绕
  @Around("execution(* cn.tulingxueyuan.service.impl.UseServiceImpl2.*(..))")
  public Object around(ProceedingJoinPoint joinPoint) {
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
