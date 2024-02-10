package com.example.YemekhaneB.api;

import com.example.YemekhaneB.model.User;
import com.example.YemekhaneB.model.Workplace;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.YemekhaneB.service.WorkplaceService;
import java.util.List;

@RestController
@RequestMapping("api/workplace")
public class WorkplaceController {

    private final WorkplaceService WorkplaceService;
    public WorkplaceController(WorkplaceService WorkplaceService) {
        this.WorkplaceService = WorkplaceService;
    }
    @GetMapping
    public ResponseEntity<List<Workplace>> getWorkplaces(){
        return new ResponseEntity<>(WorkplaceService.getWorkplaces(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> WorkplaceCreate(@RequestBody Workplace workplace) {
        WorkplaceService.createWorkplace(workplace);
        return new ResponseEntity<>(new ApiResponse("Created"),HttpStatus.OK);

    }

}
