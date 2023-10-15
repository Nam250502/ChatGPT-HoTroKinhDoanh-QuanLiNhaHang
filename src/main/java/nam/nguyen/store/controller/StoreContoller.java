package nam.nguyen.store.controller;

import nam.nguyen.store.model.Product;
import nam.nguyen.store.service.CategoryService;
import nam.nguyen.store.service.InvoiceService;
import nam.nguyen.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/store")
public class StoreContoller {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/editfood/{id}")
    public  String editFood(Model model , @PathVariable("id") Integer id){
        model.addAttribute("food",productService.getProductById(id));
        model.addAttribute("category",categoryService.getAllCategorys());
        return "staffeditfood";
    }
    @PostMapping("/updatefood")
    public String editFood(@ModelAttribute Product product, @RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String fileName = "/images/products/" + file.getOriginalFilename();
                Path path = Paths.get("src/main/resources/static" + fileName);
                Files.write(path, file.getBytes());
                product.setUrl(fileName);
            }
            productService.saveProduct(product);
            return "redirect:/staff/viewmenu";
        } catch (IOException e) {
            // Xử lý lỗi khi lưu tệp
            e.printStackTrace();
            return "errorPage";
        }
    }
    @GetMapping("/addfood")
    public  String addFood(Model model ){
        Product product = new Product();
        model.addAttribute("food",product);
        model.addAttribute("category",categoryService.getAllCategorys());
        return "store-edit-food";
    }
}
