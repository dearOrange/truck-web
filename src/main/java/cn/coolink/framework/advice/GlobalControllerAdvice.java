package cn.coolink.framework.advice;

import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Title: truck-platform
 * @Package: cn.coolink.advice
 * @Description:
 * @author: zfk
 * @date 2018/8/8 11:21
 */
@Aspect
@Component
public class GlobalControllerAdvice {

    //定义日志模板
    private static final String LOGGRE_EXCEPTION_FORMAT = "Catch Exception：" +
            "Code:%; Message:%";
    //定义静态日志对象
    private static Logger logger =LoggerFactory.getLogger(GlobalControllerAdvice.class);

    /**
     * 定义切点
     * （拦截规则：拦截cn.coolink.controller包下面的所有类中，有@RequestMapping注解的方法。）
     * @author: zfk
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut(){}

    /**
     * 拦截器具体实现(环绕增强)
     * @param joinPoint
     * @return ResultBean（被拦截方法的执行结果，或需要登录的错误提示。）
     * @author: zfk
     */
    @Around("controllerMethodPointcut()") //指定拦截器规则（也可以直接把切点表达式写进这里）
    public Object Interceptor(ProceedingJoinPoint joinPoint){
        Object result = null;
        long startTime = System.currentTimeMillis();
        try {
            beforeHander(joinPoint);
            result = joinPoint.proceed();
            afterHander(joinPoint);
        } catch (Throwable e) {
            e.printStackTrace();
            Class returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();
            if(returnType.equals(ResultBean.class)){
                result =  ResultBean.buildException(e.getMessage(),"系统内部错误");
            }else{
                result = PageResultBean.buildException(e.getMessage(),"系统内部错误");
            }

        }
        logger.info(joinPoint.getSignature() + "调用结束，时长：" + (System.currentTimeMillis() - startTime)+"ms");
        return result;
    }

    /**
     * 在方法之前调用（虚拟前置增强）
     * @param joinPoint
     * @return
     */
    private Object beforeHander(ProceedingJoinPoint joinPoint){
        //todo
        return null;
    }

    /**
     * 在方法之后调用（虚拟后置增强）
     * @param joinPoint
     * @return
     */
    private Object afterHander(ProceedingJoinPoint joinPoint){
        //todo
        return null;
    }

    /*private ResultBean<?> resultBeanEexceptionHandler(ProceedingJoinPoint joinPoint, Throwable e){
        logger.error("发生异常！！！ 对应方法为："+joinPoint.getSignature());
        e.printStackTrace();
        if(e.getClass().equals(AlertException.class)){
            return ResultBean.buildAlert(new ErrorMsg().setErrMessage(e.getMessage()));
        }
        return ResultBean.build(new ErrorMsg().setErrMessage(e.getMessage()));
    }*/

    /*private PageResultBean<?> pageResultBeanEexceptionHandler(ProceedingJoinPoint joinPoint, Throwable e){
        logger.error("发生异常！！！ 对应方法为："+joinPoint.getSignature());
        e.printStackTrace();
        if(e.getClass().equals(AlertException.class)){
            return PageResultBean.buildAlert(new ErrorMsg().setErrMessage(e.getMessage()));
        }
        return PageResultBean.build(new ErrorMsg().setErrMessage(e.getMessage()));
    }*/
}
