package com.ksenia.forum.configs;

import com.ksenia.forum.constants.Role;
import com.ksenia.forum.models.Account;
import com.ksenia.forum.models.ContactTopic;
import com.ksenia.forum.models.Topic;
import com.ksenia.forum.repositories.AccountRepository;
import com.ksenia.forum.repositories.ContactTopicRepository;
import com.ksenia.forum.repositories.TopicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class dbManager {
    @Bean
    CommandLineRunner accountInjection(AccountRepository accountRepository) {
        return args -> {
            Account admin = new Account(
                    "Alissia Rhodes",
                    "alissia.rhodes@gmail.com",
                    "12345678",
                    Role.admin
            );
            Account user1 = new Account(
                    "Leen Mcdaniel",
                    "leen.mcdaniel@gmail.com",
                    "12345678"
            );
            Account user2 = new Account(
                    "Koa Derrick",
                    "koa.derrick@gmail.com",
                    "12345678"
            );
            Account user3 = new Account(
                    "Alex Wheeler",
                    "alex.wheeler@gmail.com",
                    "12345678"
            );
            Account user4 = new Account(
                    "Kerry Burke",
                    "kerry.burke@gmail.com",
                    "12345678"
            );
            accountRepository.saveAll(List.of(admin, user1, user2, user3, user4));
        };
    }

    @Bean
    CommandLineRunner forumTopicInjection(TopicRepository topicRepository) {
        return args -> {
            Topic t1 = new Topic(
                    "Java"
            );
            Topic t2 = new Topic(
                    "Python"
            );
            Topic t3 = new Topic(
                    "Javascript"
            );
            Topic t4 = new Topic(
                    "Typescript"
            );
            Topic t5 = new Topic(
                    "Algorithms"
            );
            Topic t6 = new Topic(
                    "General"
            );
            topicRepository.saveAll(List.of(t1, t2, t3, t4, t5, t6));
        };
    }

    @Bean
    CommandLineRunner contactTopicInjection(ContactTopicRepository topicRepository){
        return args -> {
            ContactTopic t1 =  new ContactTopic("I forgot my password");
            ContactTopic t2 = new ContactTopic("I dont remember what email was used to sign in");
            ContactTopic t3 =  new ContactTopic("My account was hacked");
            ContactTopic t4 = new ContactTopic("I would like to report a bad behaviour");
            ContactTopic t5 = new ContactTopic("Something else");
        topicRepository.saveAll(List.of(t1, t2, t3, t4, t5));
    };
    }

}
