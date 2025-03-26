package net.barrufet.mc.master.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Entity(name="game")
@Table(name="mic_games")
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "gam_id")
    private UUID id;
    @Column(name="gam_name")
    private  String name;
    @Column(name="gam_description")
    private  String description;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private List<GameEdition> gameEditions;

}
