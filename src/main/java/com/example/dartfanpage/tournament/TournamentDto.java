package com.example.dartfanpage.tournament;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TournamentDto {

    private Long id;
    private String placeName;
    private String city;
    private String street;
    private LocalDate data;
    private LocalTime startAt;
    private BigDecimal entryFee;

    public TournamentDto() {
    }

    public TournamentDto(Long id, String placeName, String city, String street, LocalDate data, LocalTime startAt, BigDecimal entryFee) {
        this.id = id;
        this.placeName = placeName;
        this.city = city;
        this.street = street;
        this.data = data;
        this.startAt = startAt;
        this.entryFee = entryFee;
    }

    public TournamentDto (String placeName, String city, String street, LocalDate data, LocalTime startAt, BigDecimal entryFee) {
        this.placeName = placeName;
        this.city = city;
        this.street = street;
        this.data = data;
        this.startAt = startAt;
        this.entryFee = entryFee;
    }


}
