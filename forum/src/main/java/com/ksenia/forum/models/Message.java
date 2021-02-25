package com.ksenia.forum.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
    )
    private Long id;
    private String topic;
    private String email;
    private String name;

    @Lob
    private String text;

    private LocalDateTime dateCreated;

    @Transient
    private String dateCreatedToString;
    private boolean isAnswered;

    public Message(String topic, String text, String email, String name) {
        this.email = email;
        this.name = name;
        this.topic = topic;
        this.text = text;
        this.dateCreated = LocalDateTime.now();
        this.isAnswered = false;
    }

    public String getDateCreatedToString() {
        return this.dateCreated.getDayOfMonth() + "." +
                 this.dateCreated.getMonth()+ "." +
                 this.dateCreated.getYear()+ " " +
                 this.dateCreated.getHour()+":" +
                 this.dateCreated.getMinute()+":" +
                 this.dateCreated.getSecond();
    }
}
