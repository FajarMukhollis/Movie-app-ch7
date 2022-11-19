package com.fajar.movieapp.data.utils

import com.fajar.movieapp.data.network.model.popular.Popular
import com.fajar.movieapp.data.network.model.popular.PopularItem
import com.fajar.movieapp.data.network.model.search.Search
import com.fajar.movieapp.data.network.model.search.SearchItem

object DummyData {

    fun generateDummyPopularMovies(): Popular {
        val dummyPopularList = ArrayList<PopularItem>()
        repeat(10) {
            val popular = PopularItem(
                title = "Avatar"
            )
            dummyPopularList.add(popular)
        }
        return Popular(results = dummyPopularList)
    }

    fun generateSearchResponse(query: String): Search {
        val result = ArrayList<SearchItem>()
        if (query.isNotEmpty()) {
            val searchItem = SearchItem(title = "Spiderman")
            result.add(searchItem)
        }
        return Search(results = result)
    }
}