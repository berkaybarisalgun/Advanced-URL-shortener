package com.barisdev.Url.Shortener.service;


import ch.qos.logback.core.util.TimeUtil;
import com.barisdev.Url.Shortener.entity.Url;
import com.barisdev.Url.Shortener.exceptions.DateExceptionHandler;
import com.barisdev.Url.Shortener.exceptions.RefException;
import com.barisdev.Url.Shortener.repository.UrlRepository;
import com.barisdev.Url.Shortener.util.QRCodeGenerator;
import com.barisdev.Url.Shortener.util.RandomStringGenerator;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {
    private final UrlRepository repository;

    private final QRCodeGenerator qrCodeGenerator=new QRCodeGenerator();

    private final RandomStringGenerator randomStringGenerator = new RandomStringGenerator();


    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public List<Url> getAllUrl() {
        return repository.findAll();
    }

    public Url getUrlByref(String ref) {

        Optional<Url> url=repository.findByref(ref);
        if(url.isPresent()){
            return url.get();
        }
        else{
            throw new RefException.RefNotFoundException(ref);
        }

    }

    public Url create(Url url) throws Exception {
        if(url.getExpirationDate()==null){
            url.setExpirationDate(LocalDate.now().plusMonths(6));
        }else if(url.getExpirationDate().isBefore(LocalDate.now())){
            throw new DateExceptionHandler.PastDateException();
        }


        if (url.getRef() == null || url.getRef().isEmpty()) {
            url.setRef(generateref());
        } else if (repository.findByref(url.getRef()).isPresent()) {
            throw new RefException.sameRefException(url.getRef());
        }

        qrCodeGenerator.generateQRCode(url);

        url.setQrCodePath(qrCodeGenerator.getQrCodeName());
        url.setRef(url.getRef().toUpperCase());
        return repository.save(url);
    }

    public void delete(Long id) {
        Optional<Url> urlDelete = repository.findById(id);
        if (urlDelete.isPresent()) {
            repository.deleteById(urlDelete.get().getId());
        } else {
            throw new RuntimeException("Resource not found to delete");
        }
    }

    public Url update(Url url) {
        Optional<Url> existingUrlOpt = repository.findByref(url.getRef());

        if (existingUrlOpt.isPresent()) {
            Url existingUrl = existingUrlOpt.get();
            existingUrl.setUrl(url.getUrl()); // Update the URL value

            return repository.save(existingUrl);
        } else {
            throw new RuntimeException("URL not found for update");
        }
    }

    private String generateref() {
        String ref;

        do {
            ref = randomStringGenerator.generateRandomString();
        } while (repository.findByref(ref).isPresent());

        return ref;


    }

}
