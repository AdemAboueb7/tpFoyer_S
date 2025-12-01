package tn.esprit.tpfoyer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlocDTO {
    private String nomBloc;
    private String capaciteBloc;

    private String nomFoyerParent;
}