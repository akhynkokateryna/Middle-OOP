package company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table
@Entity

public class Company {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)  //maybe will be needed
    private String name;

    private String domain;
    private String twitter;
    private String facebook;
    private String logo;
    private String icon;
    private String employees;
    private String address;
}
