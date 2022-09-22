package com.example.hw_institutionCatalog.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id", nullable = false)
    private Institution institution;

    //TODO колонка возможно не нужна
//    @Column(name = "institution_id")
//    private Integer institutionId;

    @Column(name = "rating")
    @Min(0)
    @Max(5)
    private Integer rating;

    @Column(name = "review")
    private String review;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review1 = (Review) o;
        return Objects.equals(id, review1.id);
    }

    @Override
    public int hashCode() {
        return 67;
    }
}
