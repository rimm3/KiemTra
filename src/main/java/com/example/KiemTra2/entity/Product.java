package com.example.KiemTra2.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String image;
    @Column
    private long price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")

    private Category category;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) { // Add this method
        this.image = image;
    }
}
