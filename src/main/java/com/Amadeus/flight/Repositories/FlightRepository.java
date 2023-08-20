package com.Amadeus.flight.Repositories;

import com.Amadeus.flight.DTO.AirportRequestDTO;
import com.Amadeus.flight.DTO.FlightRequestDTO;
import com.Amadeus.flight.DTO.FlightResponseDTO;
import com.Amadeus.flight.Entity.Airport;
import com.Amadeus.flight.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    /*@Query("SELECT f FROM Flight f where Flight.departure=:departure")*/
    List<Flight> findAllByDeparture(/*@Param("departure")*/ Date departure);
    /*@Query("SELECT f FROM Flight f where Flight.returnt=?1")*/
    List<Flight> findAllByReturnt(Date returnt);
    //Kalkış veya iniş şehrine göre uçuşları getirir.
    @Query("SELECT f FROM Flight f where f.from.city=?1")
    List<Flight> AllByFrom(String from);
    @Query("SELECT f FROM Flight f where f.to.city=?1")
    List<Flight> AllByTo(String to);

    //Verilen kalkış yeri, varış yeri, kalkış tarihi ve dönüş tarihine uygun uçuşları listeleyen bir API endpoint yapılmalı.
    //Dönüş tarihi verilmediyse tek yönlü uçuş, verildiyse çift yönlü uçuştur.
    //Tek yönlü uçuş için tek uçuş bilgisi, çift yönlü uçuş için iki uçuş bilgisi verilmeli.

    @Query("SELECT f from Flight f where f.departure  BETWEEN ?1 AND ?2 AND f.from.city=?3 AND f.to.city=?4")
    List<Flight> getFlightsBetweenDatesandfrom(Date kalkis,Date kalkishesap,String from,String to);

    @Query("SELECT f from Flight f where f.departure BETWEEN ?1 AND ?2 AND f.from.city=?5 AND f.to.city=?6 OR f.departure BETWEEN ?3 AND ?4 AND f.from.city=?6 AND f.to.city=?5")
    List<Flight> getFlightsBetweenDatesandto(Date kalkis,Date kalkishesap,Date inis,Date inishesap,String from,String to);
}
