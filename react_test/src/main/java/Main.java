import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.processors.PublishProcessor;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        //test6();
        //test7();
        //test8();
        test9();
    }

    public static void test1() {
        Flowable.just("Hello world").subscribe(System.out::println);

        Flowable<String> source = Flowable.create(emitter -> {

            // signal an item
            emitter.onNext("Hello " + Thread.currentThread());

            // could be some blocking operation
            Thread.sleep(1000);

            // the consumer might have cancelled the flow
            if (emitter.isCancelled()) {
                return;
            }

            emitter.onNext("World " + Thread.currentThread());

            Thread.sleep(1000);

            // the end-of-sequence has to be signaled, otherwise the
            // consumers may never finish
            emitter.onComplete();

        }, BackpressureStrategy.BUFFER);

        Flowable<String> runBackground = source.subscribeOn(Schedulers.io());
        Flowable<String> showForeground = runBackground.observeOn(Schedulers.single());


        Disposable subscribe = showForeground.subscribe(s -> System.out.println(Thread.currentThread() + " : " + s));


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        subscribe.dispose();

    }

    public static void test2() {
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(v -> v * v)
                .doAfterNext(c -> {
                    System.out.println(Thread.currentThread() + " " + c);
                })
                .sequential()
                .blockingSubscribe(System.out::println);
    }

    public static void test3() {
        Flowable.range(1, 10)
                .concatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> w * w)
                                .doOnNext(s -> {
                                    System.out.println(Thread.currentThread() + " : " + s);
                                    TimeUnit.MILLISECONDS.sleep(2);
                                })
                )
                .blockingSubscribe(System.out::println);
    }

    public static void test4() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .ignoreElements()
                .andThen(Observable.just(1, 10, 20))
                .map(v -> v.toString())
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    public static void test5() {
        AtomicInteger count = new AtomicInteger();

        Observable.range(1, 10)
                .doOnNext(ignored -> count.incrementAndGet())
                .ignoreElements()
                // .andThen(Single.just(count.get()))
                .andThen(Single.defer(() -> Single.just(count.get())))
                .subscribe(System.out::println);

        System.out.println(count.get());
    }

    public static void test6() {

        Observable.range(1, 10)
                .map(i -> {
                    if (i % 2 == 0) {
                        throw new RuntimeException();
                    }
                    return i;
                })
                .observeOn(Schedulers.computation(), true, 1024)
                .onErrorReturnItem(0)
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    public static void test7() {
        Observable<Instant> observable = Observable.defer(() -> {
            Instant instant = Instant.now();
            return Observable.just(instant);
        });

        observable.subscribe(System.out::println);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        observable.subscribe(time -> System.out.println(time));
    }

    public static void test8() {
        Observable<String> observable = Observable.fromCallable(() -> {
            if (Math.random() < 0.5) {
                throw new IOException();
            }
            throw new IllegalArgumentException();
        });

        Observable<String> result = observable.onErrorResumeNext(error -> {
            if (error instanceof IllegalArgumentException) {
                return Observable.empty();
            }
            return Observable.error(error);
        });


        for (int i = 0; i < 10; i++) {
            result.subscribe(
                    v -> System.out.println("This should never be printed!"),
                    error -> error.printStackTrace(),
                    () -> System.out.println("Done"));
        }
    }

    public static void test9() {
//        PublishProcessor<Integer> source = PublishProcessor.create();
//
//        source
//                .observeOn(Schedulers.computation())
//                .subscribe(System.out::println, Throwable::printStackTrace);
//
//        for (int i = 0; i < 1_000_000; i++) {
//            source.onNext(i);
//        }
//
//        try {
//            Thread.sleep(10_000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Flowable.range(1, 1_000_000)
//                .observeOn(Schedulers.computation())
//                .subscribe(System.out::println, Throwable::printStackTrace);
//
//        try {
//            Thread.sleep(10_000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//        PublishProcessor<Integer> source = PublishProcessor.create();
//        source
//                .buffer(1024)
//                .observeOn(Schedulers.computation(), true, 1024)
//                .subscribe(System.out::println, Throwable::printStackTrace);
//
//        for (int i = 0; i < 1_000_000; i++) {
//            source.onNext(i);
//        }

        PublishProcessor<Integer> source = PublishProcessor.create();

        source
                .sample(1, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.computation(), true, 1024)
                .subscribe(System.out::println, Throwable::printStackTrace);

        for (int i = 0; i < 1_000_000; i++) {
            source.onNext(i);
        }

    }


}
