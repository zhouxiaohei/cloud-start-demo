package com.cloud.demo.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @description:
 * @date: 19:05 2018/11/1
 */
public class HystrixObservableCommandDemo extends HystrixObservableCommand<String> {

    private String input;

    protected HystrixObservableCommandDemo(String input) {
        super(HystrixCommandGroupKey.Factory.asKey("HystrixObservableCommandDemoGroup"));
        this.input = input;
    }

    /**
     *  　　HystrixObservable通过实现 protected Observable<String> construct() 方法来执行逻辑。
     *      通过 重写 resumeWithFallback方法来实现服务降级
     */
    @Override
    protected Observable construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if(!subscriber.isUnsubscribed()) {
                        subscriber.onNext("Hello world ");
                        //int i = 1 / 0; //模拟异常
                        subscriber.onNext("输入了啥：" + input);
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 服务降级
     */
    @Override
    protected Observable<String> resumeWithFallback() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext("为什么就到fallback了！");
                        subscriber.onNext("找出异常，找出异常！");
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
