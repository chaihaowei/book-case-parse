package com.icourt.lawyercrawlparse.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.icourt.lawyercrawlparse.entity.JudgementExt;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;

public class DsUtil {

    private static final String GOOD_CASE = "goodCase";


    public static String getDsId(Map<String,Object> map){
        String dsid=null;
        String caseName = MapUtils.getString(map, JudgementExt.caseName, "");
        dsid = getMD5(caseName);
        String caseNum = MapUtils.getString(map, JudgementExt.caseNum, "");
        if (StringUtils.isNotBlank(caseNum)) {
            dsid = getMD5(dsid + "#" + caseNum);
        }

        dsid=getMD5(GOOD_CASE+dsid);
        return dsid;
    }

    private static String getMD5(String caseName) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        return md5.digestHex(caseName);
    }
}