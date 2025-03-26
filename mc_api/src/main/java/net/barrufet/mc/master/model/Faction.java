package net.barrufet.mc.master.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@With
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name="faction")
@Table(name="mic_factions")
public class Faction {

    @Id
    @GeneratedValue
    @Column(name = "fac_id")
    private UUID id;
    @Column(name="fac_name")
    private String name;
    @Column(name="fac_alliance")
    private String alliance;
    @Lob
    @Column(name="fac_rules")
    private String rules;

    @ManyToOne
    @JoinColumn(name = "fac_ged_id", referencedColumnName = "ged_id")
    private  GameEdition gameEdition;

//    @OneToMany(mappedBy = "faction", fetch = FetchType.LAZY)
//    List<Warscroll> warscrolls;




}
