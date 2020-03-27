import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainSiteComponent} from './main-site/main-site.component';
import {TasksComponent} from './tasks/tasks.component';
import {EditorComponent} from './editor/editor.component';
import {AdditionComponent} from './addition/addition.component';

const routes: Routes = [
  {path: 'main', component: MainSiteComponent},
  {path: 'list', component: TasksComponent},
  {path: 'addition', component: AdditionComponent},
  {path: 'editor/:id', component: EditorComponent},
  {path: 'tasks',
  redirectTo: '/list',
  pathMatch: 'full'},
  {path: '',
  redirectTo: '/main',
  pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule],
})
export class AppRoutingModule { }

