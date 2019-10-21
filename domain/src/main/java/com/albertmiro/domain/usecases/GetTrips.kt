package com.albertmiro.domain.usecases

import com.albertmiro.domain.repository.TripsRepository

class GetTrips(val tripsRepository: TripsRepository) {
    operator fun invoke(forceRefresh: Boolean) =
        tripsRepository.getTrips(forceRefresh)
}