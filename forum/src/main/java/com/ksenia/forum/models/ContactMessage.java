package com.ksenia.forum.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessage {

    @Id
    @SequenceGenerator(
            name = "contact_message_sequence",
            sequenceName = "contact_message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_message_sequence"
    )
    private Long id;
    private String email;
    private String topic;

    @Lob
    private String message;
    private LocalDateTime dateCreated;
    private boolean isRead;

    public ContactMessage(String email, String topic, String message) {
        this.email = email;
        this.topic = topic;
        this.message = message;
    }

    public LocalDateTime getDateCreated(){
        return LocalDateTime.now();
    }
    public void setDateCreated(){
        this.dateCreated = LocalDateTime.now();
    }
}
