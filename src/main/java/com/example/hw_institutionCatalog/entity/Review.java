package com.example.hw_institutionCatalog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "institution_id", nullable = false)
//    private Institution institutionId;

    @Column(name = "institution_id")
    private Integer institutionId;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "review")
    private String review;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review1 = (Review) o;
        return Objects.equals(id, review1.id) && Objects.equals(institutionId, review1.institutionId) && Objects.equals(rating, review1.rating) && Objects.equals(review, review1.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, institutionId, rating, review);
    }
}
