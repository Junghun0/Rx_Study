import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.Observable.range
import io.reactivex.functions.Function
import java.util.*

fun main() {
    //일반적인 자바를 사용한 구구단
    val scanner = Scanner(System.`in`)
    println("Gugudan Java Sample")
    val dan = scanner.nextLine().toInt()

    //1. 일반적 for 문
    for (row in 1..9) {
        println("$dan * $row = ${dan * row}")
    }

    println()

    //2. for 문의 Observable 변환
    val source: Observable<Int> = range(1, 9)
    source.subscribe { row ->
        println("$dan * $row = ${dan * row}")
    }

    println()

    //3. 사용자 함수 정의하기
    val gugudan: Function<Int, Observable<String>> = Function { num ->
        range(1, 9).map { row ->
            "$num * $row = ${num * row}"
        }
    }
    val source2 = Observable.just(dan).flatMap(gugudan)
    source2.subscribe(System.out::println)

    //4. flatMap() 함수 활용
    val source3: Observable<String> = Observable.just(dan)
            .flatMap { num ->
                range(1, 9).map { row ->
                    "$num * $row = ${dan * row}"
                }
            }
}