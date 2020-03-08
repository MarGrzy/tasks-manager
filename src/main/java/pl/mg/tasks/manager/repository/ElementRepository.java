package pl.mg.tasks.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.tasks.manager.model.Element;
import pl.mg.tasks.manager.model.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElementRepository extends JpaRepository<Element, Integer> {

    List<Element> findByTask(Task task);

    @Transactional
    void deleteByTask(Task task);

    @Transactional
    Optional<Element> findByTaskAndId(Task task, Integer id);

}
