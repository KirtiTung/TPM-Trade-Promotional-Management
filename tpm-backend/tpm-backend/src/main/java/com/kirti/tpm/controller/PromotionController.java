package com.kirti.tpm.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirti.tpm.dto.ApiResponse;
import com.kirti.tpm.dto.ProductDTO;
import com.kirti.tpm.dto.PromotionRequest;
import com.kirti.tpm.entity.Promotion;
import com.kirti.tpm.entity.PromotionProduct;
import com.kirti.tpm.entity.PromotionStatus;
import com.kirti.tpm.service.ProductClient;
import com.kirti.tpm.service.PromotionService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/promotions")
public class PromotionController {
    
    private final PromotionService service;
    private final ProductClient productClient;

    public PromotionController(PromotionService service,ProductClient productClient){
        this.service=service;
        this.productClient=productClient;
    }
    @PostMapping
    public Promotion create(@RequestBody @Valid PromotionRequest request){
        
        Promotion promotion=new Promotion();
        promotion.setName(request.getName());
        promotion.setDiscount(request.getDiscount());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotion.setStatus(request.getStatus());

        return service.create(promotion);
    }

    @GetMapping
    public ApiResponse<List<Promotion>> getAll(){
        return new ApiResponse<>("Fetched all promotions",service.getAll());
    }
    
    @GetMapping("/{id}")
    public ApiResponse<Promotion> getById(@PathVariable Long id){
        return new ApiResponse<Promotion>("Fetched promotion by id", service.getById(id)
        .orElseThrow(()-> new RuntimeException("Promotion not Found")));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteById(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<String>("Deleated Successfully", null);
    }

    @PutMapping("/{id}")
    public Promotion update(@PathVariable Long id,@RequestBody @Valid PromotionRequest promotion){
        return service.updatePromotion(id,promotion);
    }

    @PutMapping("/{id}/approve")
    public Promotion approve(@PathVariable Long id){
        return service.approvePromotion(id);
    }

    @PutMapping("/{id}/active")
    public Promotion active(@PathVariable Long id){
        return service.activePromotion(id);
    }

    @GetMapping("/status/{status}")
    public List<Promotion> getByStatus(@PathVariable PromotionStatus status) {
        return service.findByStatus(status);
    }
    
    @GetMapping("/active")
    public List<Promotion> getactivePromotions(){
        return service.findByStatus(PromotionStatus.ACTIVE);
    }

    @GetMapping("/date-range")
    public List<Promotion> getByDateRange(@RequestParam LocalDate start,@RequestParam LocalDate end){
        return service.findByDateRange(start,end);
    }

    @GetMapping("/filter")
    public List<Promotion> getByFilter(@RequestParam(required = false) LocalDate start,
    @RequestParam(required = false) LocalDate end,
    @RequestParam(required = false) PromotionStatus status){
        return service.filterPromotion(start,end,status);
    }

    @GetMapping("/pagination")
    public Page<Promotion> getPaginatedPromotions(@RequestParam int page,@RequestParam int size){
        return service.getPaginatedPromotions(page,size);
    }
    @GetMapping("/pagination-filter")
    public Page<Promotion> getPaginatedFilterPoromotionsPage(
        @RequestParam(required = false)  PromotionStatus status,
        @RequestParam(required = false) LocalDate start,
        @RequestParam(required = false) LocalDate end,
        @RequestParam int page,
        @RequestParam int size) {
        return service.getPaginatedFilterPoromotionsPage(status,start,end,page,size);
    }

    @GetMapping("/product/{id}")
    public ProductDTO getMethodName(@PathVariable int id) {
        return productClient.getProductBySku(id);
    }
    
    @GetMapping("/product/all")
    public List<ProductDTO> getAllProducts(){
        return productClient.getAllProducts();
    }
    
}
