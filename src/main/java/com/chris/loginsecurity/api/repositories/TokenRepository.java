package com.chris.loginsecurity.api.repositories;

import com.chris.loginsecurity.api.models.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<JwtToken,Long> {
}
