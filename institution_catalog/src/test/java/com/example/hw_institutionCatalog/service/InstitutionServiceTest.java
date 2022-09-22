package com.example.hw_institutionCatalog.service;

import com.example.hw_institutionCatalog.AppContextTest;
import com.example.hw_institutionCatalog.dto.in.DeleteOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.entity.CuisineType;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.FoundationDateIsExpiredException;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.repository.InstitutionRepository;
import com.example.hw_institutionCatalog.repository.ReviewRepository;
import com.google.i18n.phonenumbers.NumberParseException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InstitutionServiceTest extends AppContextTest {
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    Institution institutionWithReview;

    @Test
    void getAll() {
        Page<Institution> all = institutionService.getAll(Pageable.unpaged());
        assertFalse(all.isEmpty());
        System.out.println(all);
    }

    @Test
    void addInstitution() throws FoundationDateIsExpiredException, InstitutionNotFoundException, NumberParseException {
        MockedStatic<LocalDate> localDateMockedStatic = mockStatic(LocalDate.class, CALLS_REAL_METHODS);
        LocalDate defaultNow = LocalDate.of(2022, 6, 7);
        localDateMockedStatic.when(LocalDate::now).thenReturn(defaultNow);

        InstitutionInDto institutionInDtoAfter = InstitutionInDto.builder()
                .name("test")
                .address( "address_test")
                .description("description_test")
                .telephoneNumber("8 958 526 33 63")
                .foundationDate(LocalDate.of(2022, 8, 26))
                .cuisineType(CuisineType.RUS)
                .build();
        Assertions.assertThrowsExactly(FoundationDateIsExpiredException.class,
                () -> institutionService.addInstitution(institutionInDtoAfter),
                "Restaurant with name \"" + "test" + "\"" +
                        "has foundation date " + LocalDate.now());
        InstitutionInDto institutionInDtoPrevious = InstitutionInDto.builder()
                .name("test")
                .address( "address_test")
                .description("description_test")
                .telephoneNumber("+79585263363")
                .foundationDate(LocalDate.of(2012, 12, 12))
                .build();

        Institution institution = institutionService.addInstitution(institutionInDtoPrevious);
        LocalDate foundationDate = institutionService.getFoundationDate(institution.getId());
        assertEquals(LocalDate.of(2012, 12, 12), foundationDate);
    }

    @Test
    void getDescriptionInstitutionById() throws InstitutionNotFoundException {
        int id = 1;
        Institution institution = institutionService.getInstitutionById(id);
        assertEquals(institution.getId(), id);
        System.out.println(institution.getDescription());
    }

    @Test
    void refactorInstitutionById() throws InstitutionNotFoundException {
        int id = 5;
        String description = "description_test";
        String oldDescription = institutionService.getInstitutionById(id).getDescription();
        institutionService.refactorInstitutionById(id, description);
        String newDescription = institutionService.getInstitutionById(id).getDescription();
        assertNotEquals(oldDescription, newDescription);
    }

    @Test
    void deleteOwner() throws FoundationDateIsExpiredException, NumberParseException, InstitutionNotFoundException {
        InstitutionInDto institutionInDto1 = InstitutionInDto.builder()
                .name("Test1")
                .telephoneNumber("+79999999999")
                .address("zzzzzzzzzzzzz")
                .foundationDate(LocalDate.of(2016, 01, 07))
                .ownerId(15)
                .build();
        institutionService.addInstitution(institutionInDto1);
        InstitutionInDto institutionInDto2 = InstitutionInDto.builder()
                .name("Test2")
                .telephoneNumber("+79999999999")
                .address("zzzzzzzzzzzzz")
                .foundationDate(LocalDate.of(2015, 01, 07))
                .ownerId(15)
                .build();
        institutionService.addInstitution(institutionInDto2);
        DeleteOwnerInDto deleteOwnerInDto = new DeleteOwnerInDto();
        deleteOwnerInDto.setOwnerId(15);
        assertEquals(2, institutionService.getInstitutionByOwnerId(15).size());
        institutionService.deleteOwner(deleteOwnerInDto);
        assertEquals(0, institutionService.getInstitutionByOwnerId(15).size());
    }

    /**
     * demonstration cascade parameter working
     */
    @Test
    @Transactional
    public void testCascade() {

        Institution institution = new Institution();
        institution.setName("test");
        institution.setFoundationDate(LocalDate.now().minusDays(3));
        institution.setTelephoneNumber("+79999999999");
//        Institution institutionSave = institutionRepository.save(institution);

        Review review1 = new Review();
        review1.setReview("qqq");
        review1.setInstitution(institution);
        review1.setRating(1);

        Review review2 = new Review();
        review2.setReview("qqq");
        review2.setInstitution(institution);
        review2.setRating(1);

        List<Review> list = Lists.list(review1, review2);

        institution.setReviewList(list);

        Institution save = institutionRepository.save(institution);
        Optional<Institution> byId = institutionRepository.findById(save.getId());
        assertTrue(byId.isPresent());
        List<Review> all = reviewRepository.findAllByInstitutionId(byId.get().getId());
        Assertions.assertEquals(2, all.size());
    }

    @BeforeAll
    public void beforeAll() {
        Institution institution = new Institution();
        institution.setName("test");
        institution.setFoundationDate(LocalDate.now().minusDays(3));
        institution.setTelephoneNumber("+79999999999");

        Review review1 = new Review();
        review1.setReview("qqq");
        review1.setInstitution(institution);
        review1.setRating(1);

        Review review2 = new Review();
        review2.setReview("fff");
        review2.setInstitution(institution);
        review2.setRating(2);

        Review review3 = new Review();
        review3.setReview("qqq");
        review3.setInstitution(institution);
        review3.setRating(1);

        List<Review> list = Lists.list(review1, review2, review3);
        institution.setReviewList(list);
        institutionWithReview = institutionRepository.save(institution);
    }

    /**
     * demonstrate n+1 problem. get restaurants in loop - produce n selects (but we can do it with one select).
     */
    @Test
    @Transactional
    public void testN1() {
        Page<Review> allByRestaurantId = reviewRepository.findAllById(institutionWithReview.getId(), Pageable.unpaged()).get();
        for (Review e : allByRestaurantId) {
            System.out.println(e.getRating());
            System.out.println(e.getReview());
            System.out.println(e.getInstitution().getName());
        }
    }

    /**
     * demonstration fetch type. see sql logs to understand difference between fetch type
     * set fetch type in {@link Institution} field reviews.
     * @throws InstitutionNotFoundException if restaurant not found
     */
    @Test
    @Transactional
    public void testLazy() throws InstitutionNotFoundException {
        Institution institution = institutionService.getInstitutionById(institutionWithReview.getId());
        for (Review e : institution.getReviewList()) {
            System.out.println(e.getRating());
            System.out.println(e.getReview());
            System.out.println(e.getInstitution().getName());
        }
    }
}
