package com.latviangirls.eventGuests;

import java.util.List;

public interface GuestService {

    void saveGuest(Guest guest);

    void deleteById(String guestId);

    Guest getGuestByGuestEmail(String guestEmail);

    Guest verifyGuest(String guestEmail, String guestProjectCode);
    List<Guest> findGuestById(String guestId);
}
