package com.springboot.framework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * >@Aspect:作用是把当前类标识为一个切面供容器读取
 * >
 * >@Pointcut：Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
 * >@Around：环绕增强，相当于MethodInterceptor
 * >@Before：标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
 * >@AfterThrowing：异常抛出增强，相当于ThrowsAdvice
 * >@After: final增强，不管是抛出异常或者正常退出都会执行
 * >@AfterReturning：后置增强，相当于AfterReturningAdvice，方法正常退出时执行
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/19 1:30
 */
@Component
@Aspect
public class AdviceAop {
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.springboot.framework.controller.AdminController.*(..))";

    /**
     * 切点
     * 配置需要添加切面通知的包路径
     */
    @Pointcut(AOP_POINTCUT_EXPRESSION)
    public void pointCut() {
    }

//    /**
//     * 环绕通知：
//     * 注意:Spring AOP的环绕通知会影响到AfterThrowing通知的运行,不要同时使用
//     * <p>
//     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
//     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
//     *
//     * @param point 切点
//     */
//    @Around(value = "pointCut()")
//    public Object process(ProceedingJoinPoint point) throws Throwable {
//        System.out.println("@Around：执行目标方法之前...");
//        //访问目标方法的参数：
//        Object[] args = point.getArgs();
//        if (args != null && args.length > 0 && args[0].getClass() == String.class) {
//            args[0] = "改变后的参数1";
//        }
//        //用改变后的参数执行目标方法
//        Object returnValue = point.proceed(args);
//        System.out.println("@Around：执行目标方法之后...");
//        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
//        return "原返回值：" + returnValue + "，这是返回结果的后缀";
//    }

    /**
     * 前置通知
     *
     * @param point 切点
     */
    @Before(value = "pointCut()")
    public void permissionCheck(JoinPoint point) {
        System.out.println("@Before：模拟权限检查...");
        System.out.println("@Before：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
    }

    /**
     * 后置通知
     *
     * @param point 切点
     */
    @After(value = "pointCut()")
    public void releaseResource(JoinPoint point) {
        System.out.println("@After：模拟释放资源...");
        System.out.println("@After：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@After：被织入的目标对象为：" + point.getTarget());
    }

    /**
     * 后置返回
     * 如果第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法参数类型匹配时才能执行后置返回通知，否则不执行，
     * 参数为Object类型将匹配任何目标返回值
     *
     * @param point       切点
     * @param returnValue 返回值
     */
    @AfterReturning(value = "pointCut()", returning = "returnValue")
    public void log(JoinPoint point, Object returnValue) {
        System.out.println("@AfterReturning：模拟日志记录功能...");
        System.out.println("@AfterReturning：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@AfterReturning：参数为：" +
                Arrays.toString(point.getArgs()));
        System.out.println("@AfterReturning：返回值为：" + returnValue);
        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
    }
}
