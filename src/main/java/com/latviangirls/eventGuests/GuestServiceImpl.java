package com.latviangirls.eventGuests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GuestServiceImpl {
    private GuestRepository guestRepository;

    @Autowired
    private GuestServiceImpl(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }


    public List<Guest> getAllGuests() { //metode, kas iedos visu viesu listi
        return guestRepository.findAll();
    }//metode, kas iedos visu viesu listi

    public void saveGuest(Guest guest) {
        this.guestRepository.save(guest);
    }

    public Guest getGuestByGuestEmail(String guestEmail) {
        return this.guestRepository.findByGuestEmail(guestEmail);
    }

    public void deleteById(Long guestId){
        this.guestRepository.deleteById(guestId);
    }

    public Guest verifyGuest(String guestEmail, String guestProjectCode) {
        Guest user = this.guestRepository.findGuestByGuestEmailAndGuestProjectCode(guestEmail, guestProjectCode);
        return user;
    }

    public List<Guest> findGuestById(String guestId) {
        return this.guestRepository.findAll();
    }
    public List<Guest> findAll() {
        return null;
    }


    public void updateGuest(Guest guest) {
        this.guestRepository.save(guest);
    }

    public Guest getGuestById(Long guestId) {
        return this.guestRepository.findGuestByGuestId(guestId);
    }
  }