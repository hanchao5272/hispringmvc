package pers.hanchao.hespringmvc.file;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("file")
public class FileController {

    private static final Logger LOGGER = Logger.getLogger(FileController.class);

    /**
     * <p>上传文件的两种方式：流上传和Multipart上传</p>
     * @author hanchao 2018/1/22 22:22
     **/
    @PostMapping("/upload")
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile[] files,Model model){
        boolean isSuccess = true;
        //获取上传根目录
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        LOGGER.debug("uploadRoot:" + uploadRootPath);
        File uploadRootDir = new File(uploadRootPath);
        //判断上传根目录是否存在，如果不存在，则创建层级目录
        if (!uploadRootDir.exists()){
            uploadRootDir.mkdirs();
        }
        //上传文件
        List<File> savedFileList = new ArrayList<File>();
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            //如果文件大于200kb，则不能上传
            if (file.getSize() > 204800){
                isSuccess = false;
                break;
            }
            File savedFile = saveFileByStream(file, uploadRootDir);
//            File savedFile = saveFileByMultiPartFile(file, uploadRootDir);
            savedFileList.add(savedFile);
        }
        if (!isSuccess){
            model.addAttribute("returnMessage","文件太大，禁止上传！");
            System.out.println("文件太大，禁止上传！");
        }else {
            model.addAttribute("savedFileList",savedFileList);
        }
        return "/file/file";
    }

    @GetMapping("/download/{name}/{suffix}/{type}")
    public String download(HttpServletRequest request,HttpServletResponse response,
                           @PathVariable("name") String fileName,@PathVariable("suffix") String suffix,
                           @PathVariable("type") String type,Model model){
        File uploadRootDir = new File(request.getServletContext().getRealPath("upload"));
        File file = new File(uploadRootDir.getAbsolutePath() + File.separator + fileName + "." + suffix);
        if (!file.exists()){
            model.addAttribute("returnMessage","文件不存在！");
        }else{
            //获取文件类型
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            //如果文件类型为空，则使用默认文件类型
            if (null == mimeType){
                mimeType = "application/octet-stream";
            }
            LOGGER.debug("mimeType:" + mimeType);
            response.setContentType(mimeType);
            //设置如何显示附加的文件 inline attachment @TODO
            response.setHeader("Content-Disposition",String.format(type + ":filename=\"" + file.getName()) + "\"");
            //设置长度
            response.setContentLength((int) file.length());

            try {
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(inputStream,response.getOutputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                LOGGER.error("下载文件失败");
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("下载文件失败");
            }
        }
        return "/file/file";
    }

    /**
     * <p>通过流操作保存文件</p>
     * @param srcFile 需要保存的文件
     * @param uploadRootDir 上传文件根目录
     * @return 上传完的文件
     * @author hanchao 2018/1/22 22:09
     **/
    private File saveFileByStream(MultipartFile srcFile, File uploadRootDir) {
        //获取客户端名称
        String srcFileName = srcFile.getOriginalFilename();
        LOGGER.debug("客户端文件名称：" + srcFileName);

        File serverFile = null;
        if (srcFileName != null && srcFileName.length() > 0){
            try{
                byte[] bytes = srcFile.getBytes();
                //创建服务器文件
                serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + srcFileName);
                //写入服务器文件
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(serverFile));
                bos.write(bytes);
                bos.close();
                //打印上传了哪些文件
                LOGGER.info("写入了文件：" + serverFile);
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("删除文件失败！");
            }
        }
        return serverFile;
    }

    /**
     * <p>通过MultipartFile的封装方法保存文件</p>
     * @param srcFile 需要保存的文件
     * @param uploadRootDir 上传文件根目录
     * @return 上传完的文件
     * @author hanchao 2018/1/23 22:09
     **/
    public File saveFileByMultiPartFile(MultipartFile srcFile, File uploadRootDir){
        File serverFile = null;
        //如果文件不为空，则可以上传
        if (!srcFile.isEmpty()){
            try{
                serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + srcFile.getOriginalFilename());
                srcFile.transferTo(serverFile);
                //打印上传了哪些文件
                LOGGER.info("写入了文件：" + serverFile);
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("删除文件失败！");
            }
        }
        return serverFile;
    }
}
