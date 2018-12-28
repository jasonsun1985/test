package com.tec.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ErrorExceptionAspect {

    //@Before("execution(* com.tec.aop..*.*(..))")
    @Pointcut(value = "@annotation(com.tec.aop.ErrorException)")
    private void pointcut() {
    }

    @Around(value = "pointcut() && @annotation(errorExecption)")
    public Object around(ProceedingJoinPoint point, ErrorException errorExecption){
        System.out.println("---->around");
        //注解参数
        System.out.println("注解参数："+ errorExecption.code());
        //当前拦截的类和方法：
        Class clazz = point.getTarget().getClass();
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        String codeName = clazz.getSimpleName()+"_"+method.getName();
        System.out.println("query param---->"+codeName);

        //方法返回结果
        Object result = null;
        Object args = Arrays.asList(point.getArgs());
        try {
            //执行方法（可以在方法前后添加前置和后置通知）
            result = point.proceed();
            //校验结果
            result = validateResult(result);
        } catch (Throwable e) {
            //记录日志
            System.out.println(codeName + "()方法异常：" + e);
            //打印堆栈信息
            e.printStackTrace();
            //设置返回信息
            result = "结果：抛了异常了。。-----------------------"+e.getMessage()+"，原因："+e.getCause();
        }
        //返回通知
        return result;

    }

    /**
     * 方法执行后
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "pointcut() && @annotation(errorExecption)", returning = "result")
    public Object afterReturning(JoinPoint joinPoint, ErrorException errorExecption,  Object result){
        System.out.println("---->afterReturning");
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " return with " + result);
        if(result instanceof Boolean){
            if(!((Boolean) result)){
                result = "error----result is false";
            }
        }else{
            if(result == null){
                result = "error----result is null";
            }
        }
        return result;
    }
    /**
     * 方法执行后
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "pointcut() && @annotation(errorExecption)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, ErrorException errorExecption, Exception ex){
        System.out.println("eeeee--->afterThrowing");
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + "occurs exception: " + ex);
    }

    private Object validateResult(Object result){
        if(result instanceof Boolean){
            if(!((Boolean) result)){
                System.out.println("error----result is false");
                result = "error:false";
            }
        }else{
            if(result == null){
                System.out.println("error----result is null");
                result = "error:null";
            }
        }
        return result;
    }
}