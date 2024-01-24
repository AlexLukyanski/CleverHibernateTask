package ru.clevertec.house.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import ru.clevertec.house.util.constant.Sex;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "\"Person\"")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID uuid;

    @Column(name = "name")
    @NotEmpty (message = "Cannot be empty")
    private String name;

    @Column(name = "surname")
    @NotEmpty (message = "Cannot be empty")
    private String surname;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    @NotEmpty (message = "Cannot be empty")
    private Sex sex;

    @Column(name = "passport_series")
    @NotEmpty (message = "Cannot be empty")
    private String passportSeries;

    @Column(name = "passport_number")
    @NotEmpty (message = "Cannot be empty")
    private String passportNumber;

    @CreationTimestamp
    @Column(name = "create_date")
    @NotEmpty (message = "Cannot be empty")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    @NotEmpty (message = "Cannot be empty")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "home")
    private House home;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "m2m_house_person",
            joinColumns = @JoinColumn(name = "person"),
            inverseJoinColumns = @JoinColumn(name = "house")
    )
    private List<House> ownedHouses;
}
