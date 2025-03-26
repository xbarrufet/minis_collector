package net.barrufet.mc.master.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import net.barrufet.mc.common.baseclasses.McEntity;
import net.barrufet.mc.common.exceptions.NotValidEntityException;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="gameEdition")
@Table(name="mic_game_editions")

public class GameEdition implements McEntity {

    @Id
    @GeneratedValue
    @Column(name = "ged_id")
    private UUID id;

    @Column(name="ged_name", nullable = false)
    private  String name;

    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name = "ged_gam_id", referencedColumnName = "gam_id", nullable = false)
    private  Game game;

    @OneToMany(mappedBy = "gameEdition", fetch = FetchType.LAZY)
    private List<Faction> factions;

}
