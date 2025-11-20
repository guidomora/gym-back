package com.app.gym.api_gym_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.gym.api_gym_app.model.Membership;


public interface MembershipRepository extends JpaRepository<Membership, Long> {

    Optional<Membership> findByMembershipKey(String membershipKey);
    
}
