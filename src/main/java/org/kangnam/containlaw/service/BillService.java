package org.kangnam.containlaw.service;


import lombok.extern.slf4j.Slf4j;
import org.kangnam.containlaw.Dto.NsmLeg.LsmLegRes;
import org.kangnam.containlaw.Dto.NsmLeg.Proposer;
import org.kangnam.containlaw.api.LsmLeg.LsmLegAPIService;
import org.kangnam.containlaw.entity.Bill;
import org.kangnam.containlaw.entity.BillMemberProfile;
import org.kangnam.containlaw.entity.MemberProfile;
import org.kangnam.containlaw.repository.BillRepositoryImpl;
import org.kangnam.containlaw.repository.MemberProfileRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BillService {

    @Autowired
    private BillRepositoryImpl billRepository;

//    @Autowired
//    private ChatService chatService;

    @Autowired
    private LsmLegAPIService lsmLegAPI;

    @Autowired
    private MemberProfileRepositoryImpl memberProfileRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Transactional
    public void saveBill(LsmLegRes.LsmLeg lsmLegFormRow) {
        Bill bill = createBillWithMemberProfiles(lsmLegFormRow);
        billRepository.save(bill);
    }

    public List<Bill> findByName(String name) {
        return billRepository.findByBillNameContaining(name);
    }

    public Bill findById(String id) {
        return billRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bill not found"));
    }

    @Transactional
    public Bill createBillWithMemberProfiles(LsmLegRes.LsmLeg lsmLeg){

        //String content = lsmLegAPI.getLsmLegContent(lsmLeg.getBillId());

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

        List<Proposer> proposerList = lsmLegAPI.getProposerList(lsmLeg);
        for (Proposer proposer : proposerList) {
            String proposerName = proposer.getName();
            Optional<MemberProfile> optionalMemberProfile = memberProfileRepository.findByName(proposerName);
            if (optionalMemberProfile.isPresent()) {
                MemberProfile memberProfile = optionalMemberProfile.get();
                // 중복 확인
                boolean exists = bill.getBillMemberProfiles().stream()
                        .anyMatch(bmp -> bmp.getMemberProfile().getId().equals(memberProfile.getId()));
                if (!exists) {
                    BillMemberProfile billMemberProfile = new BillMemberProfile();
                    billMemberProfile.setBill(bill);
                    billMemberProfile.setMemberProfile(memberProfile);
                    bill.getBillMemberProfiles().add(billMemberProfile);
                    memberProfile.getBillMemberProfiles().add(billMemberProfile);
                }
            }
        }
        return bill;
    }

    private static LocalDate parseDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    public Bill getBillById(String id) {
        return billRepository.findByBillId(id)
                .orElseThrow(() -> new IllegalArgumentException("Bill not found"));
    }

}