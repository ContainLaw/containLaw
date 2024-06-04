package org.kangnam.containlaw.service;

import org.kangnam.containlaw.entity.Bill;
import org.kangnam.containlaw.entity.MemberProfile;
import org.kangnam.containlaw.repository.BillRepositoryImpl;
import org.kangnam.containlaw.repository.MemberProfileRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.kangnam.containlaw.Dto.NsmLeg.Proposer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


@Service
public class ProposerService {

    @Autowired
    private BillRepositoryImpl billRepository;

    @Autowired
    private MemberProfileRepositoryImpl memberProfileRepository;

    @Transactional
    public void proposeBill(Bill bill) {
        // Bill 엔티티 저장
        billRepository.save(bill);

        // proposerList에서 MemberProfile 조회
        List<MemberProfile> proposers = bill.getProposerList().stream()
                .map(Proposer::getName)
                .map(name -> memberProfileRepository.findByName(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        // MemberProfile 처리 로직
        proposers.forEach(proposer -> {
            // 예: proposer 정보를 사용하여 추가 작업 수행
        });
    }
}

