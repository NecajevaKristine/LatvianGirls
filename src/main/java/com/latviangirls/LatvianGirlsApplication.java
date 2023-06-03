package com.latviangirls;

import com.latviangirls.eventGuests.Guest;
import com.latviangirls.eventGuests.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LatvianGirlsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LatvianGirlsApplication.class, args);
    }

    @Autowired
    private GuestRepository guestRepository;
    @Override
    public void run(String... args) throws Exception { //šis vienmēr darbosies run opcijā, kad aplikācija tiks atvērta, ierakstus pievienot tabulai
      /*  Guest guest1 = new Guest("Ritucis", "ritucis@inbox.lv", "29418133");
        guestRepository.save(guest1);*/
    }
}
