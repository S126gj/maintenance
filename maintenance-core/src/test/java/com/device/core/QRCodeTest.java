package com.device.core;

import com.alibaba.fastjson.JSONObject;
import com.device.common.utils.QrCodeUtil;
import com.device.file.component.minio.service.MinioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/10 14:20
 */
@SpringBootTest
public class QRCodeTest {

    @Autowired
    private MinioService minioService;

    @Test
    public void generateQRCode() throws Exception {
        //生成一个二维码
        //定义一个json格式的字符串，使用fastjson(它能完成json字符串和json对象的相互转化)
        //1.创建一个jsonObject对象
        JSONObject jsonObject = new JSONObject();
        //2.给jsonObject对象中存放数据
        jsonObject.put("machineId","123456");
        //json对象转换为json格式的字符串
        String contents = jsonObject.toString();
        BufferedImage image = QrCodeUtil.createImage(contents, null, true);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // 将图像输出到输出流中。
        ImageIO.write(image, "jpeg", bos);
        MultipartFile multipartFile = new MockMultipartFile("test", "test.jpeg", "image/jpeg", bos.toByteArray());
        System.out.println(minioService.uploadJpeg(multipartFile));
    }

}