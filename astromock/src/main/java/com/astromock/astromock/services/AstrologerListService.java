package com.astromock.astromock.services;

import com.astromock.astromock.model.Astrologer;
import com.astromock.astromock.model.AstrologerResponse;
import com.astromock.astromock.repository.AstrologerListRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class AstrologerListService {

    private final AstrologerListRepository astrologerRepository;

    // Constructor Injection (Best Practice)
    public AstrologerListService(AstrologerListRepository astrologerRepository) {
        this.astrologerRepository = astrologerRepository;
    }

    public List<AstrologerResponse> getAllAstrologers() {

        List<Astrologer> list = astrologerRepository.findAll();

        return list.stream().map(astro -> {

            AstrologerResponse dto = new AstrologerResponse();

            dto.setId(astro.getId());
            dto.setName(astro.getName());
            dto.setPhone(astro.getPhone());
            dto.setExperience(astro.getExperience());
            dto.setDescription(astro.getDescription());

            if (astro.getPhoto() != null) {
                dto.setPhoto(
                        Base64.getEncoder()
                                .encodeToString(astro.getPhoto())
                );
            }

            return dto;

        }).toList(); // cleaner
    }
}