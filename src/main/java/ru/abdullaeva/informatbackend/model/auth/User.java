package ru.abdullaeva.informatbackend.model.auth;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.abdullaeva.informatbackend.model.base.Variant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Поле не должно быть пустым")
    @Column(name = "login", unique = true)
    private String login;

    @NotBlank(message = "Поле не должно быть пустым")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Поле не должно быть пустым")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Поле не должно быть пустым")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Поле не должно быть пустым")
    @Column(name = "phone")
    private String phone;

    @ManyToOne
    private Role role;

    @Column(name = "active")
    private boolean active;

    @Column(name = "blocked")
    private boolean blocked;

    @JsonManagedReference
    @ManyToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_variant",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "variant_id", referencedColumnName="id"))
    private List<Variant> variants;

}