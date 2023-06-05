package com.example.first_lab.Angle;

import com.example.first_lab.Exceptions.NegativeException;
import com.example.first_lab.Exceptions.OtherException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public record Angle(double degree) {
    private static final Logger logger = LogManager.getLogger(Angle.class);

    public Angle(double degree) {

        if (Double.isInfinite(degree) || Double.isNaN(degree))
            throw new OtherException("INTERNAL SERV ERR");


        if (degree <= 0)
            throw new NegativeException("NegativeException");
        this.degree = degree;
        logger.info("OK");
    }

    public double getRadians() {
        return Math.toRadians(degree);
    }
}