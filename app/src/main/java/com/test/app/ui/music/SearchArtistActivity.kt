package com.test.app.ui.music

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding.widget.RxTextView
import com.test.app.R
import com.test.app.di.ApplicationComponent
import com.test.app.di.MusicApplicationComponent
import com.test.app.di.HasApplicationComponent
import com.test.app.ui.base.BaseActivity
import com.test.app.ui.di.ActivityComponent
import com.test.app.ui.music.MusicViewModel.ViewState
import com.test.app.ui.music.adapters.SearchAdapter
import com.test.app.ui.music.di.MusicActivityComponent
import com.test.app.ui.music.di.MusicActivityModule
import com.test.app.ui.music.model.ArtistViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.loading_layout.*
import javax.inject.Inject

class SearchArtistActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MusicViewModel

    @Inject
    lateinit var adapter: SearchAdapter

    private lateinit var activityComponent: MusicActivityComponent

    private val compositeDisposable = CompositeDisposable()

    override fun inject() {
        activityComponent = (getApplicationComponent() as MusicApplicationComponent).add(MusicActivityModule())
        activityComponent.inject(this)
    }

    override fun getActivityComponent(): ActivityComponent = activityComponent

    override fun getApplicationComponent(): ApplicationComponent =
        (application as HasApplicationComponent).getApplicationComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        observeStates()
        observeSearchTextChanged()
        showContent()
    }

    private fun observeStates() {
        compositeDisposable.add(viewModel.observeSates().subscribe { render(it) })
    }

    private fun render(state: ViewState) {
        when (state) {
            is ViewState.Loading -> showLoading()
            is ViewState.Error -> showError()
            is ViewState.ArtistsLoaded -> onArtistsLoaded(state.artists)
        }
    }

    private fun showLoading() {
        loading_view.visibility = View.VISIBLE
        content_view.visibility = View.GONE
        error_view.visibility = View.GONE
        artists_label.visibility = View.GONE
    }

    private fun showContent(empty: Boolean = true) {
        content_view.visibility = View.VISIBLE
        artists_label.visibility = if (empty) View.GONE else View.VISIBLE
        loading_view.visibility = View.GONE
        error_view.visibility = View.GONE
    }

    private fun onArtistsLoaded(artists: List<ArtistViewModel>) {
        showContent(artists.isEmpty())
        adapter.setItems(artists)
    }

    private fun showError() {
        error_view.visibility = View.VISIBLE
        loading_view.visibility = View.GONE
        content_view.visibility = View.GONE
        artists_label.visibility = View.GONE
    }

    private fun initRecyclerView() {
        content_view.adapter = adapter
        content_view.setHasFixedSize(true)
        content_view.layoutManager = LinearLayoutManager(this)
        adapter.setOnArtistClicked(object : SearchAdapter.OnArtistClicked {
            override fun onClicked(artistViewModel: ArtistViewModel) =
                goToAlbum(artistViewModel)
        })
    }

    private fun goToAlbum(artistViewModel: ArtistViewModel) {
        val intent = Intent(this, AlbumActivity::class.java)
        intent.putExtra(AlbumActivity.DATA_ARTIST_ID, artistViewModel.id.toString())
        intent.putExtra(AlbumActivity.DATA_ARTIST_NAME, artistViewModel.name)
        startActivity(intent)
    }

    private fun observeSearchTextChanged() {
        RxTextView
            .textChanges(search_input)
            .skip(1)
            .map { it.toString() }
            .subscribe { query ->
                viewModel.searchArtists(query)
            }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        viewModel.dispose()
        super.onDestroy()
    }
}