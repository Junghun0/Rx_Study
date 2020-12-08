import io.reactivex.Observable
import io.reactivex.Observable.range
import java.util.*

fun main() {
    //일반적인 자바를 사용한 구구단
    val scanner = Scanner(System.`in`)
    println("Gugudan Java Sample")
    val dan = scanner.nextLine().toInt()

    //1. 일반적 for 문
    for (row in 1..9) {
        println("$dan * $row = ${dan*row}")
    }

    println()

    //2. for 문의 Observable 변환
    val source : Observable<Int> = range(1, 9)
    source.subscribe { row ->
        println("$dan * $row = ${dan*row}")
    }
}