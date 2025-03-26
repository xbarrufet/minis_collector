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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_seq")
    @SequenceGenerator(
            name = "game_seq",
            sequenceName = "game_id_seq",
            allocationSize = 100 // Cache size for sequence values
    )
    @Column(name = "gam_id",columnDefinition = "BIGINT")
    private Long id;

    @Column(name="gam_name")
    private  String name;
    @Column(name="gam_description")
    private  String description;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private List<GameEdition> gameEditions;

}
