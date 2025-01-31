package ru.unibell.contactservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "emails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}