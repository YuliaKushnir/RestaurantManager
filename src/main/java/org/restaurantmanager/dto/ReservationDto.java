package org.restaurantmanager.dto;

import lombok.Data;
import org.restaurantmanager.enums.ReservationStatus;

import java.sql.Date;

@Data
public class ReservationDto {
    private Long id;

    private String tableType;

    private String description;

    private Date dateTime;

    private ReservationStatus reservationStatus;

    private Long userId;

    private String customerName;
}
