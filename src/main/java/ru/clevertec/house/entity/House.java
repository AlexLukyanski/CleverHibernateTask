package ru.clevertec.house.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "\"House\"")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID uuid;

    @Column(name = "area")
    @NotEmpty(message = "Cannot be empty")
    private BigDecimal area;

    @Column(name = "country")
    @NotEmpty (message = "Cannot be empty")
    private String country;

    @Column(name = "city")
    @NotEmpty (message = "Cannot be empty")
    private String city;

    @Column(name = "street")
    @NotEmpty (message = "Cannot be empty")
    private String street;

    @Column(name = "number")
    @NotEmpty (message = "Cannot be empty")
    private int number;

    @CreationTimestamp
    @Column(name = "create_date")
    @NotEmpty (message = "Cannot be empty")
    private LocalDateTime createDate;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "home",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Person> residents;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "m2m_house_person",
            joinColumns = @JoinColumn(name = "house"),
            inverseJoinColumns = @JoinColumn(name = "person")
    )
    private List<Person> owners;
}
