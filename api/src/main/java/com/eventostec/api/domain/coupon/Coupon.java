package com.eventostec.api.domain.coupon;

import com.eventostec.api.domain.event.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name="coupon")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    @Size(max = 100)
    private String code;
    @NotNull
    private Integer discount;
    @NotNull
    private Date valid;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;
}
