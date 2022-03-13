package com.digitalmaking.server.service.implementation;

import com.digitalmaking.server.model.Server;
import com.digitalmaking.server.repository.ServerRepository;
import com.digitalmaking.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {
    private final ServerRepository serverRepository;

    @Override
    public Collection<Server> list(int limit) {
        return null;
    }

    @Override
    public Server create(Server server) {
        log.info("Saving new server: ", server.getName());
        return null;
    }

    @Override
    public Server ping(String ipAddress) {
        return null;
    }

    @Override
    public Server get(Long id) {
        return null;
    }

    @Override
    public Server update(Server server) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
