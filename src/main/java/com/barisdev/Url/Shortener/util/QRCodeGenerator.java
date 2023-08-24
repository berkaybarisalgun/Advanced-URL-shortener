package com.barisdev.Url.Shortener.util;

import com.barisdev.Url.Shortener.entity.Url;


public class QRCodeGenerator {

    public static void generateQRCode(Url url){
        String qrCodePath="C:\\newProjects\\Advanced-URL-shortener\\QrCode";
        String qrCodeName=qrCodePath+url.getId()+url.getRef()+"-QRCODE.png";
        var qrCodeWriter=new QRCodeWriter();
    }

}
