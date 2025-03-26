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
@Entity(name="warscroll")
@Table(name="mic_warscrolls")
public class Warscroll {
    @Id
    @Column(name = "wsc_id")
    private String warscrollId;
    @Column(name="wsc_name")
    private String warscrollName;
    @Column(name="wsc_description")
    private String warscrollDescription;
    @Lob
    @Column(name="wsc_abilitites")
    private String abilities;
    @Column(name="wsc_allowed_sizes")
    private String allowed_sizes;
    @Column(name="wsc_cost")
    private String costs;
    @Column(name="wsc_composition")
    private String composition;
    @Column(name="wsc_keywords")
    private String keywords;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "wsc_fac_id", referencedColumnName = "fac_id")
//    Faction faction;
//
//    @OneToMany
//    @JoinColumn(name = "wsc_id", referencedColumnName = "wpr_wsc_id")
//    private List<WarscrollProfile> profiles;




}
