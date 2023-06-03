package com.latviangirls.eventGuests;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestId;
    @Column(name="nickname")
    private String guestNickName;
    private String guestEmail;
    private String guestPhoneNumber;
    private Long guestCount;
    @Column(name="inv_accept")
    private String guestInvitationAcceptance;
    private String guestResponseDate;
    private String invitationSent;
    @Column(name="inv_accept_date")
    private String guestInvitationDate;
    private String guestProjectCode;
    private String guestComment;
    @Column(name="guest_create_date")
    private String createdAt;
    @Column(name="info_update_date")
    private String updatedAt;

}

