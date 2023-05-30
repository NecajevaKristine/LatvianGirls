package com.latviangirls.eventGuests;
import com.latviangirls.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }
    public void createNewGuest(Guest guest) throws Exception {
      /*  Guest guest1 = new User();
        user1.setEmail(user.getEmail());
        user1.setNickName(user.getNickName());*/
        this.guestRepository.save(guest);
    }

    public Guest verifyGuest(String guestEmail, String guestProjectCode) throws Exception {
        Guest guest = this.guestRepository.findByGuestEmailAndGuestProjectCode(guestEmail, guestProjectCode);
        if (guest == null) throw new Exception("Email or invitation code is not correct");
        return guest;
    }


}


