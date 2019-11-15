package com.test.app.test

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen
import com.test.app.mock.MockResponseServerDispatcher
import com.test.app.page.AlbumDetailScreen
import com.test.app.page.AlbumScreen
import com.test.app.page.SearchScreen
import com.test.app.ui.music.SearchArtistActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchArtistsUiTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SearchArtistActivity::class.java, false, false)

    private val mockWebServer = MockWebServer()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockWebServer.start(8080)
        println("URL >>>> " + mockWebServer.url("/"))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSearchArtists() {
        mockWebServer.dispatcher = MockResponseServerDispatcher()
        activityRule.launchActivity(Intent())

        Screen.onScreen<SearchScreen> {
            input {
                replaceText("P")
            }
            artists {
                isVisible()
                firstChild<SearchScreen.SearchItem> {
                    artistName {
                        isVisible()
                        hasText("Daft Punk")
                        click()
                    }
                }
            }
        }

        Screen.onScreen<AlbumScreen> {
            albums {
                isVisible()
                hasSize(2)
                firstChild<AlbumScreen.AlbumItem> {
                    albumName {
                        isVisible()
                        hasText("Random Access Memories")
                        click()
                    }
                }
            }
        }

        Screen.onScreen<AlbumDetailScreen> {
            tracks {
                isVisible()
                hasSize(3)
                childAt<AlbumDetailScreen.TrackItem>(1) {
                    trackName {
                        isVisible()
                        hasText("One More Time")
                    }
                }
            }
        }
    }
}
