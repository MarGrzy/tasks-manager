package pl.mg.tasks.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mg.tasks.manager.model.Task;
import pl.mg.tasks.manager.model.Element;
import pl.mg.tasks.manager.model.Tasks;
import pl.mg.tasks.manager.repository.ElementRepository;
import pl.mg.tasks.manager.repository.TaskRepository;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
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
            return new ResponseEntity<>(new Tasks(tasks), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
