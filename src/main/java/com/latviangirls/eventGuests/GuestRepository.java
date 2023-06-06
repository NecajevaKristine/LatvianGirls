package com.latviangirls.eventGuests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface GuestRepository extends CrudRepository<Guest, Long> {
    Guest findByGuestEmailAndGuestProjectCode(String guestEmail, String guestProjectCode);
    Guest findByGuestProjectCode(String guestProjectCode);

    Guest findByGuestEmail(String guestEmail);

    Guest findGuestByGuestEmailAndGuestProjectCode(String guestEmail, String guestProjectCode);

    public interface guestRepository extends JpaRepository<Guest, Long> {
    Guest findByGuestEmailAndGuestProjectCode(String guestEmail, String guestProjectCode);

    Guest findByGuestProjectCode(String guestProjectCode);


    Guest findByGuestEmail(String guestEmail);

    void deleteById(Long guestId);

    Guest findGuestByGuestEmailAndGuestProjectCode(String guestEmail, String guestProjectCode);

    }
}

