package company;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table
@Entity
@Builder
public class Company {
    @Id
    @GeneratedValue
    public int id;

    @Column(unique = true)  //maybe will be needed
    public String name;

    public String domain;
    public String twitter;
    public String facebook;
    public String logo;
    public String icon;
    public String employees;
    public String address;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", twitter='" + twitter + '\'' +
                ", facebook='" + facebook + '\'' +
                ", logo='" + logo + '\'' +
                ", icon='" + icon + '\'' +
                ", employees='" + employees + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
