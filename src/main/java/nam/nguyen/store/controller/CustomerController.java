package nam.nguyen.store.controller;

import nam.nguyen.store.model.CartItem;
import nam.nguyen.store.model.Invoice;
import nam.nguyen.store.model.InvoiceProduct;
import nam.nguyen.store.model.Product;
import nam.nguyen.store.repository.CategoryRepository;
import nam.nguyen.store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private DiningTableService diningTableService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceProductService invoiceProductService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @GetMapping("/home")
    public String viewTable(Model model){
        model.addAttribute("table" , diningTableService.viewTable());
        return "index";
    }

    @GetMapping("/viewmenu")
    public  String viewMenu(Model model){
        model.addAttribute("menu",productService.getAllProducts());
        return "menu";
    }
    @GetMapping("/viewcart/{id}")
    public String viewCart(Model model , @PathVariable("id") Integer id){
        if(id<=diningTableService.viewTable().size()){
            model.addAttribute("menu",productService.getAllProducts());
            model.addAttribute("all_items_in_shoppingcart",cartItemService.getAllCartItem(id));
            model.addAttribute("category",categoryRepository.findAll());
            model.addAttribute("total_amount",cartItemService.total(id));
            model.addAttribute("idtable",id);
        }

        return "table-oder";
    }
    @PostMapping("/addcart")
    public  String addCart(@RequestParam("idtable") Integer idtable, @RequestParam ("idproduct") Integer idproduct){
        Product product = productService.getProductById(idproduct);
        cartItemService.addProduct(product,idtable);
        return "redirect:/customer/viewcart/"+idtable;
    }
    @PostMapping("/removecart")
    public  String removeCart(@RequestParam("idtable") Integer idtable, @RequestParam ("idproduct") Integer idproduct){
        Product product = productService.getProductById(idproduct);
        cartItemService.removeProduct(product,idtable);
        return "redirect:/customer/viewcart/"+idtable;
    }
    @PostMapping("/updatecart")
    public  String updateCart(@RequestParam("idtable") Integer idtable, @RequestParam ("idproduct") Integer idproduct ,
                              @RequestParam("quantity") Integer quantity){
        Product product = productService.getProductById(idproduct);
        cartItemService.updateProduct(product,idtable,quantity);
        return "redirect:/customer/viewcart/"+idtable;
    }
    @GetMapping("/removeallcart/{idtable}")
    public  String removeallcart(@PathVariable("idtable") Integer idtable){
       cartItemService.removeAllProduct(idtable);
        return "redirect:/customer/viewcart/"+idtable;
    }

    @GetMapping("/datmon/{idtable}")
    public String datMon(@PathVariable("idtable") Integer idtable) {
        List<CartItem> cartItems = cartItemService.getAllCartItem(idtable);
        if (cartItems.size() >= 1) {
            Invoice invoicenew = new Invoice();
            invoicenew.setIdTable(idtable);
            Integer idinvoice = invoiceService.saveInvoice(invoicenew);

            for (CartItem c : cartItems) {
                invoiceProductService.addProduct(c.getProduct(), idinvoice, c.getQuantity());
            }
            cartItemService.removeAllProduct(idtable);

            // Gửi thông báo WebSocket
            messagingTemplate.convertAndSend("/topic/new-order", "Có đơn hàng mới!");

            // Trả về đường dẫn tới hàm viewCart với tham số id để làm mới trang

        }
        return "redirect:/customer/viewcart/" + idtable;
    }



}
