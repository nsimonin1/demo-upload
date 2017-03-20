package org.simon.pascal.controller;

import org.simon.pascal.domain.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Created by simon on 20/03/2017.
 */
@RestController
@RequestMapping("/count")
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
    private UploadedFile ufile;

    public UploadController() {
        LOGGER.info("init RestController");
        this.ufile = new UploadedFile();
    }

    @GetMapping("/get/{value}")
    public void get(HttpServletResponse response, @PathVariable String value) {
        try {

            response.setContentType(ufile.getType());
            response.setContentLength(ufile.getLength());
            FileCopyUtils.copy(ufile.getBytes(), response.getOutputStream());

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @PostMapping("/upload")
    public String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        //0. notice, we have used MultipartHttpServletRequest

        //1. get the files from the request object
        Iterator<String> itr = request.getFileNames();

        MultipartFile mpf = request.getFile(itr.next());

        try {
            //just temporary save file info into ufile
            ufile.setLength(mpf.getBytes().length);
            ufile.setBytes(mpf.getBytes());
            ufile.setType(mpf.getContentType());
            ufile.setName(mpf.getOriginalFilename());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        //2. send it back to the client as <img> that calls get method
        //we are using getTimeInMillis to avoid server cached image

        return "<img src='http://localhost:8080/count/get/" + Calendar.getInstance().getTimeInMillis() + "' />";

    }
}
