package com.albertmiro.data.repository

import com.albertmiro.data.entities.DistanceEntity
import com.albertmiro.data.entities.LocationEntity
import com.albertmiro.data.entities.PilotEntity
import com.albertmiro.data.entities.TripEntity
import com.albertmiro.data.mapper.TripsMapper
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class TripsRepositoryImplTest {

    lateinit var apiDataSource: APIDataSource
    lateinit var repository: TripsRepositoryImpl
    private var tripsMapper = TripsMapper()

    @Before
    fun setup() {
        apiDataSource = mock(APIDataSource::class.java)
        repository = TripsRepositoryImpl(apiDataSource, tripsMapper)
    }

    @Test
    fun `Empty trips`() {
        Mockito.`when`(apiDataSource.getTrips())
            .thenReturn(Pair(Single.just(emptyList()), null))
        val response = repository.getTrips().blockingGet()
        assertThat(response.size, `is`(0))
    }

    @Test
    fun `List of trips`() {
        Mockito.`when`(apiDataSource.getTrips())
            .thenReturn(Pair(getListOfTrips(), null))
        val response = repository.getTrips().blockingGet()
        assertThat(response.size, `is`(2))
    }

    @Test
    fun `Trip detail not valid`() {
        Mockito.`when`(apiDataSource.getTripDetails(Mockito.anyInt()))
            .thenReturn(Pair(getInvalidTripDetail(), null))
        val response = repository.getTripDetail(Mockito.anyInt()).blockingGet()
        assertThat(response.id, `is`((-1).toLong()))
    }

    private fun getInvalidTripDetail(): Single<TripEntity> {
        return Single.just(TripEntity.empty())
    }

    @Test
    fun `Trip detail with values`() {
        Mockito.`when`(apiDataSource.getTripDetails(Mockito.anyInt()))
            .thenReturn(Pair(getValidTripDetail(), null))
        val response = repository.getTripDetail(Mockito.anyInt()).blockingGet()
        assertThat(response.id, `is`((1).toLong()))
        assertThat(response.distance, `is`("100 km"))
    }

    private fun getValidTripDetail(): Single<TripEntity> {
        return Single.just(TripEntity(
            1,
            PilotEntity("Pilot", "/src/img.jpg", 5.0),
            DistanceEntity(100, "km"),
            12341,
            LocationEntity("Barcelona", "/src/bcn.jpg", "XXX"),
            LocationEntity("The Moon", "/src/moon.jpg", "YYY")
        ))
    }

    private fun getListOfTrips(): Single<List<TripEntity>> {
        return Single.just(listOf(TripEntity.empty(), TripEntity.empty()))
    }
}