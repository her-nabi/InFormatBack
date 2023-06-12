package ru.abdullaeva.informatbackend.model.base;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.abdullaeva.informatbackend.model.auth.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "variant")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Variant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Поле не должно быть пустым")
    @Column(name = "name")
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "variants", fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH)
    private List<Task> tasks;

    @JsonBackReference
    @ManyToMany(mappedBy = "variants")
    private List<User> users ;

}
