import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Meta} from '@angular/platform-browser';
import {Task} from '../model/task';
import {RestService} from '../rest.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  constructor(private meta: Meta, private service: RestService, private router: Router, private route: ActivatedRoute) {
    this.meta.addTags([
      {name: 'description', content: 'HTML site designed to manage tasks'},
      {name: 'author', content: 'Marcin Grzymowicz'},
      {name: 'keywords', content: 'Tasks, Collection, Management, HTML, JavaScript, CSS, REST, Angular'}
    ]);
  }

  public tasks: Task[];

  ngOnInit() {
    this.service.fetchTasks().subscribe(tasks => this.tasks = tasks);
    console.log(this.tasks);
  }

  deleteOneTask(id: number): void {
    this.service.deleteTask(id).subscribe(task => this.tasks[id] = task);
    location.reload();
  }

  toTaskEditor(id: number): void {
    this.router.navigate(['/editor', id]).then();
  }
}
