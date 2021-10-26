package com.example.dartfanpage.tournament;

import com.example.dartfanpage.exception.NoExistException;
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
                .orElseThrow(() -> new NoExistException(String.format("Object with id %d not exist", dto.getId())));
        tournament.apply(dto);
        tournamentRepository.save(tournament);
    }

    public void delete(Long id) {
        if(tournamentRepository.existsById(id)){
            tournamentRepository.deleteById(id);
        } else {
            throw new NoExistException(String.format("Object with id %d not exist", id));
        }

    }

    private void removeExpiredTournaments(){
        tournamentRepository.findAll().stream().filter(tournament -> tournament.getData()
                .isBefore(LocalDate.now())).forEach(tournament -> delete(tournament.getId()));
    }

}
