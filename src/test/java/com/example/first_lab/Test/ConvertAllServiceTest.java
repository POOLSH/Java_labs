package com.example.first_lab.Test;

import com.example.first_lab.entity.AngleEntity;
import com.example.first_lab.repository.AngleRepository;
import com.example.first_lab.services.ConverterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ConvertAllServiceTest {

    @Mock
    AngleRepository angleRepository;

    @InjectMocks
    ConverterService converterService;

    @Test
    void testConvertAll() {
        List<Double> degreeList = Arrays.asList(45.0, 60.0, 90.0);
        List<AngleEntity> angleEntityList = degreeList.stream()
                .map(degree -> new AngleEntity(degree, Math.toRadians(degree)))
                .collect(Collectors.toList());
        Mockito.when(angleRepository.save(Mockito.any(AngleEntity.class))).thenReturn(null);

        List<AngleEntity> result = converterService.convertAll(degreeList);

        assertEquals(angleEntityList.size(), result.size());
        for (int i = 0; i < angleEntityList.size(); i++) {
            AngleEntity expected = angleEntityList.get(i);
            AngleEntity actual = result.get(i);
            assertEquals(expected.getDegree(), actual.getDegree());
            assertEquals(expected.getRads(), actual.getRads());
            Mockito.verify(angleRepository, Mockito.times(1)).save(Mockito.any(AngleEntity.class));
        }
    }
}
