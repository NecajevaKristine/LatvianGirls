package com.latviangirls.eventGuests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {


      Guest findByGuestEmailAndGuestProjectCode(String guestEmail, String guestProjectCode);
      Guest findByGuestProjectCode(String guestProjectCode);
      Guest findByGuestEmail(String guestEmail);
      void deleteById(Long guestId);
      Guest findGuestByGuestEmailAndGuestProjectCode (String guestEmail, String guestProjectCode);
      @Override
      List<Guest> findAll();
}

