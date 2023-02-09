package com.w1zer.service;

import com.w1zer.model.TODO;
import com.w1zer.model.TODOBuilder;
import com.w1zer.model.TODOStatus;
import com.w1zer.model.User;
import com.w1zer.repository.TODORepository;
import com.w1zer.repository.UserRepository;

import java.sql.Date;
import java.util.Collection;
import java.util.stream.Collectors;

public class TODOService {

    private final TODORepository todoRepository = new TODORepository();
    private final UserRepository userRepository = new UserRepository();

    public Collection<TODO> getTODOList(String login) {
        User user = userRepository.getOne(login);
        Collection<TODO> todos = todoRepository.getAll(user.getId());
        Collection<TODO> todosWithWrongStatuses = filterTODOWithWrongStatus(todos);
        if (todosWithWrongStatuses.isEmpty()) {
            return todos;
        }
        todoRepository.updateTODOWithWrongStatus(todosWithWrongStatuses);
        return todoRepository.getAll(user.getId());
    }

    private Collection<TODO> filterTODOWithWrongStatus(Collection<TODO> todos) {
        return todos.stream()
                .filter(todo -> todo.getStatus() == TODOStatus.Created && isOverdue(todo))
                .collect(Collectors.toList());
    }

    private boolean isOverdue(TODO todo) {
        return todo.getShouldBeDoneBefore().before(new Date(System.currentTimeMillis()));
    }

    public void addTODO(String login, String comment, Date shouldBeDoneBefore) {
        User user = userRepository.getOne(login);
        TODO todo = TODOBuilder.newBuilder()
                .withComment(comment)
                .withCreated(new Date(System.currentTimeMillis()))
                .withShouldBeDoneBefore(shouldBeDoneBefore)
                .withUserId(user.getId())
                .withStatus(TODOStatus.Created)
                .build();
        todoRepository.insert(todo);
    }

    public boolean canModify(String login, long todoId) {
        TODO todo = todoRepository.getOne(todoId);
        if (todo == null) {
            return false;
        }
        User user = userRepository.getOne(login);
        return todo.getUserId() == user.getId();
    }

    public void deleteTODO(long id) {
        todoRepository.delete(id);
    }

    public TODO getTODO(long id) {
        return todoRepository.getOne(id);
    }

    public void editTODO(TODO todo) {
        todoRepository.update(todo);
    }

}
