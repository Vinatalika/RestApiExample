package com.example.restapitest.repository;

import com.example.restapitest.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository <Note, Long>{
}
