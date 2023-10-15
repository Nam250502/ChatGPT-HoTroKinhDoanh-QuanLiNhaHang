package nam.nguyen.store.controller;

import nam.nguyen.store.model.Invoice;
import nam.nguyen.store.service.CategoryService;
import nam.nguyen.store.service.DiningTableService;
import nam.nguyen.store.service.InvoiceService;
import nam.nguyen.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    DiningTableService tableService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/addtable/{quantity}")
    public String setTable(@PathVariable("quantity") Integer quantity) {
        tableService.setTable(quantity);
        return "redirect:/customer/home";
    }
    @GetMapping("/home")
    public String viewHome(Model model) {
        List<Invoice> invoices = invoiceService.getInvoiceToday();
        model.addAttribute("invoices", invoices);
        return "staff_home";
    }
    @GetMapping("/detailtable/{id}")
    public String detailTable(Model model , @PathVariable("id")Integer id) {
       Invoice invoice = invoiceService.getInvoiceById(id);
        model.addAttribute("invoice", invoice);
        return "detail-table";
    }
    @GetMapping("/confirmOrder/{id}")
    public String confirmOrder(@PathVariable("id") Integer id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice.getStatusService() < 4 && invoice.getStatusService() >= 0) {
            invoice.setStatusService(invoice.getStatusService() + 1);
            invoiceService.saveInvoice(invoice);
        }

        return "redirect:/staff/detailtable/" + invoice.getId();
    }

    @GetMapping("/viewmenu")
    public  String viewMenu(Model model){
        model.addAttribute("menu",productService.getAllProducts());
        return "staffmenu";
    }


}
