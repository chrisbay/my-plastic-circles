import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found.component';


const routes: Routes = [
  {
    path: '', 
    component: HomeComponent,
    data: { 
      title: 'Home'
    }
  },
  {
    path: '**', 
    component: PageNotFoundComponent,
    data: { 
      title: 'Page Not Found'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
