package com.eventostec.api.domain.address;

import com.eventostec.api.domain.event.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(max = 100)
    private String city;

    @NotNull
    @Size(max = 100)
    private String uf;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;
}
