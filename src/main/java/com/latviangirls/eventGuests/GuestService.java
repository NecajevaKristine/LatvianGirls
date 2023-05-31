package com.latviangirls.eventGuests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // bean(s) others include @Controller @Repository @Bean etc
public class GuestService {

    private GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }
    public void createGuest(Guest guest) throws Exception {
        this.guestRepository.save(guest);
    }

    public Guest verifyGuest(String guestEmail, String guestProjectCode) throws Exception {
        Guest guest = this.guestRepository.findGuestByGuestEmailAndGuestProjectCode(guestEmail, guestProjectCode);
        if (guest == null) throw new Exception("LOGIN FAILED! Check if email or invitation code is correct!");
        return guest;
    }
}
