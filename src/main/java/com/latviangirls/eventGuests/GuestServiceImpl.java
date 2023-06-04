package com.latviangirls.eventGuests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public abstract class GuestServiceImpl implements GuestService{


    @Autowired
    private GuestRepository guestRepository;
    @Override
    public List<Guest> getAllGuests() { //metode, kas iedos visu viesu listi
        return (List<Guest>) this.guestRepository.findAll();
    }
    @Override
    public void saveGuest(Guest guest) { //metode, kas iedos visu viesu listi
        this.guestRepository.save(guest);
    }
    @Override
    public Guest getGuestByGuestEmail(String guestEmail) {
        return this.guestRepository.findByGuestEmail(guestEmail);
    }

    @Override
    public void deleteById(Long guestId){
        this.guestRepository.deleteById(guestId);
    }

    public Guest verifyGuest(String guestEmail, String guestProjectCode) {
        Guest user = this.guestRepository.findGuestByGuestEmailAndGuestProjectCode(guestEmail, guestProjectCode);
        return user;
    }


}
