package com.example.first_lab.Controllers;

import com.example.first_lab.Angle.Angle;
import com.example.first_lab.entity.AngleEntity;
import com.example.first_lab.repository.AngleRepository;
import com.example.first_lab.services.AngleCache;
import com.example.first_lab.services.ConverterService;
import com.example.first_lab.services.RequestCounter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
public class AngleController {
    private final AngleCache angleCache;
    private final RequestCounter counter;
    private final ConverterService converterService;

    private final AngleRepository angleRepository;

    public AngleController(AngleCache angleCache, RequestCounter requestCounter, ConverterService converterService, AngleRepository angleRepository) {
        this.angleCache = angleCache;
        this.counter = requestCounter;
        this.converterService = converterService;
        this.angleRepository = angleRepository;
    }

    @GetMapping("/angle")
    public Angle angle(@RequestParam(value = "degree", defaultValue = "1") double degree) {

        counter.increment();

        if (angleCache.containsKey(degree)) {
            Double radians = angleCache.getRadians(degree);
        } else {
            AngleEntity angleEntity = converterService.convert(degree);
            angleCache.saveRadians(angleEntity.getDegree(), angleEntity.getRads());
        }

        return new Angle(degree);
    }

    @GetMapping("/count")
    public int count(@RequestParam(value = "str", defaultValue = "1") int choice) {
        if (choice == 0) return counter.setZero();
        return counter.getCount();
    }

    @GetMapping("/all")
    public List<AngleEntity> getAll() {
        return angleRepository.findAll();
    }

    @PostMapping("/bulk")
    public Map<String, Object> bulkAngle(@RequestBody List<Double> degrees) {

        List<AngleEntity> angleEntities = converterService.convertAll(degrees);

        DoubleSummaryStatistics stats = angleEntities.stream()
                .mapToDouble(AngleEntity::getRads)
                .summaryStatistics();

        Map<String, Object> result = new HashMap<>();
        result.put("min", stats.getMin());
        result.put("max", stats.getMax());
        result.put("average", stats.getAverage());
        result.put("angles", angleEntities);
        return result;
    }

    @PutMapping("/future")
    public ResponseEntity<String> calculateFuture(@RequestBody List<Double> degrees) {
        CompletableFuture.runAsync(() -> converterService.convertAll(degrees));
        return new ResponseEntity<>("will be calculated", HttpStatus.ACCEPTED);
    }
}
