package com.example.hw_institutionCatalog.service.impl;

import com.example.hw_institutionCatalog.dto.in.AddOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.ChangeOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.DeleteOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.dto.out.InstitutionSmallOutDto;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.FoundationDateIsExpiredException;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.mapper.InstitutionMapper;
import com.example.hw_institutionCatalog.repository.InstitutionRepository;
import com.example.hw_institutionCatalog.repository.ReviewRepository;
import com.example.hw_institutionCatalog.service.InstitutionService;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final ReviewRepository reviewRepository;
    private final InstitutionMapper institutionMapper;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository, ReviewRepository reviewRepository, InstitutionMapper institutionMapper) {
        this.institutionRepository = institutionRepository;
        this.reviewRepository = reviewRepository;
        this.institutionMapper = institutionMapper;
    }

    @Override
    public Page<Institution> getAll(Pageable pageable) {
        return institutionRepository.findAll(pageable);
    }

    @Override
    public Institution getDescriptionInstitutionById(Integer id) throws InstitutionNotFoundException {
        Optional<Institution> byId = institutionRepository.findById(id);
        if (byId.isEmpty()){
            throw new InstitutionNotFoundException(id);
        }
        return byId.get();
    }


    @Override
    public Institution addInstitution(InstitutionInDto institutionInDto/*String name, String address, String description, LocalDate foundationDate*/) throws FoundationDateIsExpiredException, NumberParseException {
        if (institutionInDto.getFoundationDate() == null|| LocalDate.now().isBefore(institutionInDto.getFoundationDate())){
            throw new FoundationDateIsExpiredException(institutionInDto.getName(), institutionInDto.getFoundationDate());
        }
        Institution institution = institutionMapper.mapInstitutionInDtoToInstitution(institutionInDto);
        return institutionRepository.save(institution);
    }

    @Override
    public LocalDate getFoundationDate(Integer id) throws InstitutionNotFoundException {
        Institution institutionById = getDescriptionInstitutionById(id);
        return institutionById.getFoundationDate();
    }

    @Override
    public void refactorInstitutionById(Integer id, String description) throws InstitutionNotFoundException {
        Optional<Institution> byId = institutionRepository.findById(id);
        if (byId.isPresent()){
            Institution institution = byId.get();
            institution.setDescription(description);
            institutionRepository.save(institution);
        } else {
            throw new InstitutionNotFoundException(id);
        }
    }

    @Override
    public Page<Review> getReviewInstitutionById(Integer id, Pageable pageable) throws InstitutionNotFoundException {
        Optional<Page<Review>> byId = reviewRepository.findAllById(id, pageable);
        if (byId.isPresent()){
            return byId.get();
        }
        throw new InstitutionNotFoundException(id);
    }

    @Override
    public Page<Review> getRatingInstitutionById(Integer id, Pageable pageable) throws InstitutionNotFoundException{
        Optional<Page<Review>> byId = reviewRepository.findAllById(id, pageable);
        if (byId.isPresent()){
            return byId.get();
        }
        throw new InstitutionNotFoundException(id);
    }

    @Override
    public Review addReview(Integer institutionId, Integer rating, String review) throws InstitutionNotFoundException {
        Optional<Institution> byId = institutionRepository.findById(institutionId);
        if (byId.isEmpty()){
            throw new InstitutionNotFoundException(institutionId);
        }
        Institution institution = byId.get();
        Review review1 = Review.builder()
                .institution(institution)
                .rating(rating)
                .review(review)
                .build();
        return reviewRepository.save(review1);
//        Review review1 = Review.builder()
//                .institutionId(institutionId)
//                .rating(rating)
//                .review(review).build();
//        reviewRepository.save(review1);
    }

    @Override
    @Transactional
    public void deleteOwner(DeleteOwnerInDto deleteOwnerInDto) throws InstitutionNotFoundException {
        Integer ownerId = deleteOwnerInDto.getOwnerId();
        List<Institution> institutionList = getInstitutionByOwnerId(ownerId);
        for (Institution i: institutionList) {
            Integer id = i.getId();
            Optional<Institution> byId = institutionRepository.findById(id);
            if(byId.isEmpty()){
                throw  new InstitutionNotFoundException(id);
            }
            byId.get().setOwnerId(null);
        }
    }

    @Override
    public List<Institution> getInstitutionByOwnerId(Integer ownerId) throws InstitutionNotFoundException {
        Optional<List<Institution>> byOwnerId = institutionRepository.findAllByOwnerId(ownerId);
        if(byOwnerId.isEmpty()){
            throw new InstitutionNotFoundException(ownerId);
        }
        return byOwnerId.get();
    }

    @Override
    public void addOwner(AddOwnerInDto addOwnerInDto) throws InstitutionNotFoundException {
        Optional<Institution> byId = institutionRepository.findById(addOwnerInDto.getInstitutionId());
        if(byId.isEmpty()){
            throw new InstitutionNotFoundException(addOwnerInDto.getInstitutionId());
        }
        byId.get().setOwnerId(addOwnerInDto.getOwnerId());
    }

    //TODO два варианта
    @Override
    @Transactional
    public void changeOwner(ChangeOwnerInDto changeOwnerInDto) throws InstitutionNotFoundException {
//        Integer ownerId = changeOwnerInDto.getOldOwnerId();
//        List<Institution> institutionList = getInstitutionByOwnerId(ownerId);
//        for (Institution i: institutionList) {
//            Integer id = i.getId();
//            Optional<Institution> byId = institutionRepository.findById(id);
//            if(byId.isEmpty()){
//                throw  new InstitutionNotFoundException(id);
//            }
//            byId.get().setOwnerId(changeOwnerInDto.getNewOwnerId());
//        }
        institutionRepository.updateUserSetStatusForName(changeOwnerInDto.getNewOwnerId(), changeOwnerInDto.getOldOwnerId());
    }

    @Override
    public List<InstitutionSmallOutDto> getSmallList(Pageable pageable) {
        return institutionMapper.institutionToInstitutionSmallOutDto(institutionRepository.findSmallInstitutions(pageable));
    }

    @Override
    public void deleteInstitutionById(Integer id) {
        institutionRepository.deleteById(id);
    }
}
