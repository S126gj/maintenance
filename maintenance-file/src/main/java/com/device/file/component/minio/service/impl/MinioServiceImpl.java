package com.device.file.component.minio.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlPath;
import cn.hutool.core.util.CharsetUtil;
import com.device.common.exception.BIZException;
import com.device.common.utils.StringUtil;
import com.device.file.component.minio.config.MinioTemplate;
import com.device.file.component.minio.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class MinioServiceImpl implements MinioService {

    private final MinioTemplate minioTemplate;

    @Value("${file.minio.bucketName}")
    private String DEFAULT_DB;

    @Override
    public String uploadJpeg(MultipartFile file) {
        String filePath = "";
        String url = "";
        try {
            if (file != null) {
                String bucketName = minioTemplate.getBucketName(DEFAULT_DB);
                filePath = ossFileName(file.getOriginalFilename());
                url = minioTemplate.uploadJpeg(bucketName, filePath, file.getInputStream());
            }
        } catch (Exception e) {
            throw new BIZException("上传失败:" + e.getMessage());
        }
        return parseUrl(url);
    }

    @Override
    public String uploadFile(MultipartFile file) {
        String filePath = "";// minio路径
        String url = "";//"访问路径"
        try {
            if (file != null) {
                String bucketName = minioTemplate.getBucketName(DEFAULT_DB);
                filePath = ossFileName(file.getOriginalFilename());
                url = minioTemplate.uploadFile(bucketName, filePath, file.getInputStream());
            }
        } catch (Exception e) {
            throw new BIZException("上传失败:" + e.getMessage());
        }
        return parseUrl(url);
    }

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        String url = "";//"访问路径"
        try {
            if (file != null) {
                String bucketName = minioTemplate.getBucketName(DEFAULT_DB);
                url = minioTemplate.uploadFile(bucketName, fileName, file.getInputStream());
            }
        } catch (Exception e) {
            throw new BIZException("上传失败:" + e.getMessage());
        }
        return parseUrl(url);
    }

    /**
     * 通用文件上传
     *
     * @param minioFilePath
     * @param stream
     * @return
     * @throws Exception
     */
    @Override
    public String uploadFile(String minioFilePath, InputStream stream) {
        String url = "";
        try {
            String bucketName = minioTemplate.getBucketName(DEFAULT_DB);
            url = minioTemplate.uploadFile(bucketName, minioFilePath, stream);
        } catch (Exception e) {
            throw new BIZException("上传失败:" + e.getMessage());
        }
        return parseUrl(url);
    }

    @Override
    public void download(HttpServletResponse response, String filePath) {
        String bucketName = minioTemplate.getBucketName(DEFAULT_DB);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            inputStream = minioTemplate.getObject(bucketName, filePath);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filePath, "UTF-8"));
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            response.flushBuffer();
        } catch (IOException e) {
            throw new BIZException("文件导出异常,详情如下:", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new BIZException("下载文件不存在");
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void viewJpeg(HttpServletResponse response, String filePath) {
        String bucketName = minioTemplate.getBucketName(DEFAULT_DB);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = minioTemplate.getObject(bucketName, filePath);
            outputStream = response.getOutputStream();
            response.setContentType("image/jpeg;charset=utf-8");
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            response.flushBuffer();
        } catch (IOException e) {
            throw new BIZException("图片查看失败,详情如下" + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new BIZException("图片不存在");
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(String filePath) {
        String bucketName = minioTemplate.getBucketName(DEFAULT_DB);
        String path = "";
        if (StringUtil.isNotEmpty(filePath) && filePath.indexOf(bucketName) > -1) {
            path = filePath.split(bucketName + "/")[1];
            System.out.println(path);
        }
        try {
            minioTemplate.removeObject(bucketName, path);
        } catch (Exception e) {
            throw new BIZException("删除失败:" + e.getMessage());
        }
    }

    @Override
    public InputStream getInputStream(String filePath) {
        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL(filePath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(2 * 1000);
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            IOUtils.copy(conn.getInputStream(), output);
            return new ByteArrayInputStream(output.toByteArray());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

    @Override
    public String uploadFileDownload(MultipartFile file) {
        // minio路径
        String filePath = "";
        // 访问路径
        String url = "";
        try {
            if (file != null) {
                String bucketName = minioTemplate.getBucketName(DEFAULT_DB);
                filePath = ossFileName(file.getOriginalFilename());
                // 上传可直接下载的文件，不需要预览
                url = minioTemplate.uploadFileDownload(bucketName, filePath, file.getInputStream(),
                    MediaType.APPLICATION_OCTET_STREAM_VALUE);
            }
        } catch (Exception e) {
            throw new BIZException("上传失败:" + e.getMessage());
        }
        return parseUrl(url);
    }

    @Override
    public String uploadFileWithOutDB(MultipartFile file, String fileName) {
        String url = "";//"访问路径"
        try {
            if (file != null) {
                url = minioTemplate.uploadFile(DEFAULT_DB, fileName, file.getInputStream());
            }
        } catch (Exception e) {
            throw new BIZException("上传失败:" + e.getMessage());
        }
        return parseUrl(DEFAULT_DB, url);
    }

    @Override
    public String uploadFileWithOutDB(MultipartFile file) {
        String url = "";//"访问路径"
        try {
            if (file != null) {
                String filePath = ossFileName(file.getOriginalFilename());
                url = minioTemplate.uploadFile(DEFAULT_DB, filePath, file.getInputStream());
            }
        } catch (Exception e) {
            throw new BIZException("上传失败:" + e.getMessage());
        }
        return parseUrl(DEFAULT_DB, url);
    }

    private static String ossFileName(String filePath) {
        StringBuffer sb = new StringBuffer();
        // sb.append(SessionLocal.getFactory()).append("/");
        String date = DateUtil.format(new Date(), "yyyy-MM");
        sb.append(date).append("/");
        sb.append(UUID.fastUUID().toString().replaceAll("-", "")).append("-" + filePath);
        return sb.toString();
    }

    /**
     * 解决乱码
     *
     * @param filePath
     * @return
     */
    private String parseUrl(String filePath) {
        String bucketName = minioTemplate.getBucketName(DEFAULT_DB);
        UrlPath urlPath = null;
        String[] fps = null;
        if (StringUtil.isNotEmpty(filePath) && filePath.indexOf(bucketName) > -1) {
            fps = filePath.split("/" + bucketName);
            try {
                UrlBuilder builder = UrlBuilder.ofHttp(filePath, CharsetUtil.CHARSET_UTF_8);
                urlPath = builder.getPath();
            } catch (Exception e) {
                throw new BIZException("路径解析错误！");
            }
        } else {
            throw new BIZException("路径解析错误！");
        }
        return fps[0] + urlPath.toString();
    }

    /**
     * 解决乱码
     *
     * @param bucketName
     * @param filePath
     * @return
     */
    private String parseUrl(String bucketName, String filePath) {
        UrlPath urlPath = null;
        String[] fps = null;
        if (StringUtil.isNotEmpty(filePath) && filePath.indexOf(bucketName) > -1) {
            fps = filePath.split("/" + bucketName);
            try {
                UrlBuilder builder = UrlBuilder.ofHttp(filePath, CharsetUtil.CHARSET_UTF_8);
                urlPath = builder.getPath();
            } catch (Exception e) {
                throw new BIZException("路径解析错误！");
            }
        } else {
            throw new BIZException("路径解析错误！");
        }
        return fps[0] + urlPath.toString();
    }


}
