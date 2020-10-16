# RxKotlin Study Repository 👍

## 1. Observable Class
Observable 클래스는 데이터의 변화가 발생하는 데이터 소스이다. Observable 은 Observer Pattern 을 구현한다.<br/>
Observer Pattern 은 객체의 상태 변화를 관찰하는 Observer 목록을 객체에 등록한다. 그리고 상태 변화가 있을 때마다 메서드를 호출하여 객체가 직접 목록의 각 Observer 에 변화를 알려준다.<br/>
라이프 사이클은 존재하지 않으며 보통 단일 함수를 통해 변화만 알린다.<br/>
안드로이드 프레임워크에서 사용자가 버튼을 누르면 버튼에 미리 등록해 둔 onClick() 메서드를 호출해 원하는 처리를 하는 것을 예로 들 수 있다.<br/>
**RxJava 의 Observable 은 세 가지 알림을 구독자에게 전달한다.**
- **onNext** : Observable 이 데이터의 발행을 알린다.
- **onComplete** : 모든 데이터의 발행을 완료했음을 알린다. OnComplete 이벤트는 단 한번만 발생하며, 발생한 후에는 더 이상 onNext 이벤트가 발생해서는 안된다.
- **onError** : Observable 에서 어떤 이유로 에러가 발생했음을 알린다. onError 이벤트가 발생하면 이후에 onNext 및 onComplete 이벤트가 발생하지 않는다. *즉, Observable 의 실행을 종료한다.*

| 팩토리 함 | 함수 |
|---|:---:|
| `RxJava 2.x 추가 팩토리 함수` | fromArray(), fromIterable(), fromCallable(), fromFuture(), fromPublisher() | 
| `기타 팩토리 함수` | interval(), range(), timer(), defer()  |

1. Observer 방식<br/>
Observer 인터페이스를 구현한 객체를 subscribe 해서 소비자를 추가한다.
```kotlin
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
```
결과
```kotlin
onSubscribe()
onNext() 1
onNext() 2
onNext() 3
onNext() 4
onComplete()
```
- just() 함수
데이터를 발행하는 가장 쉬운 방법은 기존의 자료구조를 사용하는 것이다. **just()** 함수는 인자로 넣은 데이터를 차례로 발행하려고 *Observable 을 생성*한다.<br/>
한 개의 값을 넣을 수도 있고 인자로 여러 개의 값(최대 10개)을 넣을 수도 있다. 단, *타입은 모두 같아야 한다.*<br/>
 


