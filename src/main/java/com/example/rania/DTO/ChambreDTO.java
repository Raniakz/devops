package com.example.rania.DTO;

import com.example.rania.Utils.Enum.TypeChambre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChambreDTO {
    private Long numeroChambre;
    private Long capacite;
    private TypeChambre typeC;
}
