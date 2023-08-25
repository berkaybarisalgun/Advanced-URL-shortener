package com.barisdev.Url.Shortener.util;

import com.barisdev.Url.Shortener.entity.Url;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class QRCodeGenerator {

    public static void generateQRCode(Url url) throws WriterException, IOException {
        //String qrCodePath1 = "C:" + File.separator + "newProjects" + File.separator + "Advanced-URL-shortener" + File.separator + "QrCode";
        String qrCodePath=System.getProperty("user.dir");
        String qrCodeName = qrCodePath + File.separator + url.getId() + url.getRef() + "-QRCODE.png";

        var qrCodeWriter = new QRCodeWriter();
        String fullUrl = "Url:" + url.getUrl() + "\n"; // Önceki içerik üzerine URL eklendi
        BitMatrix bitMatrix = qrCodeWriter.encode(
                fullUrl, // Düzenlenmiş içerik
                BarcodeFormat.QR_CODE, 400, 400);

        Path path = FileSystems.getDefault().getPath(qrCodeName);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path); // Format JPG'den PNG'ye çevrildi

    }

}
