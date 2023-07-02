package com.device.file.minio.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;

import java.util.Date;

public class FilePath {
    /**
     * 根据原有文件名生成私有云端保存路径
     * @param fileName
     * @return
     */
    public static String ossPath(String fileName){
        StringBuffer sb = new StringBuffer();
        // sb.append(SessionLocal.getFactory()).append("/");
        String date = DateUtil.format(new Date(),"yyyy-MM");
        sb.append(date).append("/");
        sb.append(UUID.fastUUID().toString().replaceAll("-","")).append("-"+fileName);
        return sb.toString();
    }
//    @PostMapping
//    public R test(@RequestBody MultipartFile file)throws Exception{
//        String fileName = file.getOriginalFilename();
//        minioTemplate.uploadFile(ossPath(fileName),file.getInputStream());
//        return R.ok();
//    }
}
