package com.apitest.TestSping.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity//(name = "PRODUCT")// để chỉ nó là entity
@Table(name= "tblProduct")
public class Product {

    @Id// chỉ khóa chính
    @GeneratedValue(strategy = GenerationType.AUTO)// id tự sinh tăng lên.
    private Long id;
    // validate: thieets lập các ràng buộc trong bảng.
    @Column(nullable = false,unique = true,length = 3000)
    // giá trị đầu là ko để null, caias thứ 2 ko thể trùng với những cái đã có.
    private String productName;
    private int nam;
    private String url;
    private int price;
    public Product() {

    }
    public Product(String prodname, int year, String url, int price) {
        this.productName = prodname;
        this.nam = year;
        this.url = url;
        this.price = price;
    }

    @Override
    public String toString(){
        return "Product{"+
                "id=" +id+
                "proname="+productName+
                "year="+nam+
                "url='"+url+'\''+
                "price="+price+
                "}";
    }
// định nghĩa hai product thế nào là giống nhau.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                Objects.equals(id, product.id) &&
                Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price);
    }
}
