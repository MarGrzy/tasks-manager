import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainSiteComponent} from './main-site/main-site.component';
import {TasksComponent} from './tasks/tasks.component';
import {TasksPipe} from './tasks/tasks-pipe.pipe'

const routes: Routes = [
  {path: 'main', component: MainSiteComponent},
  {path: 'list', component: TasksComponent},
  {path: 'tasks',
  redirectTo: '/main',
  pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule],
  providers: [TasksPipe]
})
export class AppRoutingModule { }
