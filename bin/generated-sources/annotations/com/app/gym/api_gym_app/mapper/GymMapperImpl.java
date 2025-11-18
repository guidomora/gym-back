package com.app.gym.api_gym_app.mapper;

import com.app.gym.api_gym_app.dto.GymDTO;
import com.app.gym.api_gym_app.model.Gym;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-18T01:02:20-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251023-0518, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class GymMapperImpl implements GymMapper {

    @Override
    public GymDTO toGymDTO(Gym gym) {
        if ( gym == null ) {
            return null;
        }

        GymDTO.GymDTOBuilder gymDTO = GymDTO.builder();

        gymDTO.id( gym.getId() );
        gymDTO.nombre( gym.getNombre() );

        return gymDTO.build();
    }

    @Override
    public Gym toGym(GymDTO gymDTO) {
        if ( gymDTO == null ) {
            return null;
        }

        Gym.GymBuilder gym = Gym.builder();

        gym.id( gymDTO.getId() );
        gym.nombre( gymDTO.getNombre() );

        return gym.build();
    }

    @Override
    public List<GymDTO> toGymDTOs(List<Gym> gyms) {
        if ( gyms == null ) {
            return null;
        }

        List<GymDTO> list = new ArrayList<GymDTO>( gyms.size() );
        for ( Gym gym : gyms ) {
            list.add( toGymDTO( gym ) );
        }

        return list;
    }
}
