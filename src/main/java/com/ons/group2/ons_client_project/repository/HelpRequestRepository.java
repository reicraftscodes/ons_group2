package com.ons.group2.ons_client_project.repository;

import com.ons.group2.ons_client_project.model.HelpRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HelpRequestRepository extends JpaRepository<HelpRequest, Integer> {

    HelpRequest save(HelpRequest helpRequest);

    Optional<HelpRequest> findById(Long id);
}
