package com.tor.mapper;

import com.tor.pojo.CountryOnionRelay;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CountryOnionRelayMapper {

    List<CountryOnionRelay> selectAll();

 }
