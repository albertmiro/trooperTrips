package com.albertmiro.domain.usecases

import com.albertmiro.domain.repository.TripsRepository

class GetTripDetails(val tripsRepository: TripsRepository) {
    operator fun invoke(id: Int) =
        tripsRepository.getTripDetail(id)
}