package net.barrufet.mc.master.repositories;

import net.barrufet.mc.master.model.GameEdition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GameEditionRepository extends JpaRepository<GameEdition, Long> {
    List<GameEdition> findByGameId(Long gameId);
}
