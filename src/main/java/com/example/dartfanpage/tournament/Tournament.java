package com.example.dartfanpage.tournament;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placeName;
    private String city;
    private String street;
    private String venueNumber;
    private String zipCode;
    private String postOffice;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    private LocalTime startAt;
    private BigDecimal entryFee;

    public TournamentDto toDto() {
        return new TournamentDto(
                this.id,
                this.placeName,
                this.city,
                this.street,
                this.venueNumber,
                this.zipCode,
                this.postOffice,
                this.data,
                this.startAt,
                this.entryFee);
    }

public static Tournament fromDto(TournamentDto dto){
        Tournament tournament = new Tournament();
        tournament.placeName = dto.getPlaceName();
        tournament.city = dto.getCity();
        tournament.street = dto.getStreet();
        tournament.venueNumber = dto.getVenueNumber();
        tournament.zipCode = dto.getZipCode();
        tournament.postOffice = dto.getPostOffice();
        tournament.data = dto.getData();
        tournament.startAt = dto.getStartAt();
        tournament.entryFee= dto.getEntryFee();
        return tournament;
}

    public void apply(TournamentDto dto) {
        this.placeName = dto.getPlaceName();
        this.city = dto.getCity();
        this.street = dto.getStreet();
        this.venueNumber = dto.getVenueNumber();
        this.zipCode = dto.getZipCode();
        this.postOffice = dto.getPostOffice();
        this.data = dto.getData();
        this.startAt = dto.getStartAt();
        this.entryFee = dto.getEntryFee();
    }

}
