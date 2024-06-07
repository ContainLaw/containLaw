package org.kangnam.containlaw.service;

import lombok.extern.slf4j.Slf4j;
import org.kangnam.containlaw.Dto.NsmLeg.LsmLegRes;
import org.kangnam.containlaw.Dto.NsmLeg.Proposer;
import org.kangnam.containlaw.Dto.ResponseData;
import org.kangnam.containlaw.api.LsmLeg.LsmLegAPIService;
import org.kangnam.containlaw.api.Profile.ProfileService;
import org.kangnam.containlaw.entity.Bill;
import org.kangnam.containlaw.entity.BillMemberProfile;
import org.kangnam.containlaw.entity.Category;
import org.kangnam.containlaw.entity.MemberProfile;
import org.kangnam.containlaw.repository.BillMemberProfileRepositoryImpl;
import org.kangnam.containlaw.repository.BillRepositoryImpl;
import org.kangnam.containlaw.repository.CategoryRepositoryImpl;
import org.kangnam.containlaw.repository.MemberProfileRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class BillService {

    private final BillRepositoryImpl billRepository;
    private final LsmLegAPIService lsmLegAPI;
    private final MemberProfileRepositoryImpl memberProfileRepository;
    private final ChatService chatService;
    private final CategoryRepositoryImpl categoryRepository;
    private final ProfileService profileService;
    private final BillMemberProfileRepositoryImpl billMemberProfileRepository;

    @Autowired
    public BillService(BillRepositoryImpl billRepository, LsmLegAPIService lsmLegAPI, MemberProfileRepositoryImpl memberProfileRepository,
                       ChatService chatService, CategoryRepositoryImpl categoryRepository, ProfileService profileService,
                       BillMemberProfileRepositoryImpl billMemberProfileRepository) {
        this.billRepository = billRepository;
        this.lsmLegAPI = lsmLegAPI;
        this.memberProfileRepository = memberProfileRepository;
        this.chatService = chatService;
        this.categoryRepository = categoryRepository;
        this.profileService = profileService;
        this.billMemberProfileRepository = billMemberProfileRepository;
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Transactional
    public void saveBill(LsmLegRes.LsmLeg lsmLegFormRow) throws IOException, InterruptedException {
        if (!billRepository.existsByBillId(lsmLegFormRow.getBillId())) {
            Bill bill = createBillWithMemberProfiles(lsmLegFormRow);
            billRepository.saveAndFlush(bill);  // Bill 엔티티를 먼저 저장 및 플러시

            // 각 BillMemberProfile 엔티티를 명시적으로 저장
            for (BillMemberProfile billMemberProfile : bill.getBillMemberProfiles()) {
                // 중복 확인 후 저장
                if (!billMemberProfileRepository.existsByBillAndMemberProfile(
                        billMemberProfile.getBill(), billMemberProfile.getMemberProfile())) {
                    billMemberProfileRepository.save(billMemberProfile);
                }
            }
        } else {
            log.info("법안 ID: {}는 이미 존재합니다", lsmLegFormRow.getBillId());
        }
    }

    public List<Bill> findByName(String name) {
        return billRepository.findByBillNameContaining(name);
    }

    public Bill findById(String id) {
        return billRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bill not found"));
    }

    @Transactional
    public Bill createBillWithMemberProfiles(LsmLegRes.LsmLeg lsmLeg) throws IOException, InterruptedException {
        String content = lsmLegAPI.getLsmLegContent(lsmLeg.getBillId());
        ResponseData responseData = chatService.getAllInformation(content);

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

        bill.setSummary(responseData.getSummary());
        bill.setAdvantages(responseData.getAdvantages());
        bill.setDisadvantages(responseData.getDisadvantages());

        List<String> categoriesFromChatGPT = Arrays.asList(responseData.getCategory().split(",\\s*"));
        Set<Category> categorySet = new HashSet<>();
        for (String categoryName : categoriesFromChatGPT) {
            Category category = categoryRepository.findByName(categoryName)
                    .orElseGet(() -> {
                        Category newCategory = new Category();
                        newCategory.setName(categoryName);
                        return categoryRepository.save(newCategory);
                    });
            categorySet.add(category);
        }
        bill.setCategories(new ArrayList<>(categorySet));

        List<Proposer> proposerList = lsmLegAPI.getProposerList(lsmLeg);
        for (Proposer proposer : proposerList) {
            String proposerName = proposer.getName();
            Optional<MemberProfile> optionalMemberProfile = memberProfileRepository.findByName(proposerName);
            if (optionalMemberProfile.isPresent()) {
                MemberProfile memberProfile = optionalMemberProfile.get();
                boolean exists = bill.getBillMemberProfiles().stream()
                        .anyMatch(bmp -> bmp.getMemberProfile().getId().equals(memberProfile.getId()));
                if (!exists) {
                    BillMemberProfile billMemberProfile = new BillMemberProfile();
                    billMemberProfile.setBill(bill);
                    billMemberProfile.setMemberProfile(memberProfile);
                    bill.getBillMemberProfiles().add(billMemberProfile);
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
