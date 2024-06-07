package org.kangnam.containlaw.controller;

import org.kangnam.containlaw.entity.Bill;
import org.kangnam.containlaw.service.BillService;
import org.kangnam.containlaw.service.CategoryService;
import org.kangnam.containlaw.service.MemberProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private CategoryService categoryService;

    //법안이름으로 법안 검색
    @GetMapping("/searchByBill")
    public String searchByBillName(@RequestParam("law") String billName, Model model) {
        List<Bill> bills = billService.findByName(billName);
        model.addAttribute("bills", bills);
        model.addAttribute("searchTerm", billName);
        return "searchByLaw";
    }

    //법안 상세보기
    @GetMapping("/bills/{id}")
    public String showBill(@PathVariable String id, Model model) {
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill", bill);
        model.addAttribute("profiles", bill.getMembers());
        return "bill";
    }

    //카테고리별 법안 검색
    @GetMapping("/category/{category}")
    public String getBillsByCategory(@PathVariable String categoryName, Model model) {
        List<Bill> bills = categoryService.findBillsByCategory(categoryName);
        model.addAttribute("bills", bills);
        model.addAttribute("category", categoryName);
        return "searchByCategory";
    }
}
