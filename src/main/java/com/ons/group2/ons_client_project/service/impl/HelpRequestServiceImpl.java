package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.HelpRequest;
import com.ons.group2.ons_client_project.repository.HelpRequestRepository;
import com.ons.group2.ons_client_project.service.HelpRequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelpRequestServiceImpl implements HelpRequestService {

    private HelpRequestRepository helpRequestRepository;

    public HelpRequestServiceImpl(HelpRequestRepository helpRequestRepository) {
        this.helpRequestRepository = helpRequestRepository;
    }

    @Override
    public HelpRequest save(HelpRequest helpRequest) {
        return helpRequestRepository.save(helpRequest);
    }

    @Override
    public Optional<HelpRequest> findById(Long id) {
        return helpRequestRepository.findById(id);
    }

    @Override
    public List<HelpRequest> getAllRequests() {
        return helpRequestRepository.findAll();
    }
}
