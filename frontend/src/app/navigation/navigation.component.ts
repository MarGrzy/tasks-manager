import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  public main = '&#9749; Main';
  public tasksList = '&#128220; Tasks';
  public taskAddition = '&#10133; Addition';

  constructor() { }

  ngOnInit(): void {
  }

}
