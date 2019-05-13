package com.tor.service;

import com.tor.common.ServerResponse;
import com.tor.pojo.CountryOnionRelay;


import java.util.List;

public interface IInitService {

    public ServerResponse<List<CountryOnionRelay>> getAllCountryOnionRelay();

}
