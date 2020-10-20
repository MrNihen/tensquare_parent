package com.tensquare.gathering.dao;

import com.tensquare.gathering.pojo.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Creat by nihen on 2020/10/20 19:32
 */
public interface GatheringDao extends JpaRepository<Gathering,String> {
}
