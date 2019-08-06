package com.ef.parser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class LogAccess {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_access")
    private LocalDateTime dateAccess;

    private String ip;
    private String request;
    private int status;

    @Column(name = "user_agent")
    private String userAgent;

}
