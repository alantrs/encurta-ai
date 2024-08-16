package com.dev.encurta_ai.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
class QrCodeServiceTest {

    @InjectMocks
    private QrCodeService qrCodeService;

    @Test
    @DisplayName("Should create QR Code")
    void generateQRCodeSuccess() throws WriterException, IOException {
        String text = "This is a test";

        String qrCode = qrCodeService.generateQRCode(text);

        assertNotNull(qrCode);
        assertDoesNotThrow(() -> Base64.getDecoder().decode(qrCode), "The string should be valid Base64");

    }
}