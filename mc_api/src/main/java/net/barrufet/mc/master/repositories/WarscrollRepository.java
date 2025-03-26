package net.barrufet.mc.master.repositories;

import net.barrufet.mc.master.model.Faction;
import net.barrufet.mc.master.model.Warscroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarscrollRepository  extends JpaRepository<Warscroll, String> {
}
