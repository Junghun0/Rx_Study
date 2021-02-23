import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.toFlowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.intellij.lang.annotations.Flow
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit

fun main() {
//    val observable = Observable.just(1,2,3,4,5,6,7,8,9) // 1
//
//    val subject = BehaviorSubject.create<Int>()
//    subject.observeOn(Schedulers.computation())
//        .subscribe {
//            println("Subs 1 Received $it")
//            runBlocking { delay(200) }
//        }
//
//    subject.observeOn(Schedulers.computation())
//        .subscribe {
//            println("Subs 2 Received $it")
//        }
//
//    observable.subscribe(subject)
//    runBlocking { delay(2000) }

//    Flowable.interval(1, TimeUnit.SECONDS)
//            .observeOn(Schedulers.io())
//            .subscribe {
//                runBlocking { delay(5) }
//                println("Received Item $it")
//            }
//
//    runBlocking { delay(10000) }

//    Observable.range(1, 1000)
//            .map { MyItem(it) }
//            .observeOn(Schedulers.io())
//            .subscribe {
//                println("Received $it")
//                runBlocking { delay(50) }
//            }
//    runBlocking { delay(600000) }

//    Flowable.range(1, 15)
//            .map { MyItem(it) }
//            .observeOn(Schedulers.io())
//            .subscribe(object: Subscriber<MyItem> {
//                lateinit var subscription: Subscription
//                override fun onSubscribe(subscription: Subscription) {
//                    this.subscription = subscription
//                    subscription.request(5)
//                }
//
//                override fun onComplete() {
//                    println("Done!")
//                }
//
//                override fun onNext(t: MyItem?) {
//                    runBlocking { delay(50) }
//                    println("Subscriber received $t")
//                    if (t?.id == 5) {
//                        println("Request two more")
//                        subscription.request(2)
//                    }
//                }
//
//                override fun onError(t: Throwable?) {
//                    t?.printStackTrace()
//                }
//            })
//    runBlocking { delay(10000) }



//    val subscriber: Subscriber<Int> = object : Subscriber<Int> {
//        override fun onComplete() {
//            println("All Complete")
//        }
//
//        override fun onSubscribe(s: Subscription?) {
//            println("New Subscription")
//            s?.request(10)
//        }
//
//        override fun onNext(t: Int?) {
//            println("Next $t")
//        }
//
//        override fun onError(t: Throwable?) {
//            println("Error!! ${t?.message}")
//        }
//    }
//
//    val flowable = Flowable.create<Int>( {
//        for (i in 1..10) {
//            it.onNext(i)
//        }
//        it.onComplete()
//    }, BackpressureStrategy.BUFFER)
//
//    flowable.subscribe(subscriber)
//
//    runBlocking { delay(10000) }

//    val source = Observable.range(1, 1000)
//    source.toFlowable(BackpressureStrategy.MISSING)
//            .onBackpressureBuffer()
//            .map { MyItem(it) }
//            .observeOn(Schedulers.io())
//            .subscribe {
//                print("Received $it \t")
//                runBlocking { delay(1000) }
//            }
//    runBlocking { delay(100000) }


//    val source = Observable.range(1, 1000)
//    source.toFlowable(BackpressureStrategy.MISSING)
//            .onBackpressureDrop{ println("Dropped $it")}
//            .map { MyItem(it) }
//            .observeOn(Schedulers.io())
//            .subscribe {
//                print("Received $it \t")
//                runBlocking { delay(1000) }
//            }
//    runBlocking { delay(100000) }


//    val flowable = Flowable.generate<Int> {
//        it.onNext(GenerateFlowableItem.item)
//    }
//
//    flowable
//            .map { MyItem(it) }
//            .observeOn(Schedulers.io())
//            .subscribe {
//                runBlocking { delay(100) }
//                println("Next $it")
//            }
//    runBlocking { delay(700000) }

//    val connectableFlowable = listOf("1", "2", "3", "4", "5", "6").toFlowable().publish()
//    connectableFlowable.
//            subscribe {
//                println("Subscription 1 값-> $it")
//                runBlocking { delay(1000) }
//                println("Subscription 1 delay")
//            }
////    connectableFlowable.connect()
////    runBlocking { delay(1000) }
//    connectableFlowable.
//            subscribe {
//                println("Subscription 2 값-> $it")
//                runBlocking { delay(1200) }
//                println("Subscription 2 delay")
//            }
//    connectableFlowable.connect()


//    val connectableFlowable = listOf(1, 2, 3, 4, 5).toFlowable()
//    val publishPrecessor = PublishProcessor.create<Int>()
//
//    publishPrecessor.subscribe {
//        println("Subscription 1 -> $it")
//        runBlocking { delay(1000) }
//        println("Subscription 1 delay")
//    }
//
//    publishPrecessor.subscribe {
//        println("Subscription 2-> $it")
//    }
//
//    connectableFlowable.subscribe(publishPrecessor)


//    val flowable = Flowable.range(1, 111)
//    flowable.buffer(10, 15)
//            .subscribe {
//                println(it)
//            }

//    val flowable = Flowable.interval(1, TimeUnit.SECONDS)
//    flowable.buffer(3, TimeUnit.SECONDS)
//            .subscribe {
//                println(it)
//            }
//    runBlocking { delay(10000)

//    val flowable = Flowable.range(1, 111)
//    flowable.window(10).subscribe {
//                flowableInstance -> flowableInstance.subscribe {
//                print("$it ,")
//            }
//                println()
//    }

    val flowable = Flowable.interval(1, TimeUnit.SECONDS)
    flowable.throttleFirst(2, TimeUnit.SECONDS)
            .subscribe { println(it) }
    runBlocking { delay(10000) }


}

data class MyItem(val id: Int) {
    init {
        println("MyItem Created $id")
    }
}

object GenerateFlowableItem {
    var item = 0
    get() {
        field += 1
        return field
    }
}