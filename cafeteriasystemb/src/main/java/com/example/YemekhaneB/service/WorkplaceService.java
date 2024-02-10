package com.example.YemekhaneB.service;

import com.example.YemekhaneB.model.Workplace;
import org.springframework.stereotype.Service;
import com.example.YemekhaneB.repository.WorkplaceRepository;

import java.util.List;

@Service
public class WorkplaceService {
    private final com.example.YemekhaneB.repository.WorkplaceRepository WorkplaceRepository;
    public WorkplaceService(WorkplaceRepository WorkplaceRepository){
        this.WorkplaceRepository = WorkplaceRepository;
    }
    public List<Workplace> getWorkplaces(){
        return WorkplaceRepository.findAll();
    }
    public Workplace getWorkplace(Long id) {
        return WorkplaceRepository.findById(id).orElse(null); // Or another default value
    }
    public void createWorkplace(Workplace workplace){

    }
}
