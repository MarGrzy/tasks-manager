import { Component, OnInit } from '@angular/core';
import {Meta} from '@angular/platform-browser';
import {RestService} from '../rest.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TaskModel} from '../model/task-model';

@Component({
  selector: 'app-addition',
  templateUrl: './addition.component.html',
  styleUrls: ['./addition.component.css']
})
export class AdditionComponent implements OnInit {

  constructor(private meta: Meta, private service: RestService, private router: Router, private route: ActivatedRoute) {
    this.meta.addTags([
      {name: 'description', content: 'HTML site designed to manage tasks'},
      {name: 'author', content: 'Marcin Grzymowicz'},
      {name: 'keywords', content: 'Tasks, Collection, Management, HTML, JavaScript, CSS, REST, Angular'}
    ]);
  }

  public _name: string;
  public _date: string;

  addOneTask(): void {
    this.service.addTask(new TaskModel(null, this._name, this._date)).subscribe(error => {console.log(error);
  });
    this.router.navigateByUrl('/list')
      .then(() => {
      window.location.reload();
      });
  }

  ngOnInit(): void {
  }

}
