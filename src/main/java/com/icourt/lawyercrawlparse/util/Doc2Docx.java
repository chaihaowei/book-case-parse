package com.icourt.lawyercrawlparse.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.aspose.words.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
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


    public static String parseWord2Html(String soucePath) throws Exception {
        if(!getLicense()){
            throw new RuntimeException("license 验证失败");
        }
        try {
            Document doc = new Document(soucePath);
            HtmlSaveOptions saveOptions = new HtmlSaveOptions();
            // HtmlSaveOptions的其他设置信息请参考相关API
            saveOptions.setExportHeadersFootersMode(ExportHeadersFootersMode.NONE);
            //设置转换html 图片格式我base64
            saveOptions.setExportImagesAsBase64(Boolean.TRUE);
            ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();
            String htmlText = "";
            doc.save(htmlStream, saveOptions);
            htmlText = new String(htmlStream.toByteArray(),"UTF-8");
            htmlStream.close();
            return htmlText;
        }catch (Exception ex){
            log.error("error path:{}",soucePath);
            log.error("转换html 出错：",ex);
            throw ex;
        }

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
