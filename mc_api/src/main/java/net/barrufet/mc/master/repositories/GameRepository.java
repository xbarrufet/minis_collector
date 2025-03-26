package net.barrufet.mc.master.repositories;

import net.barrufet.mc.master.model.GameEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import net.barrufet.mc.master.model.Game;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findByNameIgnoreCase(String name);
}
