package tn.esprit.tpfoyer.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpfoyer.dto.BlocDTO;
import tn.esprit.tpfoyer.entities.Bloc;

@Mapper(componentModel = "spring")
public interface BlocMapper {

    @Mapping(target = "nomFoyerParent", source = "foyer.nomFoyer")
    BlocDTO toDto(Bloc bloc);

}