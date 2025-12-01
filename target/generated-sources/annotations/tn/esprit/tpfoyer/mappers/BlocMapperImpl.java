package tn.esprit.tpfoyer.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tn.esprit.tpfoyer.dto.BlocDTO;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Foyer;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-01T21:27:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Microsoft)"
)
@Component
public class BlocMapperImpl implements BlocMapper {

    @Override
    public BlocDTO toDto(Bloc bloc) {
        if ( bloc == null ) {
            return null;
        }

        BlocDTO blocDTO = new BlocDTO();

        blocDTO.setNomFoyerParent( blocFoyerNomFoyer( bloc ) );
        blocDTO.setNomBloc( bloc.getNomBloc() );
        blocDTO.setCapaciteBloc( String.valueOf( bloc.getCapaciteBloc() ) );

        return blocDTO;
    }

    private String blocFoyerNomFoyer(Bloc bloc) {
        if ( bloc == null ) {
            return null;
        }
        Foyer foyer = bloc.getFoyer();
        if ( foyer == null ) {
            return null;
        }
        String nomFoyer = foyer.getNomFoyer();
        if ( nomFoyer == null ) {
            return null;
        }
        return nomFoyer;
    }
}
