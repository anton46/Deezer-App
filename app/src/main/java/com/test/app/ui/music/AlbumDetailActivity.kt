package com.test.app.ui.music

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.app.R
import com.test.app.di.ApplicationComponent
import com.test.app.di.MusicApplicationComponent
import com.test.app.di.HasApplicationComponent
import com.test.app.ui.base.BaseActivity
import com.test.app.ui.di.ActivityComponent
import com.test.app.ui.music.adapters.AlbumDetailAdapter
import com.test.app.ui.music.di.MusicActivityComponent
import com.test.app.ui.music.di.MusicActivityModule
import com.test.app.ui.music.model.AlbumViewModel
import com.test.app.ui.music.model.SongViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_album.*
import kotlinx.android.synthetic.main.activity_album.content_view
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.loading_layout.*
import javax.inject.Inject

class AlbumDetailActivity : BaseActivity() {
    companion object {
        const val DATA_ALBUM = "DATA_ALBUM"
    }

    @Inject
    lateinit var viewModel: MusicViewModel

    @Inject
    lateinit var adapter: AlbumDetailAdapter

    private lateinit var activityComponent: MusicActivityComponent

    private val compositeDisposable = CompositeDisposable()

    override fun inject() { activityComponent =
            (getApplicationComponent() as MusicApplicationComponent).add(MusicActivityModule())
        activityComponent.inject(this)
    }

    override fun getActivityComponent(): ActivityComponent = activityComponent

    override fun getApplicationComponent(): ApplicationComponent =
        (application as HasApplicationComponent).getApplicationComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_detail_activity)
        setupToolbar()
        initRecyclerView()
        observeStates()
        loadAlbums()
    }

    private fun setupToolbar() {
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadAlbums() {
        val album = intent.getSerializableExtra(DATA_ALBUM)
        if (album is AlbumViewModel) {
            adapter.setAlbum(album)
            viewModel.getTracks(album.id.toString())
        } else {
            throw IllegalAccessException("AlbumViewModel is null")
        }
    }

    private fun observeStates() {
        compositeDisposable.add(viewModel.observeSates().subscribe { render(it) })
    }

    private fun render(state: MusicViewModel.ViewState) {
        when (state) {
            is MusicViewModel.ViewState.Loading -> showLoading()
            is MusicViewModel.ViewState.Error -> showError()
            is MusicViewModel.ViewState.TracksLoaded -> onAlbumLoaded(state.tracks)
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

    private fun onAlbumLoaded(tracks: List<SongViewModel>) {
        showContent()
        adapter.setItems(tracks)
    }

    private fun showError() {
        error_view.visibility = View.VISIBLE
        loading_view.visibility = View.GONE
        content_view.visibility = View.GONE
    }

    private fun initRecyclerView() {
        content_view.adapter = adapter
        content_view.setHasFixedSize(true)
        content_view.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroy() {
        viewModel.dispose()
        compositeDisposable.dispose()
        super.onDestroy()
    }
}