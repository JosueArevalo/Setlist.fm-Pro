package com.josuearevalodev.data.setlistfm

import com.google.gson.Gson
import com.josuearevalodev.domain.setlistfm.entities.ArtistSetlistsResponseEntity
import com.josuearevalodev.domain.setlistfm.entities.SearchArtistsResponseEntity

object MockGenerator {

    val searchArtistsResponseEntity: SearchArtistsResponseEntity
        get() {
            val gson = Gson()
            return gson.fromJson<SearchArtistsResponseEntity>(jsonResponseSearchArtists, SearchArtistsResponseEntity::class.java)
        }

    val artistSetlistsResponseEntity: ArtistSetlistsResponseEntity
        get() {
            val gson = Gson()
            return gson.fromJson<ArtistSetlistsResponseEntity>(jsonResponseArtistSetlists, ArtistSetlistsResponseEntity::class.java)
        }

    val jsonResponseSearchArtists = "{\n" +
            "   \"type\": \"artists\",\n" +
            "   \"itemsPerPage\": 30,\n" +
            "   \"page\": 1,\n" +
            "   \"total\": 4,\n" +
            "   \"artist\": [\n" +
            "      {\n" +
            "         \"mbid\": \"c4707a18-2236-4426-9e67-429ce023777c\",\n" +
            "         \"name\": \"Avalanch\",\n" +
            "         \"sortName\": \"Avalanch\",\n" +
            "         \"disambiguation\": \"Spanish metal band\",\n" +
            "         \"url\": \"https://www.setlist.fm/setlists/avalanch-13d61159.html\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"mbid\": \"03cb4c58-3624-4f27-8b6e-92b5d6c08543\",\n" +
            "         \"name\": \"Avalanch\",\n" +
            "         \"sortName\": \"Avalanch\",\n" +
            "         \"disambiguation\": \"Japanese noise rock\",\n" +
            "         \"url\": \"https://www.setlist.fm/setlists/avalanch-53d4d3c1.html\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"mbid\": \"042bf7e4-cc30-4e6e-8757-4780ad3ca3aa\",\n" +
            "         \"name\": \"Avalanch\",\n" +
            "         \"sortName\": \"Avalanch\",\n" +
            "         \"disambiguation\": \"UK grime MC\",\n" +
            "         \"url\": \"https://www.setlist.fm/setlists/avalanch-53d2f311.html\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"mbid\": \"01b3648f-dd64-4208-861b-6e219f1f54a7\",\n" +
            "         \"name\": \"Avalanch All Star Band\",\n" +
            "         \"sortName\": \"Avalanch All Star Band\",\n" +
            "         \"disambiguation\": \"\",\n" +
            "         \"url\": \"https://www.setlist.fm/setlists/avalanch-all-star-band-3cab90f.html\"\n" +
            "      }\n" +
            "   ]\n" +
            "}"

    val jsonResponseArtistSetlists = "{\n" +
            "  \"setlist\" : [ {\n" +
            "    \"artist\" : {\n" +
            "      \"mbid\" : \"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d\",\n" +
            "      \"tmid\" : 735610,\n" +
            "      \"name\" : \"The Beatles\",\n" +
            "      \"sortName\" : \"Beatles, The\",\n" +
            "      \"disambiguation\" : \"John, Paul, George and Ringo\",\n" +
            "      \"url\" : \"https://www.setlist.fm/setlists/the-beatles-23d6a88b.html\"\n" +
            "    },\n" +
            "    \"venue\" : {\n" +
            "      \"city\" : { },\n" +
            "      \"url\" : \"https://www.setlist.fm/venue/compaq-center-san-jose-ca-usa-6bd6ca6e.html\",\n" +
            "      \"id\" : \"6bd6ca6e\",\n" +
            "      \"name\" : \"Compaq Center\"\n" +
            "    },\n" +
            "    \"tour\" : {\n" +
            "      \"name\" : \"North American Tour 1964\"\n" +
            "    },\n" +
            "    \"set\" : [ {\n" +
            "      \"name\" : \"...\",\n" +
            "      \"encore\" : 12345,\n" +
            "      \"song\" : [ { }, { } ]\n" +
            "    }, {\n" +
            "      \"name\" : \"...\",\n" +
            "      \"encore\" : 12345,\n" +
            "      \"song\" : [ { }, { } ]\n" +
            "    } ],\n" +
            "    \"info\" : \"Recorded and published as 'The Beatles at the Hollywood Bowl'\",\n" +
            "    \"url\" : \"https://www.setlist.fm/setlist/the-beatles/1964/hollywood-bowl-hollywood-ca-63de4613.html\",\n" +
            "    \"id\" : \"63de4613\",\n" +
            "    \"versionId\" : \"7be1aaa0\",\n" +
            "    \"eventDate\" : \"23-08-1964\",\n" +
            "    \"lastUpdated\" : \"2013-10-20T05:18:08.000+0000\"\n" +
            "  }, {\n" +
            "    \"artist\" : {\n" +
            "      \"mbid\" : \"...\",\n" +
            "      \"tmid\" : 12345,\n" +
            "      \"name\" : \"...\",\n" +
            "      \"sortName\" : \"...\",\n" +
            "      \"disambiguation\" : \"...\",\n" +
            "      \"url\" : \"...\"\n" +
            "    },\n" +
            "    \"venue\" : {\n" +
            "      \"city\" : { },\n" +
            "      \"url\" : \"...\",\n" +
            "      \"id\" : \"...\",\n" +
            "      \"name\" : \"...\"\n" +
            "    },\n" +
            "    \"tour\" : {\n" +
            "      \"name\" : \"...\"\n" +
            "    },\n" +
            "    \"set\" : [ {\n" +
            "      \"name\" : \"...\",\n" +
            "      \"encore\" : 12345,\n" +
            "      \"song\" : [ { }, { } ]\n" +
            "    }, {\n" +
            "      \"name\" : \"...\",\n" +
            "      \"encore\" : 12345,\n" +
            "      \"song\" : [ { }, { } ]\n" +
            "    } ],\n" +
            "    \"info\" : \"...\",\n" +
            "    \"url\" : \"...\",\n" +
            "    \"id\" : \"...\",\n" +
            "    \"versionId\" : \"...\",\n" +
            "    \"eventDate\" : \"...\",\n" +
            "    \"lastUpdated\" : \"...\"\n" +
            "  } ],\n" +
            "  \"total\" : 42,\n" +
            "  \"page\" : 1,\n" +
            "  \"itemsPerPage\" : 20\n" +
            "}"
}
