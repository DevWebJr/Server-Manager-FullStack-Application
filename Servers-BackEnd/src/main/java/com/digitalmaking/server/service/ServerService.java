package com.digitalmaking.server.service;

import com.digitalmaking.server.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    Collection<Server> list(int limit);
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
}
