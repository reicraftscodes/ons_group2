package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.HelpRequest;
import java.util.Optional;

public interface HelpRequestService {
    HelpRequest save(HelpRequest helpRequest);
    Optional<HelpRequest> findById(Long id);

}
