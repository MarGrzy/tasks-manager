import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Task} from './model/task';
import {TaskModel} from './model/task-model';
import {ElementModel} from './model/element-model';
import {catchError, retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  private tasksURL = 'http://localhost:8080/tasks/';
  constructor(private http: HttpClient) {
  }

  fetchTasks(): Observable<Task[]> {
    let headers = new HttpHeaders();
    headers = headers.set('Accept', 'application/json');

    return this.http.get<Task[]>(this.tasksURL,{headers})
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }

  deleteTask(id: number): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Basic ' + localStorage.getItem('auth'));

    return this.http.delete(this.tasksURL+id,{headers})
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  addTask (task: TaskModel): Observable<TaskModel> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic '+ localStorage.getItem('auth')
      })
    };
    return this.http.post<TaskModel>(this.tasksURL, task, httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  editTask (task: TaskModel, id: number): Observable<TaskModel> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + localStorage.getItem('auth')
      })
    };
    return this.http.put<TaskModel>(this.tasksURL + id, task, httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  fetchOneTask(id: string): Observable<Task> {
    let headers = new HttpHeaders();
    headers = headers.set('Accept', 'application/json');

    return this.http.get<Task>(this.tasksURL+id, {headers})
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  addElement (taskId: number, element: ElementModel): Observable<ElementModel> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + localStorage.getItem('auth')
      })
    };
    return this.http.post<ElementModel>(this.tasksURL + taskId + '/elements', element, httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  deleteElement (taskId: number, elementId: number): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Basic ' + localStorage.getItem('auth'));

    return this.http.delete(this.tasksURL + taskId + '/elements/' + elementId, {headers})
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }
}
