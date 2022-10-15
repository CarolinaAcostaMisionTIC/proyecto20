package com.proyecto20.controller;

import com.proyecto20.model.ClientReport;
import com.proyecto20.model.Reservation;
import com.proyecto20.model.Status;
import com.proyecto20.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Reservation")
@CrossOrigin(origins = "*")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    private ClientReport clientReport;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReservation(@RequestBody Reservation reservation){
        reservationService.create(reservation);
    }

    @GetMapping("/all")
    public List<Reservation> getReservations(){
        return reservationService.reservations();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateReservation(@RequestBody Reservation reservation){
        reservationService.update(reservation);
    }

    @GetMapping("/report-dates/{d1}/{d2}")
    public List<Reservation> getReservationsReportDates(@PathVariable("d1") String d1, @PathVariable("d2") String d2){
        return reservationService.periodTimeReservationsReport(d1,d2);
    }
    @GetMapping("/report-clients")
    public List<ClientReport> getReservationsReportClient(){
        return reservationService.getTopClient();
    }

    @GetMapping("/report-status")
    public Status getReservationsStatusReport(){
        return reservationService.reservationsCountByStatus();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable ("id") Integer id){
        reservationService.delete(id);
    }
}
