package net.barrufet.mc.master.repositories;

import net.barrufet.mc.master.model.Warscroll;
import net.barrufet.mc.master.model.WarscrollProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarscrollProfileRepository extends JpaRepository<WarscrollProfile, String>  {
}
