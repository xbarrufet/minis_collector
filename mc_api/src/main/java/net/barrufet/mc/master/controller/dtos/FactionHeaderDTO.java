package net.barrufet.mc.master.controller.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@With
@AllArgsConstructor
@EqualsAndHashCode
public class FactionHeaderDTO {
    private UUID id;
    private String name;
    private String alliance;

}
