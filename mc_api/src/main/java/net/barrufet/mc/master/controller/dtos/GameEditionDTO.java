package net.barrufet.mc.master.controller.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@With
@AllArgsConstructor
public class GameEditionDTO extends GameEditionAsChildDTO {

    private GameAsChildDTO game;

}
