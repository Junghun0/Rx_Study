import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

fun main() {
    Observable // 생산자 : 데이터를 생산하여 전달
        .just("Hello!!!", "RxJava~?") // 생성 연산자
        .map { it.dropLast(1) } // 변환 소비자 : 데이터의 가공
        .subscribe(::println) // 소비자 : 데이터를 받아서 처리 (println)

    val observer = object : Observer<Int> {
        override fun onComplete() {
            println("onComplete()")
        }

        override fun onSubscribe(d: Disposable) {
            println("onSubscribe()")
        }

        override fun onNext(t: Int) {
            println("onNext() $t")
        }

        override fun onError(e: Throwable) {
            println("onError()")
        }
    }

    Observable
        .just(2, 4, 6, 8)
        .map { it / 2 }
        .subscribe(observer)

    Observable.create<Int> {emitter ->
        emitter.onNext(100)
        emitter.onNext(200)
        emitter.onNext(300)
        emitter.onNext(400)
        emitter.onComplete()
    }.subscribe(::println)
}