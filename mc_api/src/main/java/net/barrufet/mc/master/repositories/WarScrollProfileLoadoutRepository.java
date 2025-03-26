package net.barrufet.mc.master.repositories;

import net.barrufet.mc.master.model.Warscroll;
import net.barrufet.mc.master.model.WarscrollProfileLoadout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarScrollProfileLoadoutRepository extends JpaRepository<WarscrollProfileLoadout, String> {
}
