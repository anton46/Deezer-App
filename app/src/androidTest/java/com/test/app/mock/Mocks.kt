package com.test.app.mock

class Mocks {

    fun mockArtistResponse() = "{\n" +
            "   \"data\":[\n" +
            "{\n" +
            "  \"id\": \"27\",\n" +
            "  \"name\": \"Daft Punk\",\n" +
            "  \"link\": \"https://www.deezer.com/artist/27\",\n" +
            "  \"share\": \"https://www.deezer.com/artist/27?utm_source=deezer&utm_content=artist-27&utm_term=0_1573826636&utm_medium=web\",\n" +
            "  \"picture\": \"https://api.deezer.com/artist/27/image\",\n" +
            "  \"picture_small\": \"https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/56x56-000000-80-0-0.jpg\",\n" +
            "  \"picture_medium\": \"https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/250x250-000000-80-0-0.jpg\",\n" +
            "  \"picture_big\": \"https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/500x500-000000-80-0-0.jpg\",\n" +
            "  \"picture_xl\": \"https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/1000x1000-000000-80-0-0.jpg\",\n" +
            "  \"nb_album\": 30,\n" +
            "  \"nb_fan\": 3724299,\n" +
            "  \"radio\": true,\n" +
            "  \"tracklist\": \"https://api.deezer.com/artist/27/top?limit=50\",\n" +
            "  \"type\": \"artist\"\n" +
            "}" +
            "   ]\n" +
            "}"

    fun mockAlbumResponse() = "{\n" +
            "   \"data\":[\n" +
            "      {\n" +
            "         \"id\":\"6575789\",\n" +
            "         \"title\":\"Random Access Memories\",\n" +
            "         \"link\":\"https://www.deezer.com/album/6575789\",\n" +
            "         \"cover\":\"https://api.deezer.com/album/6575789/image\",\n" +
            "         \"cover_small\":\"https://e-cdns-images.dzcdn.net/images/cover/b298094528702627877720d0be4448b5/56x56-000000-80-0-0.jpg\",\n" +
            "         \"cover_medium\":\"https://e-cdns-images.dzcdn.net/images/cover/b298094528702627877720d0be4448b5/250x250-000000-80-0-0.jpg\",\n" +
            "         \"cover_big\":\"https://e-cdns-images.dzcdn.net/images/cover/b298094528702627877720d0be4448b5/500x500-000000-80-0-0.jpg\",\n" +
            "         \"cover_xl\":\"https://e-cdns-images.dzcdn.net/images/cover/b298094528702627877720d0be4448b5/1000x1000-000000-80-0-0.jpg\",\n" +
            "         \"genre_id\":106,\n" +
            "         \"fans\":737516,\n" +
            "         \"release_date\":\"2013-05-17\",\n" +
            "         \"record_type\":\"album\",\n" +
            "         \"tracklist\":\"https://api.deezer.com/album/6575789/tracks\",\n" +
            "         \"explicit_lyrics\":false,\n" +
            "         \"type\":\"album\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"id\":\"1471670\",\n" +
            "         \"title\":\"TRON: Legacy Reconfigured\",\n" +
            "         \"link\":\"https://www.deezer.com/album/1471670\",\n" +
            "         \"cover\":\"https://api.deezer.com/album/1471670/image\",\n" +
            "         \"cover_small\":\"https://e-cdns-images.dzcdn.net/images/cover/2f34e0fe8086785fab7d6dfd8d48ba5a/56x56-000000-80-0-0.jpg\",\n" +
            "         \"cover_medium\":\"https://e-cdns-images.dzcdn.net/images/cover/2f34e0fe8086785fab7d6dfd8d48ba5a/250x250-000000-80-0-0.jpg\",\n" +
            "         \"cover_big\":\"https://e-cdns-images.dzcdn.net/images/cover/2f34e0fe8086785fab7d6dfd8d48ba5a/500x500-000000-80-0-0.jpg\",\n" +
            "         \"cover_xl\":\"https://e-cdns-images.dzcdn.net/images/cover/2f34e0fe8086785fab7d6dfd8d48ba5a/1000x1000-000000-80-0-0.jpg\",\n" +
            "         \"genre_id\":106,\n" +
            "         \"fans\":1531,\n" +
            "         \"release_date\":\"2011-04-05\",\n" +
            "         \"record_type\":\"album\",\n" +
            "         \"tracklist\":\"https://api.deezer.com/album/1471670/tracks\",\n" +
            "         \"explicit_lyrics\":false,\n" +
            "         \"type\":\"album\"\n" +
            "      }\n" +
            "   ]\n" +
            "}"

    fun mockTrackResponse() = "{\n" +
            "   \"data\":[\n" +
            "      {\n" +
            "         \"id\":\"3135553\",\n" +
            "         \"readable\":true,\n" +
            "         \"title\":\"One More Time\",\n" +
            "         \"title_short\":\"One More Time\",\n" +
            "         \"title_version\":\"\",\n" +
            "         \"isrc\":\"GBDUW0000053\",\n" +
            "         \"link\":\"https://www.deezer.com/track/3135553\",\n" +
            "         \"duration\":\"320\",\n" +
            "         \"track_position\":1,\n" +
            "         \"disk_number\":1,\n" +
            "         \"rank\":\"850065\",\n" +
            "         \"explicit_lyrics\":false,\n" +
            "         \"explicit_content_lyrics\":0,\n" +
            "         \"explicit_content_cover\":0,\n" +
            "         \"preview\":\"https://cdns-preview-e.dzcdn.net/stream/c-e77d23e0c8ed7567a507a6d1b6a9ca1b-7.mp3\",\n" +
            "         \"artist\":{\n" +
            "            \"id\":\"27\",\n" +
            "            \"name\":\"Daft Punk\",\n" +
            "            \"tracklist\":\"https://api.deezer.com/artist/27/top?limit=50\",\n" +
            "            \"type\":\"artist\"\n" +
            "         },\n" +
            "         \"type\":\"track\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"id\":\"3135554\",\n" +
            "         \"readable\":true,\n" +
            "         \"title\":\"Aerodynamic\",\n" +
            "         \"title_short\":\"Aerodynamic\",\n" +
            "         \"title_version\":\"\",\n" +
            "         \"isrc\":\"GBDUW0000057\",\n" +
            "         \"link\":\"https://www.deezer.com/track/3135554\",\n" +
            "         \"duration\":\"212\",\n" +
            "         \"track_position\":2,\n" +
            "         \"disk_number\":1,\n" +
            "         \"rank\":\"724626\",\n" +
            "         \"explicit_lyrics\":false,\n" +
            "         \"explicit_content_lyrics\":6,\n" +
            "         \"explicit_content_cover\":0,\n" +
            "         \"preview\":\"https://cdns-preview-b.dzcdn.net/stream/c-b2e0166bba75a78251d6dca9c9c3b41a-5.mp3\",\n" +
            "         \"artist\":{\n" +
            "            \"id\":\"27\",\n" +
            "            \"name\":\"Daft Punk\",\n" +
            "            \"tracklist\":\"https://api.deezer.com/artist/27/top?limit=50\",\n" +
            "            \"type\":\"artist\"\n" +
            "         },\n" +
            "         \"type\":\"track\"\n" +
            "      }\n" +
            "   ]\n" +
            "}"


}
