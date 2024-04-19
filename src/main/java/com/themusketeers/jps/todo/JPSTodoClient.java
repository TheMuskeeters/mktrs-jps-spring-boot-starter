/*----------------------------------------------------------------------------*/
/* Source File:   JPSTODOCLIENT.JAVA                                          */
/* Copyright (c), 2024 The Musketeers                                         */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Mar.08/2024  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.themusketeers.jps.todo;

import static com.themusketeers.jps.common.constants.TodoClientConstants.TODOS_API;
import static com.themusketeers.jps.common.constants.TodoClientConstants.TODOS_API_ID;

import com.themusketeers.jps.JPSClient;
import com.themusketeers.jps.todo.model.Todo;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

/**
 * Implements a Json Place Holder (JPS) Rest Client.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
public class JPSTodoClient implements JPSClient<Todo> {
    private final RestClient restClient;

    public JPSTodoClient(@Qualifier("jsonPlaceholderRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<Todo> findAll() {
        return restClient.get()
            .uri(TODOS_API)
            .retrieve()
            .body(new ParameterizedTypeReference<>() {
            });
    }

    @Override
    public Todo findById(Integer id) {
        return restClient.get()
            .uri(TODOS_API_ID, id)
            .retrieve()
            .body(Todo.class);
    }

    @Override
    public Todo create(Todo todo) {
        return restClient.post()
            .uri(TODOS_API)
            .body(todo)
            .retrieve()
            .body(Todo.class);
    }

    @Override
    public Todo update(Integer id, Todo todo) {
        return restClient.put()
            .uri(TODOS_API_ID, id)
            .body(todo)
            .retrieve()
            .body(Todo.class);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return restClient.delete()
            .uri(TODOS_API_ID, id)
            .retrieve()
            .toBodilessEntity();
    }
}
