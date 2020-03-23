export class TaskModel {

  private id: number;
  private name: string;
  private date: string;

  constructor(id: number, name: string, date: string) {
    this.id = id;
    this.name = name;
    this.date = date;
  }
}
