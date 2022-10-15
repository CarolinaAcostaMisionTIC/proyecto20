package com.proyecto20.service;

import com.proyecto20.model.Client;
import com.proyecto20.model.ClientReport;
import com.proyecto20.model.Reservation;
import com.proyecto20.model.Status;
import com.proyecto20.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;


    public Reservation create(Reservation reservation) {
        if (reservation.getIdReservation() == null){
            return reservationRepository.save(reservation);
        }else{
            Optional<Reservation> reservationNew = getReservation(reservation.getIdReservation());
            if (reservationNew.isEmpty()){
                return reservationRepository.save(reservation);
            }else {
                return reservation;
            }
        }
    }

    public Optional<Reservation> getReservation(Integer id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> reservations() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    public Reservation update(Reservation reservation) {
        if (reservation != null && reservation.getIdReservation() != null){
            if (reservationRepository.existsById(reservation.getIdReservation())){
                Optional<Reservation> oldReservation = reservationRepository.findById(reservation.getIdReservation());
                Reservation editedReservation = oldReservation.get();
                if (reservation.getStartDate() != null){
                    editedReservation.setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null){
                    editedReservation.setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null){
                    editedReservation.setStatus(reservation.getStatus());
                }
                if (reservation.getLib() != null){
                    editedReservation.setLib(reservation.getLib());
                }
                if (reservation.getClient() != null){
                    editedReservation.setClient(reservation.getClient());
                }
                if (reservation.getScore() != null){
                    editedReservation.setScore(reservation.getScore());
                }
                return reservationRepository.save(editedReservation);
            }else{
                return reservation;
            }
        }else {
            return reservation;
        }
    }

    public List<Reservation> periodTimeReservationsReport(String d1, String d2) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date a = new Date();
        Date b = new Date();
        try{
            a = parser.parse(d1);
            b = parser.parse(d2);
        }catch(ParseException e){
            e.printStackTrace();
        }
        if(a.before(b)){
            return reservationRepository.findAllByStartDateAfterAndStartDateBefore(a, b);
        }else {
            return new ArrayList<>();
        }
    }

    public List<ClientReport> getTopClient() {
        List<ClientReport> topClient = new ArrayList<>();
        List<Object[]> report = reservationRepository.reservationsClients();
        for (int i = 0; i < report.size(); i++) {
            topClient.add(new ClientReport((Long)report.get(i)[1],(Client)report.get(i)[0]));
        }
        return topClient;
    }

    public Status reservationsCountByStatus() {
        List<Reservation> completed = reservationRepository.findAllByStatus("completed");
        List<Reservation> cancelled = reservationRepository.findAllByStatus("cancelled");
        return new Status(completed.size(),cancelled.size());
    }

    public boolean delete(Integer id) {
        if(reservationRepository.existsById(id)){
            reservationRepository.deleteById(id);
            return true;
        }else
            return false;
    }
}
