import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'values',  pure: false })
export class TasksPipe implements PipeTransform {
  transform(task: any, args: any[]): any {
    return Array.from(Object.values(task));
    // return Object.keys(task).map(key => task[key]);
  }
}
