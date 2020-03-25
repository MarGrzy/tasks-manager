package pl.mg.tasks.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mg.tasks.manager.model.Elements;
import pl.mg.tasks.manager.model.Task;
import pl.mg.tasks.manager.model.Element;
import pl.mg.tasks.manager.model.Tasks;
import pl.mg.tasks.manager.repository.ElementRepository;
import pl.mg.tasks.manager.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ElementRepository elementRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTasks(@RequestHeader(defaultValue = MediaType.APPLICATION_JSON_VALUE, value = "accept") String accept,
                                           @RequestHeader(defaultValue = "", value = "accept-language") String lang,
                                           @RequestParam(defaultValue = "false") boolean withoutElement) {
        List<Task> tasks = withoutElement ? taskRepository.findAllByElements_Empty() : taskRepository.findAll();
        if (!lang.equals("") && !lang.startsWith("pl") && !lang.startsWith("en")) {
            for (Task task : tasks) {
                task.setName(task.getName() + " translated to " + lang.split("_")[0]);
                for (Element element : task.getElements()) {
                    element.setName(element.getName() + " translated to " + lang.split("_")[0]);
                }
            }
        }

        if (accept.equals(MediaType.APPLICATION_JSON_VALUE)) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTask(@PathVariable("id") Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postTask(@RequestBody Task task, UriComponentsBuilder uri) {
        task = taskRepository.save(task);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri.path("tasks/{id}").buildAndExpand(task.getId()).toUri());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity putTasks(@RequestBody List<Task> tasks) {
        taskRepository.deleteAll();
        taskRepository.saveAll(tasks);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity putTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        if (task.getId() == null || !task.getId().equals(id)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<Task> org = taskRepository.findById(id);
        if (org.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        taskRepository.save(task);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteTasks() {
        taskRepository.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        taskRepository.delete(task.get());

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/elements", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getElements(@PathVariable("id") int id, @RequestHeader("accept") String accept) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<Element> elements = elementRepository.findByTask(task.get());
        if (accept.equals(MediaType.APPLICATION_JSON_VALUE)) {
            return new ResponseEntity<>(new Elements(elements), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @GetMapping(value = "/{id}/elements/{eid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Element> getElement(@PathVariable("id") Integer id, @PathVariable("eid") Integer eid) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Optional<Element> element = elementRepository.findByTaskAndId(task.get(), eid);
        if (element.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(element.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/elements")
    public ResponseEntity deleteElements(@PathVariable("id") Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        elementRepository.deleteByTask(task.get());

        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}/elements/{eid}")
    public ResponseEntity deleteElement(@PathVariable("id") Integer id, @PathVariable("eid") Integer eid) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Optional<Element> element = elementRepository.findByTaskAndId(task.get(), eid);
        if (element.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        elementRepository.deleteById(eid);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping(value = "/{id}/elements", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postElement(@PathVariable("id") Integer id, @RequestBody Element element, UriComponentsBuilder uri) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        element.setTask(task.get());
        element = elementRepository.save(element);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri.path("tasks/{id}/elements/{wid}").buildAndExpand(id, element.getId()).toUri());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/elements/{eid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity putElement(@PathVariable("id") Integer id, @PathVariable("eid") Integer eid, @RequestBody Element element) {
        if (element.getId() == null || !element.getId().equals(eid)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Optional<Element> org = elementRepository.findByTaskAndId(task.get(), eid);
        if (org.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        element.setTask(task.get());
        elementRepository.save(element);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/elements", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity putElements(@PathVariable("id") Integer id, @RequestBody List<Element> elements) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        elementRepository.deleteByTask(task.get());
        elements.forEach(e -> e.setTask(task.get()));

        elementRepository.saveAll(elements);

        return new ResponseEntity(HttpStatus.OK);
    }
}
