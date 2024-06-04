package org.kangnam.containlaw.service;


import lombok.extern.slf4j.Slf4j;
import org.kangnam.containlaw.Dto.NsmLeg.LsmLegRes;
import org.kangnam.containlaw.entity.Bill;
import org.kangnam.containlaw.repository.BillRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class BillService {

    @Autowired
    private BillRepositoryImpl billRepository;

    @Transactional
    public Bill saveBill(LsmLegRes.LsmLeg lsmLegFormRow) {
        Bill bill = createBillWithMemberProfiles(lsmLegFormRow);
        return billRepository.save(bill);
    }

    public List<Bill> findByName(String name) {
        return billRepository.findByBillNameContaining(name);
    }

    public Bill findById(String id) {
        return billRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bill not found"));
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Transactional
    public Bill createBillWithMemberProfiles(LsmLegRes.LsmLeg lsmLeg) {
        Bill bill = new Bill();
        bill.setBillId(lsmLeg.getBillId());
        bill.setBillNo(lsmLeg.getBillNo());
        bill.setBillName(lsmLeg.getBillName());
        bill.setProposer(lsmLeg.getProposer());
        bill.setCommittee(lsmLeg.getCurrCommittee());
        bill.setProposeDate(parseDate(lsmLeg.getProposeDt()));
        bill.setProcDate(parseDate(lsmLeg.getProcDt()));
        bill.setProcResultCode(lsmLeg.getProcResultCd());
        bill.setUrl(lsmLeg.getLinkUrl());
        return bill;
    }

    private static LocalDate parseDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    public void saveBill(Bill bill) {
        billRepository.save(bill);
    }
}