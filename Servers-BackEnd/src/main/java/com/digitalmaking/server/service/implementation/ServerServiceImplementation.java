package com.digitalmaking.server.service.implementation;

import com.digitalmaking.server.enumeration.Status;
import com.digitalmaking.server.model.Server;
import com.digitalmaking.server.repository.ServerRepository;
import com.digitalmaking.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static com.digitalmaking.server.enumeration.Status.SERVER_DOWN;
import static com.digitalmaking.server.enumeration.Status.SERVER_UP;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {
    private final ServerRepository serverRepository;

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by id: {}", id);
        serverRepository.deleteById(id);
        return true;
    }

    private String setServerImageUrl() {
        String[] imagesNames = {"server_01.png", "server_02.png", "server_03.png", "server_04.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/server/image/"
                + imagesNames[new Random().nextInt(4)])
                .toUriString();
    }
}
