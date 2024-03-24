package com.apitest.TestSping.Cotroller;


import com.apitest.TestSping.Reponsitory.ProductReponsitory;
import com.apitest.TestSping.entity.Product;
import com.apitest.TestSping.entity.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductCotroller {
    //auto tạo reponstitory khi được bật lên
    @Autowired
    private ProductReponsitory reponsitory;


    // this request is ://http/localhost:8080/api/v1/product
    @GetMapping("")
    List<Product>getAllProduct(){
        return reponsitory.findAll();
                //List.of(new Product(1L,"App",2020,"cbsdfbbd",200000),
                  //     new Product(2L,"App",2020,"ababab",999));

    }
    // lấy chi tiết product.
    @GetMapping("/{id}")
    // cách một trả về 1 op miễn sao khác null
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = reponsitory.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "successfull request", foundProduct)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false", "Can not find id: " + id, foundProduct));
    }
        /* đoạn trên toi giản hơn
        if(foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "successfull request", foundProduct)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Can not find id: "+id,foundProduct)
            );
        }*/

    //cách 2 c thể để trả về kiểu null nếu không tìm thấy
   /* Optional<Product> findById(@PathVariable Long id){
        // ta bao ngoài bằng đối tượng optional là cái api đó cí thể trả về là null.
        return reponsitory.findById(id);
    }*/
    //====================== insert new product with Post methus
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
        // lấy ra danh sách product để so sánh trước xem đã tồn tại chưa
        List <Product> foundProduct = reponsitory.findByProductName(newProduct.getProductName().trim());
        if(foundProduct.size()>0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("false","product already taken","")
            );
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok","insert product in database",reponsitory.save(newProduct))
            );
        }

    }
    //updata product( if not find id of product, insert product new
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
        Product foundProduct = reponsitory.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setNam(newProduct.getNam());
                    product.setUrl(newProduct.getUrl());
                    product.setPrice(newProduct.getPrice());
                    return reponsitory.save(product);
                }).orElseGet(()->{
                    newProduct.setId(id);
                    return reponsitory.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "successfull update product", foundProduct));

    }
    // delete product
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        Boolean exits = reponsitory.existsById(id);
        if(exits){
            reponsitory.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("oke","successfull delete product","")
            );
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false","No find id of product","")
            );
        }

    }
}



