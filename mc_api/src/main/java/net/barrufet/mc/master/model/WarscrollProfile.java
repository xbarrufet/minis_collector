package net.barrufet.mc.master.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@With
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name="unitProfile")
@Table(name="unit_profiles")
public class WarscrollProfile {

    @Id
    private String profile_id;
    @Column(name="profile_name")
    private String profile_name;
    @Lob
    private String attributes;
    @Column(name="base_cost")
    private int base_cost;
    @Lob
    @Column(name="loadout_configuration")
    private String loadout_configuration;
    @Column(name="is_default")
    private boolean is_default;

//    @OneToMany(mappedBy = "warscrollProfile")
//    List<WarscrollProfileLoadout> loadout;

    //@ManyToOne(,fetch = FetchType.LAZY)
    //@JoinColumn(name = "wpr_wsc_id", referencedColumnName = "wsc_id")
    //private Warscroll warscroll;

}
