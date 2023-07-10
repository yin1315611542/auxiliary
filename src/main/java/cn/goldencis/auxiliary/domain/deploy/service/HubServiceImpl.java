package cn.goldencis.auxiliary.domain.deploy.service;

import cn.goldencis.auxiliary.domain.deploy.Hub;
import cn.goldencis.auxiliary.domain.deploy.QHub;
import cn.goldencis.auxiliary.domain.deploy.repository.HubRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HubServiceImpl implements HubService {

    @Autowired
    List<Hub> hubs;
    @Autowired
    HubRepository hubRepository;

    @Override
    public void initHubs() {
        hubs.addAll(Lists.newArrayList(hubRepository.findAll(QHub.hub.state.eq(true))));
    }

    @Override
    public void saveHub() {
        hubRepository.saveAll(hubs);
    }


}
