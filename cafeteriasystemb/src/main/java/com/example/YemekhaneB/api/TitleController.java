package com.example.YemekhaneB.api;

import com.example.YemekhaneB.model.Title;
import com.example.YemekhaneB.service.TitleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/titles")
public class TitleController {
    private final TitleService TitleService;
    public TitleController(TitleService TitleService) {
        this.TitleService = TitleService;
    }
    @GetMapping
    public ResponseEntity<List<Title>>getTitles(){
      return new ResponseEntity<>(TitleService.getTitles(), HttpStatus.OK);
    }
    /*@PostMapping
    public ResponseEntity<ApiResponse>SaveTitle(@RequestBody Long id){
        TitleService.createTitle(id);
        return new ResponseEntity<>(new ApiResponse("Successful"), HttpStatus.OK);

    }*/
}
