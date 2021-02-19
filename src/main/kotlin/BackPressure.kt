import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    val observable = Observable.just(1,2,3,4,5,6,7,8,9) // 1

    val subject = BehaviorSubject.create<Int>()
    subject.observeOn(Schedulers.computation())
        .subscribe {
            println("Subs 1 Received $it")
            runBlocking { delay(200) }
        }

    subject.observeOn(Schedulers.computation())
        .subscribe {
            println("Subs 2 Received $it")
        }

    observable.subscribe(subject)
    runBlocking { delay(2000) }

}