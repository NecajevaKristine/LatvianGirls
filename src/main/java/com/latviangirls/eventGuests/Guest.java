package com.latviangirls.eventGuests;

import com.latviangirls.invitationConfirmation.ConfirmParticipation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="guests")

public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String guestId;
    @Column(name="nickname")
    private String guestNickName;
    private String guestPhoneNumber;
    @Column( name = "email",
            nullable = false,
            unique = true)
    private String guestEmail ;
    private String guestCount;
    @Column(name="inv_accept")
    @Enumerated(EnumType.STRING)
    private ConfirmParticipation guestInvitationAcceptance;
    private Timestamp guestResponseDate;
    private Timestamp invitationSent;
    @Column(name="inv_accept_date")
    private String guestInvitationDate;
    private String guestProjectCode;
    private String guestComment;
    @Column(name="guest_create_date")
    private Timestamp createdAt;
    @Column(name="info_update_date")
    private Timestamp updatedAt;


}

