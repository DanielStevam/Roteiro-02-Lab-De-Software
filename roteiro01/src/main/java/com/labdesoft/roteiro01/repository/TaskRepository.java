package com.labdesoft.roteiro01.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labdesoft.roteiro01.entity.*;

@EntityScan
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}