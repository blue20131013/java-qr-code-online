package com.example.qrcode.controller;

import com.example.qrcode.service.QrService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QrController {

    private final QrService qrService;

    public QrController(QrService qrService) {
        this.qrService = qrService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/generate")
    public String generate(@RequestParam("url") String url, Model model) {
        if (url == null || url.trim().isEmpty()) {
            model.addAttribute("error", "URL 또는 텍스트를 입력해주세요.");
            return "index";
        }
        
        String qrCodeBase64 = qrService.generateQrCodeBase64(url, 300, 300);
        model.addAttribute("qrCode", qrCodeBase64);
        model.addAttribute("savedUrl", url);
        return "index";
    }
}
