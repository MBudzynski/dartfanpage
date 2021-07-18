package com.example.dartfanpage.tournament;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public List<TournamentDto> getCurrentTournaments() {
        removeExpiredTournaments();
        return tournamentRepository.findAll().stream()
                .map(tournament -> tournament.toDto()).collect(Collectors.toList());
    }

    public void addTournament(TournamentDto dto) {
        Tournament tournament = Tournament.fromDto(dto);
        tournamentRepository.save(tournament);
    }

    public Optional<TournamentDto> findTournamentById(Long id) {
        return tournamentRepository.findById(id).map(tournament -> tournament.toDto());
    }

    public void update(TournamentDto dto) {
        Tournament tournament = tournamentRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono produktu o id: " + dto.getId()));
        tournament.apply(dto);
        tournamentRepository.save(tournament);
    }

    public void delete(Long id) {
        tournamentRepository.deleteById(id);
    }

    private void removeExpiredTournaments(){
        tournamentRepository.findAll().stream().filter(tournament -> tournament.getData()
                .isBefore(LocalDate.now())).forEach(tournament -> delete(tournament.getId()));
    }

}
