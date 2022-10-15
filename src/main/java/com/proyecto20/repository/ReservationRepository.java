package com.proyecto20.repository;

import com.proyecto20.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Integer> {
    public List<Reservation> findAllByStatus (String status);

    public List<Reservation> findAllByDate(Date d1, Date d2);

    @Query("SELECT c.client, COUNT(c.client) from Reservation AS c group by c.client order by COUNT(c.client) DESC")
    public List<Object[]> reservationsClients();
}
