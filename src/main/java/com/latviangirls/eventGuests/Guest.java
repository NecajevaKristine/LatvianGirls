package com.latviangirls.eventGuests;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long guestId;
    private String guestNickName;
    private String guestEmail;
    private String guestPhoneNumber;
   /* private Enum guestRel;
    private Long guestCount;
    private Long guestAdult;
    private Long guestChild;*/
    private String guestProjectCode;
    private String createdAt;
    private String updatedAt;
}