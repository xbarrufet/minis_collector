package net.barrufet.mc.master.controller.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@With
@AllArgsConstructor
public class GameDTO extends GameAsChildDTO{


    List<GameEditionAsChildDTO> gameEditions;



}
