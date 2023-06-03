package com.latviangirls.eventGuests;

import java.util.List;

public interface GuestService {
    default List<Guest> getAllGuests(){
        return null;
    }
    void saveGuest(Guest guest);

    void deleteById(Long guestId);

    Guest getGuestByGuestEmail(String guestEmail);

    Guest verifyGuest(String guestEmail, String guestProjectCode);



}
