# AspectJ 详解

* [Android AspectJ 详解](https://juejin.im/post/5d7a049af265da03d1557f42)
* [AspectJX](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx)

## AspectJX

### 集成 AspectJX

* 工程根目录添加 plugin

```gradle
classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.8'
```

* 应用主模块添加 plugin

```gradle
apply plugin: 'android-aspectjx'
```

* 应用主模块或者 Library 模块添加依赖

```gradle
api 'org.aspectj:aspectjrt:1.8.14'
```

### AspectJX 存在的问题

* 莫名其妙出现类找不到的问题，导致应用闪退。
* 如果同时集成 Hugo，也无法兼容。

## 应用场景

### Activity 生命周期方法耗时监控

```java
@Aspect
public class ActivityAspect {

  @Pointcut("execution(* android.app.Activity.onCreate(..))")
  public void activityOnCreate() {}

  @Pointcut("execution(* android.app.Activity.onResume(..))")
  public void activityOnResume() {}

  @Pointcut("execution(* android.app.Activity.onStart(..))")
  public void activityOnStart() {}

  @Around("activityOnCreate() || activityOnResume() || activityOnStart()")
  public void logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
    long startNanos = System.nanoTime();
    joinPoint.proceed();
    long stopNanos = System.nanoTime();
    long lengthMillis = TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos);

    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    String declaringClassName = methodSignature.getMethod().getDeclaringClass().getName();
    String methodName = methodSignature.getName();
    String message = String.format("%1$8s    %2$-20s %3$s.%4$s", "[" + lengthMillis + "ms]", className, declaringClassName, methodName);
    Logger.d("aspectj: " + message);
  }

}
```
