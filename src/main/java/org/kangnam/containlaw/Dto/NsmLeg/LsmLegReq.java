package org.kangnam.containlaw.Dto.NsmLeg;

import lombok.Setter;

@Setter
public class LsmLegReq {
    String Type;
    String pIndex;
    String pSize;
    String BILL_ID = "";
    String BILL_NO = "";
    String AGE = "";
    String BILL_NAMESTRING = "";
    String PROPOSER = "";
    String PROPOSER_KIND = "";
    String CURR_COMMITTEE_ID = "";
    String CURR_COMMITTEE = "";
    String PROC_RESULT_CD = "";
    String PROC_DT = "";

    public LsmLegReq() {
        this.Type="xml";
        this.pIndex="1";
        this.pSize="20";
        this.AGE="21";
    }

    @Override
    public String toString() {
        return  "&Type=" + Type +
                "&pIndex=" + pIndex +
                "&pSize=" + pSize +
                "&BILL_ID=" + BILL_ID +
                "&BILL_NO=" + BILL_NO +
                "&AGE=" + AGE +
                "&BILL_NAMESTRING=" + BILL_NAMESTRING +
                "&PROPOSER=" + PROPOSER +
                "&PROPOSER_KIND=" + PROPOSER_KIND +
                "&CURR_COMMITTEE_ID=" + CURR_COMMITTEE_ID +
                "&CURR_COMMITTEE=" + CURR_COMMITTEE +
                "&PROC_RESULT_CD=" + PROC_RESULT_CD +
                "&PROC_DT=" + PROC_DT;
    }
    /*
    [기본 인자]
    변수명	타입	변수         설명	            설명
    KEY	    STRING(필수)	    인증키	            기본값 : sample key
    Type	STRING(필수)	    호출 문서(xml, json)	기본값 : xml
    pIndex	INTEGER(필수)	페이지 위치	        기본값 : 1(sample key는 1 고정)
    pSize	INTEGER(필수)	페이지 당 요청 숫자	기본값 : 100(sample key는 5 고정)

    [요청 인자]
    변수명	            타입	        변수설명     변수예시
    BILL_ID	            STRING(선택)	의안ID	    BILL_ID='PRC_Z2Z1Z0Z3X2L4M0H9A2V6K5R0V7P2H1'
    BILL_NO	            STRING(선택)	의안번호	    BILL_NO='2114286'
    AGE	                STRING(선택)	대	        AGE='21'
    BILL_NAME           STRING(선택)	의안명(한글)	BILL_NAME='의안명(한글) 검색어' (예시) BILL_NAME="80년
    PROPOSER	        STRING(선택)	제안자	    PROPOSER='제안자 검색어' (예시) PROPOSER=2012년
    PROPOSER_KIND	    STRING(선택)	제안자구분	PROPOSER_KIND='정부'
    CURR_COMMITTEE_ID	STRING(선택)	소관위코드	CURR_COMMITTEE_ID='B002368'
    CURR_COMMITTEE	    STRING(선택)	소관위	    CURR_COMMITTEE='소관위 검색어' (예시) CURR_COMMITTEE=2002
    PROC_RESULT_CD	    STRING(선택)	본회의심의결과	PROC_RESULT_CD='회기불계속폐기'
    PROC_DT	            STRING(선택)	의결일	    PROC_DT='2021-12-31'

    [출력값]
    No	출력명	            출력 설명
    1	BILL_ID	            의안ID
    2	BILL_NO	            의안번호
    3	AGE	                대
    4	BILL_NAME	        의안명(한글)
    5	PROPOSER	        제안자
    6	PROPOSER_KIND	    제안자구분
    7	PROPOSE_DT	        제안일
    8	CURR_COMMITTEE_ID	소관위코드
    9	CURR_COMMITTEE	    소관위
    10	COMMITTEE_DT	    소관위회부일
    11	COMMITTEE_PROC_DT	위원회심사_처리일
    12	LINK_URL	        의안상세정보_URL
    13	RST_PROPOSER	    대표발의자
    14	LAW_PROC_RESULT_CD	법사위처리결과
    15	LAW_PROC_DT	        법사위처리일
    16	LAW_PRESENT_DT	    법사위상정일
    17	LAW_SUBMIT_DT	    법사위회부일
    18	CMT_PROC_RESULT_CD	소관위처리결과
    19	CMT_PROC_DT	        소관위처리일
    20	CMT_PRESENT_DT	    소관위상정일
    21	RST_MONA_CD	        대표발의자코드
    22	PROC_RESULT_CD	    본회의심의결과
    23	PROC_DT	            의결일
    */
}
