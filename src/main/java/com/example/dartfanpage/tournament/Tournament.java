package com.example.dartfanpage.tournament;

import org.apache.tomcat.jni.Address;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String placeName;
    private String city;
    private String street;
    private LocalDate data;
    private LocalTime startAt;
    private BigDecimal entryFee;

    public TournamentDto toDto() {
        return new TournamentDto(
                this.id,
                this.placeName,
                this.city,
                this.street,
                this.data,
                this.startAt,
                this.entryFee);
    }

public static Tournament fromDto(TournamentDto dto){
        Tournament tournament = new Tournament();
        tournament.placeName = dto.getPlaceName();
        tournament.city = dto.getCity();
        tournament.street = dto.getStreet();
        tournament.data = dto.getData();
        tournament.startAt = dto.getStartAt();
        tournament.entryFee= dto.getEntryFee();
        return tournament;
}

}
