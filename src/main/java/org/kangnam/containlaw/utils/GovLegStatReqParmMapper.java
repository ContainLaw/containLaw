package org.kangnam.containlaw.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class GovLegStatReqParmMapper {
    private static final Map<String, String> lsKndCodeMap = new HashMap<>();
    private static final Map<String, String> cptOfiOrgCodeMap = new HashMap<>();
    private static final Map<String, String> lbPrcStsCodeMap = new HashMap<>();

    static {

        // 법령종류 코드 : lsKndCd
        lsKndCodeMap.put("법률","AA0101");
        lsKndCodeMap.put("대통령령","AA0102");
        lsKndCodeMap.put("총리령","AA0103");
        lsKndCodeMap.put("부령","AA0104");
        lsKndCodeMap.put("대통령훈령","AA0105");
        lsKndCodeMap.put("국무총리훈령","AA0106");

        // 소관부처 코드 : cptOfiOrgCd
        cptOfiOrgCodeMap.put("감사원", "1040000");
        cptOfiOrgCodeMap.put("경찰청", "1320000");
        cptOfiOrgCodeMap.put("고용노동부", "1492000");
        cptOfiOrgCodeMap.put("공정거래위원회", "1130000");
        cptOfiOrgCodeMap.put("과학기술정보통신부", "1721000");
        cptOfiOrgCodeMap.put("관세청", "1220000");
        cptOfiOrgCodeMap.put("교육부", "1342000");
        cptOfiOrgCodeMap.put("국가보훈처", "1180000");
        cptOfiOrgCodeMap.put("국가인권위원회", "1620000");
        cptOfiOrgCodeMap.put("국가정보원", "1030000");
        cptOfiOrgCodeMap.put("국무조정실", "1092000");
        cptOfiOrgCodeMap.put("국무총리", "0000002");
        cptOfiOrgCodeMap.put("국민권익위원회", "1140100");
        cptOfiOrgCodeMap.put("국방부", "1290000");
        cptOfiOrgCodeMap.put("국세청", "1210000");
        cptOfiOrgCodeMap.put("국토교통부", "1613000");
        cptOfiOrgCodeMap.put("국회", "9700000");
        cptOfiOrgCodeMap.put("금융위원회", "1160100");
        cptOfiOrgCodeMap.put("기상청", "1360000");
        cptOfiOrgCodeMap.put("기획재정부", "1051000");
        cptOfiOrgCodeMap.put("농림축산식품부", "1543000");
        cptOfiOrgCodeMap.put("농촌진흥청", "1390000");
        cptOfiOrgCodeMap.put("대검찰청", "1280000");
        cptOfiOrgCodeMap.put("대법원", "9740000");
        cptOfiOrgCodeMap.put("대통령", "1015000");
        cptOfiOrgCodeMap.put("문화재청", "1550000");
        cptOfiOrgCodeMap.put("문화체육관광부", "1371000");
        cptOfiOrgCodeMap.put("방송통신위원회", "1570100");
        cptOfiOrgCodeMap.put("방위사업청", "1690000");
        cptOfiOrgCodeMap.put("법무부", "1270000");
        cptOfiOrgCodeMap.put("법제처", "1170000");
        cptOfiOrgCodeMap.put("병무청", "1300000");
        cptOfiOrgCodeMap.put("보건복지부", "1352000");
        cptOfiOrgCodeMap.put("산림청", "1400000");
        cptOfiOrgCodeMap.put("산업통상자원부", "1450000");
        cptOfiOrgCodeMap.put("새만금개발청", "1730000");
        cptOfiOrgCodeMap.put("소방청", "1661000");
        cptOfiOrgCodeMap.put("식품의약품안전처", "1471000");
        cptOfiOrgCodeMap.put("여성가족부", "1383000");
        cptOfiOrgCodeMap.put("외교부", "1262000");
        cptOfiOrgCodeMap.put("원자력안전위원회", "1079960");
        cptOfiOrgCodeMap.put("인사혁신처", "1760000");
        cptOfiOrgCodeMap.put("조달청", "1230000");
        cptOfiOrgCodeMap.put("중소벤처기업부", "1421000");
        cptOfiOrgCodeMap.put("중앙선거관리위원회", "9760000");
        cptOfiOrgCodeMap.put("통계청", "1240000");
        cptOfiOrgCodeMap.put("통일부", "1250000");
        cptOfiOrgCodeMap.put("특허청", "1430000");
        cptOfiOrgCodeMap.put("해양경찰청", "1530000");
        cptOfiOrgCodeMap.put("행정안전부", "1741000");
        cptOfiOrgCodeMap.put("행정중심복합도시건설청", "1670000");
        cptOfiOrgCodeMap.put("헌법재판소", "9750000");
        cptOfiOrgCodeMap.put("환경부", "1480000");

        // 추진단계 코드 : lbPrcStsCdGrp
        lbPrcStsCodeMap.put("입안","EA01");
        lbPrcStsCodeMap.put("심사","EA02");
        lbPrcStsCodeMap.put("심의/의결","EA03");
        lbPrcStsCodeMap.put("공포","EA04 ");
        lbPrcStsCodeMap.put("폐기/철회","EA05");

    }

    public static String getLsKndCode(String lsKndName) {
        if (lsKndCodeMap.containsKey(lsKndName))
            return lsKndCodeMap.get(lsKndName);
        return "";
    }
    public static String getCptOfiOrgCode(String cptOfiOrgName) {
        if (cptOfiOrgCodeMap.containsKey(cptOfiOrgName))
            return cptOfiOrgCodeMap.get(cptOfiOrgName);
        return "";
    }
    public static String getLbPrcStsCode(String lbPrcStsName) {
        if (lbPrcStsCodeMap.containsKey(lbPrcStsName))
            return lbPrcStsCodeMap.get(lbPrcStsName);
        return "";
    }
    public static String getDateForm(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.+MM.+dd.");
        return dateTime.format(formatter);
    }

}
