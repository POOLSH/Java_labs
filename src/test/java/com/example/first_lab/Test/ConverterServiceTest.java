package com.example.first_lab.Test;

import com.example.first_lab.entity.AngleEntity;
import com.example.first_lab.repository.AngleRepository;
import com.example.first_lab.services.ConverterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ConverterServiceTest {

    @Mock
    AngleRepository angleRepository;

    @InjectMocks
    ConverterService converterService;

    @Test
    void testConvert() {
        Double degree = 45.0;
        double rads = Math.toRadians(degree);
        ArgumentCaptor<AngleEntity> captor = ArgumentCaptor.forClass(AngleEntity.class);
        Mockito.when(angleRepository.save(captor.capture())).thenReturn(null);

        AngleEntity result = converterService.convert(degree);

        assertEquals(degree, result.getDegree());
        assertEquals(rads, result.getRads());
        assertNotNull(result.getDegree());

        assertEquals(degree, captor.getValue().getDegree());
        assertEquals(rads, captor.getValue().getRads());
    }
}