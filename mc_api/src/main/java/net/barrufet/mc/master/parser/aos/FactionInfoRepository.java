package net.barrufet.mc.master.parser.aos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FactionInfoRepository extends JpaRepository<FactionInfo,String> {
    FactionInfo findByName(String name);
}
