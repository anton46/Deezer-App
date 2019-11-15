package com.test.app.page

import android.content.ClipData
import android.view.View
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.test.app.R
import org.hamcrest.Matcher

class SearchScreen : Screen<SearchScreen>() {
    val input: KEditText = KEditText { withId(R.id.search_input) }
    val artists: KRecyclerView = KRecyclerView({
        withId(R.id.content_view)
    }, itemTypeBuilder = {
        itemType(::SearchItem)
    })

    class SearchItem(parent: Matcher<View>) : KRecyclerItem<ClipData.Item>(parent) {
        val artistName: KTextView = KTextView(parent) { withId(R.id.artist_name) }
    }
}

class AlbumScreen : Screen<AlbumScreen>() {
    val albums: KRecyclerView = KRecyclerView({
        withId(R.id.content_view)
    }, itemTypeBuilder = {
        itemType(::AlbumItem)
    })

    class AlbumItem(parent: Matcher<View>) : KRecyclerItem<ClipData.Item>(parent) {
        val albumName: KTextView = KTextView(parent) { withId(R.id.album_name) }
    }
}

class AlbumDetailScreen : Screen<AlbumDetailScreen>() {
    val tracks: KRecyclerView = KRecyclerView({
        withId(R.id.content_view)
    }, itemTypeBuilder = {
        itemType(::TrackItem)
    })

    class TrackItem(parent: Matcher<View>) : KRecyclerItem<ClipData.Item>(parent) {
        val trackName: KTextView = KTextView(parent) { withId(R.id.track_name) }
    }
}