package net.barrufet.mc.master.repositories;

import net.barrufet.mc.master.model.Faction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface FactionRepository  extends JpaRepository<Faction, Long> {

    List<Faction> findByGameEditionId(Long gameEditionId);
}
