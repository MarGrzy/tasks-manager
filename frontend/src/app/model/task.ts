import {Element} from './element';

export class Task {

  public _id: number;
  public _name: string;
  public _date: string;
  public _elements: Element[];

  constructor(id: number, name: string, date: string, elements: Element[]) {
    this._id = id;
    this._name = name;
    this._date = date;
    this._elements = elements;
  }

  get id(): number {
    return this._id;
  }

  get name(): string {
    return this._name;
  }

  get date(): string {
    return this._date;
  }

  get elements(): Element[] {
    return this._elements;
  }
}
