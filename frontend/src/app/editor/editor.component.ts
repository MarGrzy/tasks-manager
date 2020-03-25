import { Component, OnInit } from '@angular/core';
import {Meta} from '@angular/platform-browser';
import {RestService} from '../rest.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Task} from '../model/task'
import {TaskModel} from '../model/task-model';
import {ElementModel} from '../model/element-model';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  constructor(private meta: Meta, private service: RestService, private router: Router, private route: ActivatedRoute) {
    this.meta.addTags([
      {name: 'description', content: 'HTML site designed to manage tasks'},
      {name: 'author', content: 'Marcin Grzymowicz'},
      {name: 'keywords', content: 'Tasks, Collection, Management, HTML, JavaScript, CSS, REST, Angular'}
    ]);
  }

  public editedName: string;
  public editedDate: string;
  public task: Task;
  public elementName: string;
  public elementDetails: string;
  public _showElementEditor: boolean;

  ngOnInit() {
    this.service.fetchOneTask(this.route.snapshot.paramMap.get('id'))
      .subscribe(task => {this.task = task;
      this.editedName = this.task.name; this.editedDate = this.task.date});
    this._showElementEditor = false;
  }

  updateTask(taskId: number) {
    if (this.editedName.trim() !== '' && this.editedDate !== null) {
      let editedTask = new TaskModel(taskId, this.editedName, this.editedDate);
      this.service.editTask(editedTask, taskId).subscribe(error => console.log(error));
    }
  }

  get showElementEditorDialog() {
    return this._showElementEditor;
  }

  showElementDialog(): void {
    this._showElementEditor = true;
  }

  cancelAddingElement(): void {
    this._showElementEditor = false;
  }

  addOneElement(taskId: number): void {
    if (this.elementName.trim() !== '' && this.elementDetails !== null) {
      this.service.addElement(taskId, new ElementModel(this.elementName, this.elementDetails)).subscribe(error => console.log(error));
    }
    window.location.reload();
  }

  deleteOneElement(taskId: number, elementId: number): void {
    this.service.deleteElement(taskId, elementId).subscribe();
    location.reload();
  }
}
