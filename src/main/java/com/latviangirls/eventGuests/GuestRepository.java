package com.latviangirls.eventGuests;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
    Guest findGuestByGuestEmailAndGuestProjectCode (String guestEmail, String guestProjectCode);

}

