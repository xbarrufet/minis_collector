package net.barrufet.mc.master.parser.aos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@With
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name="AosFactionInfo")
@Table(name="mic_aos_factions_info")
public class FactionInfo {
    @Id
    private  String name;
    private  String alliance;
    private  String url;
}
