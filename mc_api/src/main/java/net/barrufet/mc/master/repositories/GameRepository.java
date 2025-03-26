package net.barrufet.mc.master.repositories;

import net.barrufet.mc.master.model.GameEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import net.barrufet.mc.master.model.Game;

import java.util.List;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {

}
