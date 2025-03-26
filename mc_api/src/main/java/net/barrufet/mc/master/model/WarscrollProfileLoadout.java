package net.barrufet.mc.master.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@With
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name="unitProfileLoadout")
@Table(name="unit_profile_loadouts")
public class WarscrollProfileLoadout {

    @Id
    private String loadoutId;
    @Column(name="loadout_name")
    private String loadout_name;
    @Lob
    @Column(name="attributes")
    private String attributes;
    @Column(name="base_cost")
    private int base_cost;
    @Lob
    @Column(name="abilities")
    private String abilities;
    @Column(name="is_default")
    private boolean is_default;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "wpl_wpr_id", referencedColumnName = "wpr_id")
//    WarscrollProfile warscrollProfile;

}
