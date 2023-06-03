package com.latviangirls.eventGuests;

import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService{


    @Autowired
    private GuestRepository guestRepository;
    @Override
    public List<Guest> getAllGuests() { //metode, kas iedos visu viesu listi
        return guestRepository.findAll();
    }
    @Override
    public void saveGuest(Guest guest) { //metode, kas iedos visu viesu listi
        this.guestRepository.save(guest);
    }

    @Override
    public Guest getGuestByGuestEmail(String guestEmail) {
        Guest optional = guestRepository.findByGuestEmail(guestEmail);
        Guest guest = null;
        return guest;
    }

}
