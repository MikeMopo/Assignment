package com.arobot.assignment.repository;

import com.arobot.assignment.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
