package cn.goldencis.auxiliary.domain.deploy.service;

import cn.goldencis.auxiliary.domain.deploy.Hub;
import cn.goldencis.auxiliary.domain.deploy.repository.HubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class HubServiceImpl implements HubService {
    @Autowired
    List<Hub> hubs;
    @Autowired
    HubRepository hubRepository;

    @PostConstruct
    public void initHubs() {

    }

}
