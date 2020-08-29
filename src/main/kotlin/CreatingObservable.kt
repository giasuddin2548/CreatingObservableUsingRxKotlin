import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.Callable

class CreatingObservable {

    fun createObservablesWithJust() {
        val obs: Observable<Any> = Observable.just(0, 1, 2, 3, 4, 5, "Nahin")

        val observer: Observer<Any> = object : Observer<Any> {
            override fun onComplete() {
                println("Completed")
            }

            override fun onSubscribe(d: Disposable?) {
                println("Disposable:$d")
            }

            override fun onNext(t: Any?) {
                println("Received item:$t")
            }

            override fun onError(e: Throwable?) {
                println("Error:$e")
            }


        }

        obs.subscribe(observer)


    }

    fun createObservablesFromIterable() {
        val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val obs: Observable<Int> = Observable.fromIterable(list)

        val observer: Observer<Int> = object : Observer<Int> {
            override fun onComplete() {
                println("Completed")
            }

            override fun onSubscribe(d: Disposable?) {
                println("Disposable:$d")
            }

            override fun onNext(t: Int?) {
                println("Received item:$t")
            }

            override fun onError(e: Throwable?) {
                println("Error:$e")
            }


        }


        obs.subscribe(observer)
    }

    fun createObservablesUsingCreate() {
        val observable: Observable<String> = Observable.create<String> {
            it.onNext("Samir")
            it.onNext("Rima")
            it.onNext("Nadia")
            it.onNext("Junaid")
            it.onNext("Najmul")
            it.onComplete()
            // it.onError(Exception("Custom Exception"))
        }


        val observer: Observer<String> = object : Observer<String> {
            override fun onComplete() {
                println("Completed")
            }

            override fun onSubscribe(d: Disposable?) {
                println("Disposable:$d")
            }

            override fun onNext(t: String?) {
                println("Received item:$t")
            }

            override fun onError(e: Throwable?) {
                println("Error:$e")
            }


        }

        observable.subscribe(observer)


    }

    fun createObservableWithFromAndCallable() {
        val observer: Observer<Any> = object : Observer<Any> {
            override fun onComplete() {
                println("All Completed")
            }

            override fun onSubscribe(d: Disposable?) {
                println("Subscription:$d")
            }

            override fun onNext(t: Any?) {
                println("Received item:$t")
            }

            override fun onError(e: Throwable?) {
                println("Error:$e")
            }

        }

        val mylist = listOf<Any>(1, "Samir", 2, "Rima")
        val observable: Observable<Any> = Observable.fromIterable(mylist)
        observable.subscribe(observer)

        val callable = Callable<Any> { "From Callable" }

        val observableFromCallable: Observable<Any> = Observable.fromCallable(callable)

        observableFromCallable.subscribe(observer)


    }

    fun createColdObservable() {
        val observable: Observable<String> = Observable.just("Samir", "Rima", "Nadia")
        observable.subscribe(
            { value -> println(value) },//onNext() method will call
            { error -> println(error) },//onError() method will call
            { println("Completed") })//onComplete() method will call


    }
}