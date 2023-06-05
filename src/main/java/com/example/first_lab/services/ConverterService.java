package com.example.first_lab.services;

import com.example.first_lab.entity.AngleEntity;
import com.example.first_lab.repository.AngleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterService {


    private final AngleRepository angleRepository;

    public ConverterService(AngleRepository angleRepository) {
        this.angleRepository = angleRepository;
    }

    public AngleEntity convert(Double degree) {
        double rads = Math.toRadians(degree);
        AngleEntity angleEntity = new AngleEntity(degree, rads);
        angleRepository.save(angleEntity);
        return angleEntity;
    }

    public List<AngleEntity> convertAll(List<Double> degreeList) {
        List<AngleEntity> list = new ArrayList<>();
        for (Double aDouble : degreeList) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.add(this.convert(aDouble));
        }
        return list;
    }
}
