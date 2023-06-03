package com.latviangirls.eventGuests;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
public class GuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }
    public void createNewGuest(Guest guest) throws Exception {
        this.guestRepository.save(guest);
    }

    public Guest verifyGuest(String guestEmail,String guestProjectCode) throws Exception {
        Guest guest = this.guestRepository.findByGuestEmailAndGuestProjectCode(guestEmail, guestProjectCode);
        if (guest == null) throw new Exception("Email or invitation code is not correct");
        return guest;
    }

        public List<Guest> getAllGuests() {
             return (List<Guest>) guestRepository.findAll();
    }

 /*   Timestamp createdAt;

    @PrePersist //šī metode uztaisa datumu pirms saglabāšanas
    public void guestSaveTime(){
        this.createdAt = new Timestamp(System.currentTimeMillis());

    }*/


}


