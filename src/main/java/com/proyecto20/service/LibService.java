package com.proyecto20.service;


import com.proyecto20.model.Lib;
import com.proyecto20.repository.LibRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibService {
    @Autowired
    private LibRepository libRepository;

    public Lib create(Lib lib) {
        if (lib.getId() == null){
            return libRepository.save(lib);
        }else{
            Optional<Lib> libNew = getLib(lib.getId());
            if (libNew.isEmpty()){
                return libRepository.save(lib);
            }else {
                return lib;
            }
        }
    }


    public Optional<Lib> getLib(Integer id) {
        return libRepository.findById(id);
    }


    public List<Lib> libs() {
        return (List<Lib>) libRepository.findAll();
    }

    public Lib update(Lib lib) {
        if (lib != null && lib.getId() != null){
            if (libRepository.existsById(lib.getId())){
                Optional<Lib> oldLib = libRepository.findById(lib.getId());
                Lib editedLib = oldLib.get();
                if (lib.getName() != null){
                    editedLib.setName(lib.getName());
                }
                if (lib.getTarget() != null){
                    editedLib.setTarget(lib.getTarget());
                }
                if (lib.getCapacity() != 0){
                    editedLib.setCapacity(lib.getCapacity());
                }
                if (lib.getDescription() != null){
                    editedLib.setDescription(lib.getDescription());
                }
                if (lib.getCategory() != null){
                    editedLib.setCategory(lib.getCategory());
                }
                if (lib.getMessages() != null){
                    editedLib.setMessages(lib.getMessages());
                }
                if (lib.getReservations() != null){
                    editedLib.setReservations(lib.getReservations());
                }
                return libRepository.save(editedLib);
            }else{
                return lib;
            }
        }else {
            return lib;
        }
    }

    public boolean delete(Integer id) {
        if(libRepository.existsById(id)){
            libRepository.deleteById(id);
            return true;
        }else
            return false;
    }
}
