package com.test.app.mock

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


class MockResponseServerDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        request.path?.let {
            if (it.contains("search/artist".toRegex()))
                return MockResponse().setResponseCode(200).setBody(Mocks().mockArtistResponse())
            if (it.contains("/albums".toRegex()))
                return MockResponse().setResponseCode(200).setBody(Mocks().mockAlbumResponse())
            if (it.contains("/tracks".toRegex()))
                return MockResponse().setResponseCode(200).setBody(Mocks().mockTrackResponse())
        }
        return MockResponse().setResponseCode(404)
    }
}