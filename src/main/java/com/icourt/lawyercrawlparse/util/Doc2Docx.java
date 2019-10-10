package com.icourt.lawyercrawlparse.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.aspose.words.Document;
import com.aspose.words.License;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.InputStream;
@Slf4j
public class Doc2Docx {

    private static final String TMP ="/tmp";
    private static final String DOCX=".docx";

    public static boolean getLicense() throws Exception {
        // license.xml应放在..\WebRoot\WEB-INF\classes路径下
        InputStream is = Doc2Docx.class.getClassLoader().getResourceAsStream("license.xml");
        License aposeLic = new License();
        aposeLic.setLicense(is);
        return Boolean.TRUE;
    }


    /**
     * 获取转换后的file
     * @param soucePath 路径
     * @return 转换后的file
     * @throws Exception
     */
    public static File getFile(String soucePath)throws Exception  {
        if(StringUtils.endsWith(soucePath, DOCX)){
            return FileUtil.newFile(soucePath);
        }
        if(!getLicense()){
            throw new RuntimeException("license 验证失败");
        }
        String s = IdUtil.simpleUUID();
        File tmpDir = FileUtil.getTmpDir();
        String tmp=tmpDir.getAbsolutePath()+TMP;
        if(!FileUtil.exist(tmp)){
            FileUtil.mkdir(tmp);
        }
        String path =tmp+"/"+s+DOCX;

        Document doc = new Document(soucePath);
        doc.save(path);
        log.info("tmp file path:{}",path);
        File file = FileUtil.newFile(path);
        return file;
    }


    public static void main(String[] args) throws Exception {
        File file = getFile("/Users/chaihaowei/bookParse/parse/第一批案例（共计771）/民商事（514个）/2017民商/1429.黑龙江7-牛国梁-依安县正宇实业有限公司诉赵元弟确认合同效力纠纷案案.doc");
        if(!getLicense()){
            throw new RuntimeException("license 验证失败");
        }
        Document doc = new Document("/Users/chaihaowei/bookParse/parse/第一批案例（共计771）/民商事（514个）/2017民商/1429.黑龙江7-牛国梁-依安县正宇实业有限公司诉赵元弟确认合同效力纠纷案案.doc");
        doc.save("/Users/chaihaowei/Desktop/outpuit.docx");
    }
}
