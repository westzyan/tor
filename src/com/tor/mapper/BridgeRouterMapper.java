package com.tor.mapper;

import com.tor.pojo.BridgeRouter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface BridgeRouterMapper {

    List<BridgeRouter> selectAll();

}
