package org.kangnam.containlaw.controller;

import org.kangnam.containlaw.entity.Bill;
import org.kangnam.containlaw.entity.MemberProfile;
import org.kangnam.containlaw.service.BillService;
import org.kangnam.containlaw.service.MemberProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private MemberProfileService memberProfileService;

    @GetMapping("/search")
    public ResponseEntity<List<Bill>> searchBillsByName(@RequestParam String name) {
        List<Bill> bills = billService.findByName(name);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable String id) {
        Bill bill = billService.findById(id);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/proposer/{name}")
    public ResponseEntity<MemberProfile> getProfileByProposerName(@PathVariable String name) {
        MemberProfile profile = memberProfileService.findByName(name);
        return ResponseEntity.ok(profile);
    }
}
