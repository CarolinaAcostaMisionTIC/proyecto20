package com.proyecto20.controller;

import com.proyecto20.model.Lib;
import com.proyecto20.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Lib")
@CrossOrigin(origins = "*")
public class LibController {
    @Autowired
    private LibService libService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBox(@RequestBody Lib lib){
        libService.create(lib);
    }

    @GetMapping("/all")
    public List<Lib> getBoxes(){
        return libService.libs();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Lib updateBox(@RequestBody Lib lib){
        return libService.update(lib);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBox(@PathVariable ("id") Integer id){
        libService.delete(id);
    }
}
