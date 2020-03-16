import { Component, OnInit } from '@angular/core';
import {Meta} from '@angular/platform-browser';

@Component({
  selector: 'app-main-site',
  templateUrl: './main-site.component.html',
  styleUrls: ['./main-site.component.css']
})
export class MainSiteComponent implements OnInit {

  constructor(private meta: Meta) {
    this.meta.addTags([
      {name: 'description', content: 'HTML site designed to manage tasks'},
      {name: 'author', content: 'Marcin Grzymowicz'},
      {name: 'keywords', content: 'Tasks, Collection, Management, HTML, JavaScript, CSS, REST, Angular'}
    ]);
  }

  ngOnInit(): void {
  }

}
