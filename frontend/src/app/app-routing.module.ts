import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainSiteComponent} from './main-site/main-site.component';

const routes: Routes = [
  {path: 'main', component: MainSiteComponent},
  {path: 'tasks',
  redirectTo: '/main',
  pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
