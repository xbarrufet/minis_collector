package net.barrufet.mc.master.controller.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@With
@AllArgsConstructor
@EqualsAndHashCode
public class GameAsChildDTO {
    private String id;
    private String name;
    private String description;
}
