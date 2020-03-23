export class Element {

  public _id: number;
  public _name: string;
  public _details: string;

  constructor(id: number, name: string, details: string) {
    this._id = id;
    this._name = name;
    this._details = details;
  }

  get id(): number {
    return this._id;
  }

  get name(): string {
    return this._name;
  }

  get details(): string {
    return this._details;
  }

}
