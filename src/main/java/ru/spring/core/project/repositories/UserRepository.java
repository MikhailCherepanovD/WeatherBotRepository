package ru.spring.core.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.spring.core.project.entity.Place;
import ru.spring.core.project.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   @Query(value = "SELECT * from USERS where chat_id = :chatId " , nativeQuery = true)
   List<User> findAllUsersByChatId(@Param("chatId") Long userId);


   @Query(value =
           "INSERT INTO USERS (chat_id, user_name) SELECT :chatId, :userName \n" +
           "WHERE NOT EXISTS (" +
           "SELECT 1 FROM USERS " +
           "WHERE chat_id = :chatId );", nativeQuery = true)
   void insertIfNotExistCheckById(@Param("chatId") Long chatId,@Param("userName") String userName);

}
