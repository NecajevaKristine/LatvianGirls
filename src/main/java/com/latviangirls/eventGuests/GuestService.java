package com.latviangirls.eventGuests;

import java.util.List;

public interface GuestService {
    default List<Guest> getAllGuests(){
        return null;
    }
    void saveGuest(Guest guest);
    Guest getGuestByGuestEmail(String guestEmail);
}
