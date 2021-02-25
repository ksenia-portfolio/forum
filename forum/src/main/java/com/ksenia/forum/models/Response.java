package com.ksenia.forum.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Response {

    @Id
    @SequenceGenerator(
            name = "response_sequence",
            sequenceName = "response_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "response_sequence"
    )
    private Long id;

    @ManyToOne
    private Message message;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Account account;

    @Lob
    private String text;
    private int rating;
    private LocalDateTime dateCreated;

    public Response(Account account, Message message, String text) {
        this.account = account;
        this.message = message;
        this.text = text;
        this.rating = 0;
        this.dateCreated = LocalDateTime.now();
    }

    public void rateResponse(){
        this.rating ++;
    }
}
