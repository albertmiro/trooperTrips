package com.albertmiro.data

import com.albertmiro.data.api.APIService
import com.albertmiro.data.entities.DistanceEntity
import com.albertmiro.data.entities.LocationEntity
import com.albertmiro.data.entities.PilotEntity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets


class APIServiceTest {

    lateinit var service: APIService
    lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    @After
    @Throws(IOException::class)
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun shouldGetNotEmptyListOfTrips() {
        enqueueResponse("trips.json")
        service.getTrips()
            .test()
            .assertValue { response -> response.isNotEmpty() }
    }

    @Test
    fun shouldGetCorrectNumberOfTrips() {
        enqueueResponse("trips.json")
        service.getTrips()
            .test()
            .assertValue { response -> response.size == 7 }
    }

    @Test
    fun shouldGetTripWithCorrectValues() {
        enqueueResponse("trips.json")
        service.getTrips()
            .test()
            .assertValue { response -> response.isNotEmpty() }
            .assertValue { response -> response[0].id == (1).toLong() }
            .assertValue { response -> isPilotCorrect(response[0].pilot) }
            .assertValue { response -> isDistanceCorrect(response[0].distance) }
            .assertValue { response -> response[0].duration == (19427000).toLong() }
            .assertValue { response -> isLocationCorrect(response[0].pickUp, true) }
            .assertValue { response -> isLocationCorrect(response[0].dropOff, false) }
    }

    private fun isPilotCorrect(pilot: PilotEntity): Boolean {
        return pilot.name == "Dark Vador" &&
                pilot.avatar == "/static/dark-vador.png" &&
                pilot.rating == 5.0
    }

    private fun isDistanceCorrect(distance: DistanceEntity): Boolean {
        return distance.value == (2478572).toLong() &&
                distance.unit == "km"
    }

    private fun isLocationCorrect(location: LocationEntity, isPickUp: Boolean): Boolean {
        return if (isPickUp) {
            location.name == "Yavin 4" &&
                    location.picture == "/static/yavin-4.png" &&
                    location.date == "2017-12-09T14:12:51Z"
        } else {
            location.name == "Naboo" &&
                    location.picture == "/static/naboo.png" &&
                    location.date == "2017-12-09T19:35:51Z"
        }
    }

    @Test
    fun shouldGetEmptyResponse() {
        enqueueResponse("trips_empty.json")
        service.getTrips()
            .test()
            .assertValue { response -> response.isNullOrEmpty() }
    }

    @Test
    fun tripError400() {
        mockWebServer.enqueue(MockResponse().setBody("{error:\"bad request\"").setResponseCode(400))
        service.getTrips()
            .test()
            .assertError(HttpException::class.java)
    }

    @Test
    fun shouldGetTripDetailWithCorrectValues() {
        enqueueResponse("trip_detail.json")
        service.getTripDetails(Mockito.anyInt())
            .test()
            .assertValue { response -> response.id == (1).toLong() }
            .assertValue { response -> isPilotCorrect(response.pilot) }
            .assertValue { response -> isDistanceCorrect(response.distance) }
            .assertValue { response -> response.duration == (19427000).toLong() }
            .assertValue { response -> isLocationCorrect(response.pickUp, true) }
            .assertValue { response -> isLocationCorrect(response.dropOff, false) }
    }

    @Test
    fun shouldGetTripDetailEmpty() {
        enqueueResponse("trip_detail_empty.json")
        service.getTripDetails(Mockito.anyInt())
            .test()
            .assertValue { response -> response.id == (0).toLong() }
            .assertValue { response -> response.pilot == null }
            .assertValue { response -> response.distance == null }
            .assertValue { response -> response.duration == (0).toLong() }
            .assertValue { response -> response.pickUp == null }
            .assertValue { response -> response.dropOff == null }
    }

    @Test
    fun tripDetailError400() {
        mockWebServer.enqueue(MockResponse().setBody("{error:\"bad request\"").setResponseCode(400))
        service.getTripDetails(Mockito.anyInt())
            .test()
            .assertError(HttpException::class.java)
    }

    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("service-response/$fileName")
        val source = inputStream.source()
        val buffer = source.buffer()
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setBody(buffer.readString(StandardCharsets.UTF_8)))
    }

}