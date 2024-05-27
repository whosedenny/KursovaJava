package com.hotel.roomreserv.Repository;

import com.hotel.roomreserv.Models.Reservation;
import com.hotel.roomreserv.Repository.Abstract.IReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@DataMongoTest
public class ReservationRepositoryTest {

    @Autowired
    private IReservationRepository reservationRepository;

    @Test
    public void ReservationRepository_AddReservation_ReturnAddedReservation() {
        Reservation reservation = new Reservation();
        reservation.setDateStart(LocalDate.now());
        reservation.setDateEnd(LocalDate.now().plusDays(3));

        Reservation savedReservation = reservationRepository.save(reservation);

        String reservationId = savedReservation.getId();

        Optional<Reservation> retrievedReservationOptional = reservationRepository.findById(reservationId);

        Assertions.assertTrue(retrievedReservationOptional.isPresent());
        Reservation retrievedReservation = retrievedReservationOptional.get();
        Assertions.assertEquals(LocalDate.now(), retrievedReservation.getDateStart());
        Assertions.assertEquals(LocalDate.now().plusDays(3), retrievedReservation.getDateEnd());
    }

    @Test
    public void ReservationRepository_DeleteReservationById_ReservationNotExists() {
        Reservation reservation = new Reservation();
        reservation.setDateStart(LocalDate.now());
        reservation.setDateEnd(LocalDate.now().plusDays(2));
        Reservation savedReservation = reservationRepository.save(reservation);

        reservationRepository.deleteById(savedReservation.getId());

        Optional<Reservation> deletedReservation = reservationRepository.findById(savedReservation.getId());
        Assertions.assertFalse(deletedReservation.isPresent());
    }

    @Test
    public void ReservationRepository_UpdateReservation_ReturnUpdatedReservation() {
        Reservation reservation = new Reservation();
        reservation.setDateStart(LocalDate.now());
        reservation.setDateEnd(LocalDate.now().plusDays(2));
        Reservation savedReservation = reservationRepository.save(reservation);

        savedReservation.setDateEnd(LocalDate.now().plusDays(4));
        Reservation updatedReservation = reservationRepository.save(savedReservation);

        Optional<Reservation> retrievedReservationOptional = reservationRepository.findById(updatedReservation.getId());

        Assertions.assertTrue(retrievedReservationOptional.isPresent());
        Reservation retrievedReservation = retrievedReservationOptional.get();
        Assertions.assertEquals(LocalDate.now().plusDays(4), retrievedReservation.getDateEnd());
    }

    @Test
    public void ReservationRepository_FindAllReservations_ReturnAllReservations() {
        Reservation reservation1 = new Reservation();
        reservation1.setDateStart(LocalDate.now());
        reservation1.setDateEnd(LocalDate.now().plusDays(3));

        Reservation reservation2 = new Reservation();
        reservation2.setDateStart(LocalDate.now().plusDays(1));
        reservation2.setDateEnd(LocalDate.now().plusDays(4));

        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);

        List<Reservation> reservations = reservationRepository.findAll();
        Assertions.assertEquals(5, reservations.size());
        Assertions.assertTrue(reservations.stream().anyMatch(r -> r.getDateEnd().equals(LocalDate.now().plusDays(3))));
        Assertions.assertTrue(reservations.stream().anyMatch(r -> r.getDateEnd().equals(LocalDate.now().plusDays(4))));
    }
}
