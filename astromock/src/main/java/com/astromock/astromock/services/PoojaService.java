package com.astromock.astromock.services;

import com.astromock.astromock.model.Pooja;
import com.astromock.astromock.repository.PoojaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoojaService {

    private final PoojaRepository repo;

    public PoojaService(PoojaRepository repo) {
        this.repo = repo;
    }

    public List<Pooja> getAll() {
        return repo.findAll();
    }

    public Pooja save(Pooja pooja) {
        return repo.save(pooja);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}