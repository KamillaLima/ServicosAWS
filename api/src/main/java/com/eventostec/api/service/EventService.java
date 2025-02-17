package com.eventostec.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.eventostec.api.domain.event.Event;
import com.eventostec.api.dto.EventRequestDTO;
import com.eventostec.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;


    @Autowired
    private EventRepository eventRepository;

    public Event createEventDto(EventRequestDTO data){
        String imgUrl = null;
        if(data.image() != null){
            imgUrl = this.uploadImg(data.image());
        }
        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setDate(new Date(data.date()));
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setImageUrl(imgUrl);
        newEvent.setRemote(data.remote());
        eventRepository.save(newEvent);
        return newEvent;
    }

    private String uploadImg(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = this.convertMultiPartToFile(multipartFile);
            s3Client.putObject(bucketName , filename,file);
            file.delete();
            return  s3Client.getUrl(bucketName,filename).toString();
        } catch (Exception e) {
            System.out.println("ERRO AO RETORNAR O ARQUIVO");
            return  null;
        }
    }

    private File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {

        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream((convFile));
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }
}
