package com.app.gym.api_gym_app.mapper;

import com.app.gym.api_gym_app.dto.GymDTO;
import com.app.gym.api_gym_app.model.Gym;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GymMapper {
    GymDTO toGymDTO(Gym gym);

    Gym toGym(GymDTO gymDTO);

    List<GymDTO> toGymDTOs(List<Gym> gyms);
}
