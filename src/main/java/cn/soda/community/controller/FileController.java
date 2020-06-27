package cn.soda.community.controller;

import cn.soda.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload() {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
//        fileDTO.setMessage("");
        fileDTO.setUrl("/images/default-avatar.jpg");
        return fileDTO;
    }
}
