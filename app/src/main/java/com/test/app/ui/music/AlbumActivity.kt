package com.test.app.ui.music

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.test.app.R
import com.test.app.di.ApplicationComponent
import com.test.app.di.MusicApplicationComponent
import com.test.app.di.HasApplicationComponent
import com.test.app.ui.base.BaseActivity
import com.test.app.ui.di.ActivityComponent
import com.test.app.ui.music.MusicViewModel.ViewState
import com.test.app.ui.music.adapters.AlbumAdapter
import com.test.app.ui.music.di.MusicActivityComponent
import com.test.app.ui.music.di.MusicActivityModule
import com.test.app.ui.music.model.AlbumViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_album.*
import kotlinx.android.synthetic.main.activity_main.content_view
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.loading_layout.*
import javax.inject.Inject

class AlbumActivity : BaseActivity() {

    companion object {
        const val DATA_ARTIST_NAME = "DATA_ARTIST_NAME"
        const val DATA_ARTIST_ID = "DATA_ARTIST_ID"
    }

    @Inject
    lateinit var viewModel: MusicViewModel

    @Inject
    lateinit var adapter: AlbumAdapter

    private lateinit var activityComponent: MusicActivityComponent

    private val compositeDisposable = CompositeDisposable()

    override fun inject() {
        activityComponent =
            (getApplicationComponent() as MusicApplicationComponent).add(MusicActivityModule())
        activityComponent.inject(this)
    }

    override fun getActivityComponent(): ActivityComponent = activityComponent

    override fun getApplicationComponent(): ApplicationComponent =
        (application as HasApplicationComponent).getApplicationComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        setupToolbar()
        initRecyclerView()
        observeStates()
        loadAlbums()
    }

    private fun setupToolbar() {
        toolbar.title = getString(R.string.albums)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadAlbums() {
        val artistName = intent.getStringExtra(DATA_ARTIST_NAME)
        val artistId = intent.getStringExtra(DATA_ARTIST_ID)
        viewModel.getAlbums(artistId)
        adapter.setArtistName(artistName)
    }

    private fun observeStates() {
        compositeDisposable.add(viewModel.observeSates().subscribe { render(it) })
    }

    private fun render(state: ViewState) {
        when (state) {
            is ViewState.Loading -> showLoading()
            is ViewState.Error -> showError()
            is ViewState.AlbumsLoaded -> onAlbumLoaded(state.albums)
        }
    }

    private fun showLoading() {
        loading_view.visibility = View.VISIBLE
        content_view.visibility = View.GONE
        error_view.visibility = View.GONE
    }

    private fun showContent() {
        content_view.visibility = View.VISIBLE
        loading_view.visibility = View.GONE
        error_view.visibility = View.GONE
    }

    private fun onAlbumLoaded(model: List<AlbumViewModel>) {
        showContent()
        adapter.setItems(model)
    }

    private fun showError() {
        error_view.visibility = View.VISIBLE
        loading_view.visibility = View.GONE
        content_view.visibility = View.GONE
    }

    private fun initRecyclerView() {
        content_view.adapter = adapter
        content_view.setHasFixedSize(true)
        content_view.layoutManager = GridLayoutManager(this, 2)
        adapter.setOnAlbumClickListener(object : AlbumAdapter.OnAlbumClicked {
            override fun onClicked(album: AlbumViewModel) {
                goToTracks(album)
            }
        })
    }

    private fun goToTracks(albumViewModel: AlbumViewModel) {
        val intent = Intent(this, AlbumDetailActivity::class.java)
        intent.putExtra(AlbumDetailActivity.DATA_ALBUM, albumViewModel)
        startActivity(intent)
    }


    override fun onDestroy() {
        viewModel.dispose()
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
