package com.latviangirls.eventGuests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GuestServiceImpl implements GuestService{


    @Autowired
    private GuestRepository guestRepository;

    public List<Guest> getAllGuests() { //metode, kas iedos visu viesu listi
        return guestRepository.findAll();
    }//metode, kas iedos visu viesu listi
    @Override
    public void saveGuest(Guest guest) {
        this.guestRepository.save(guest);
    }
    @Override
    public Guest getGuestByGuestEmail(String guestEmail) {
        return this.guestRepository.findByGuestEmail(guestEmail);
    }

    @Override
    public void deleteById(String guestId){
        this.guestRepository.deleteById(Long.valueOf(guestId));
    }

    public Guest verifyGuest(String guestEmail, String guestProjectCode) {
        Guest user = this.guestRepository.findGuestByGuestEmailAndGuestProjectCode(guestEmail, guestProjectCode);
        System.out.println();
        return user;
    }
    @Override
    public List<Guest> findGuestById(String guestId) {
        return this.guestRepository.findAll();
    }
    public List<Guest> findAll() {
        return null;
    }


}