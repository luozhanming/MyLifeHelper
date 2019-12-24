package cn.luozhanming.github.vo

import androidx.annotation.IntDef

data class Pager<T>(val curPage: Int = 0, @PageState val pageState: Int, val datas: List<T>?)

fun <T> getEmptyPage(clazz: Class<T>) = Pager<T>(0, 0, null)

/*从未加载过*/
const val PAGE_STATE_NEVER = 2001
/*加载成功*/
const val PAGE_STATE_SUCCESS = 2002
/*加载失败*/
const val PAGE_STATE_FAILED = 2003
/*无更多数据*/
const val PAGE_STATE_NO_MORE = 2004

@IntDef(PAGE_STATE_NEVER, PAGE_STATE_SUCCESS, PAGE_STATE_FAILED, PAGE_STATE_NO_MORE)
annotation class PageState
