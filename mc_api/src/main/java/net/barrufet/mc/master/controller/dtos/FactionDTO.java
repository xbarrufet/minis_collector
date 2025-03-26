package net.barrufet.mc.master.controller.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@With
@AllArgsConstructor
public class FactionDTO extends FactionHeaderDTO{
    private String rules;
}
