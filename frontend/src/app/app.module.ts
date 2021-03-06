import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import { MainSiteComponent } from './main-site/main-site.component';
import { NavigationComponent } from './navigation/navigation.component';
import {LoginComponent} from './login/login.component';
import { TasksComponent } from './tasks/tasks.component';
import { EditorComponent } from './editor/editor.component';
import { AdditionComponent } from './addition/addition.component';

@NgModule({
  declarations: [
    AppComponent,
    MainSiteComponent,
    NavigationComponent,
    LoginComponent,
    TasksComponent,
    EditorComponent,
    AdditionComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
