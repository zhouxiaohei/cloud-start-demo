package com.cloud.demo.hystrix;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @description:
 * @date: 10:26 2018/10/31
 */
@Slf4j
public class ObservableDemo {

    /**
     *  简单的rxjava demo  观察者模式
     */
    public static void rxjavaDemo(){

        // 创建事件源 即被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello Rxjava");
                subscriber.onNext("who am i");
                subscriber.onCompleted();
            }
        });

        // 创建订阅者 即观察着
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println(" Completed subscriber");
            }

            @Override
            public void onError(Throwable throwable) {
                log.error(" error subscriber");
            }

            @Override
            public void onNext(String s) {
                log.info("subscriber : {}", s);
            }
        };

        //订阅
        observable.subscribe(subscriber);

    }


    /**
     *  HystrixCommand执行的execute/queue  同步/异步
     *
     *  queue执行的就是toObservable() 返回Future对象
     *  execute是直接通过Future得到结果
     */
    public static void hystrixCommandDemo(String input, int type){
        HystrixCommandDemo command = new HystrixCommandDemo(input);
        String result = null;
        if(type == 1){
            result = command.execute(); // this.queue().get();
        }else if(type == 2){
            Future<String> queue = command.queue(); // this.toObservable().toBlocking().toFuture();
            while (!queue.isDone()){
                log.info("Do other things ...");
            }
            try {
                result = queue.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }else{
            result = testObserve(command, type);
        }
        log.info("执行结果：{}", result);
    }

    /**
     * 虽然HystrixCommand具备了observe()和toObservable()的功能，但是它的实现有一定的局限性，只能执行一个命令，所以只会有一个结果
     * 而Hystrix还提供了HystrixObservableCommand, 通过它实现的命令可以执行多个command
     *
     */
    public static String testObserve(HystrixCommandDemo command, int type){
        /**
         * 返回的是Hot Observable,不论 "事件源" 是否有"订阅者",都会在创建后对事件进行发布。
         * 所以对于Hot Observable的每一个“订阅者”都有可能从“事件源”的中途开始的，并可能只是看到了整个操作的局部过程
         */

        String result = null;
        if(type == 3){
            Observable<String> observe = command.observe();
            observe.subscribe(new Observer<String>() {
                @Override
                public void onCompleted() {
                    log.info("==============onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(String s) {
                    log.info("=========onNext: {}", s);
                }
            });


            observe.subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    log.info("==================call: {}", s);
                }
            });

            result = observe.toBlocking().single();

        }else if(type == 4){
            /**
             *  Cold Observable在没有 “订阅者” 的时候并不会发布事件，而是进行等待，直到有 “订阅者” 之后才发布事件
             *  所以对于Cold Observable的订阅者，它可以保证从一开始看到整个操作的全部过程
             */
            Observable<String> observable = command.toObservable();
            observable.subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    log.info("==================call: {}", s);
                }
            });
            result = observable.toBlocking().single();
        }

        return result;
    }


    public static void hystrixObservableCommandDemo(String input, int type){
        HystrixObservableCommandDemo hystrixObservableCommandDemo = new HystrixObservableCommandDemo(input);
        if(type == 1){
            Observable<String> observe = hystrixObservableCommandDemo.observe();
            Iterator<String> iterator = observe.toBlocking().getIterator();
            while(iterator.hasNext()){
                log.info("hystrixObservableCommand observe :{}", iterator.next());
            }

        }else if(type == 2){
            Observable<String> observable = hystrixObservableCommandDemo.toObservable();
            Iterator<String> iterator = observable.toBlocking().getIterator();
            while(iterator.hasNext()){
                log.info("hystrixObservableCommand toObserve : {}", iterator.next());
            }
        }

    }


    public static void main(String[] args) {
        //rxjavaDemo();  //运行rxJavaDemo
        //hystrixCommandDemo("呵呵呵哒", 3);
        //hystrixObservableCommandDemo("呵呵呵哒", 2);
    }


}

/**
 *
 *   cloud书中伪代码
 *   描述客户端---调用者---hystrix command之间的调用关系
 *
 */

//接收者/服务方
class Receiver{
    public void action(){

    }
}

//抽象命令
interface Command{
    void execute();
}

//具体命令实现类
class ConcreteCommand implements Command{

    private Receiver receiver;

    public ConcreteCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.action();
    }
}

//客户端调用
class Invoker{

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void action(){
        command.execute();
    }
}

class Client{
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.action();
    }
}
