package nam.nguyen.store.controller;

import nam.nguyen.store.model.Invoice;
import nam.nguyen.store.service.DiningTableService;
import nam.nguyen.store.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    DiningTableService tableService;
    @Autowired
    private InvoiceService invoiceService;
    @GetMapping("/addtable/{quantity}")
    public String setTable(@PathVariable("quantity") Integer quantity) {
        tableService.setTable(quantity);
        return "redirect:/customer/home";
    }
    @GetMapping("/home")
    public String viewHome(Model model) {
        List<Invoice> invoices = invoiceService.getAllInvoice();
        model.addAttribute("invoices", invoices);
        return "staff_home";
    }
}
