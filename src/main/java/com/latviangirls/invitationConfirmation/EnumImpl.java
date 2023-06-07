package com.latviangirls.invitationConfirmation;

public class EnumImpl {
    public static void main(String[] args) {

        ConfirmParticipation myVar = ConfirmParticipation.YES;

        switch (myVar) {

            case YES:
                System.out.println("Yes, of course!");
                break;
            case NO:
                System.out.println("Can not participate!");
                break;
            case MAYBE:
                System.out.println("I would like, but I will let You know later!");
                break;
        }
    }
}
