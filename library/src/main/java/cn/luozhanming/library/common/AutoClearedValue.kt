package cn.luozhanming.library.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.reflect.KProperty

class AutoClearedValue<T : Any> {
    private var _value: T? = null


    constructor(fragment: Fragment) {
        fragment.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {

            }
        })
    }

    constructor(activity: AppCompatActivity) {
        activity.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {

            }
        })
    }


    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException(
            "should never call auto-cleared-value get when it might not be available"
        )
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        _value = value
    }
}


fun <T : Any> AppCompatActivity.autoCleared() = AutoClearedValue<T>(this)
fun <T : Any> Fragment.autoCleared() = AutoClearedValue<T>(this)

