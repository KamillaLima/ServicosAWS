package com.eventostec.api.domain.event;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Table(name="event")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(max = 100)
    private String title;
    @NotNull
    @Size(max = 250)
    private String description;
    @NotNull
    @Size(max = 100)
    private String imageUrl;
    @NotNull
    @Size(max = 100)
    private String eventUrl;
    @NotNull
    private Boolean remote;
    @NotNull
    private Date date;
}
