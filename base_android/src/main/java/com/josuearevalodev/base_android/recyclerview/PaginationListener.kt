package com.josuearevalodev.base_android.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    abstract val firstPage: Int
    abstract val itemsPerPage: Int
    abstract val isLastPage: Boolean
    abstract val isLoading: Boolean
    protected abstract fun loadMoreItems()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount
        val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                firstVisibleItemPosition >= 0 &&
                totalItemCount >= itemsPerPage) {
                loadMoreItems()
            }
        }
    }
}
