package cn.luozhanming.library.di

interface ComponentBuilder<T> {
    fun build(): T
}