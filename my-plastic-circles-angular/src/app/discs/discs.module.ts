import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { DiscsListComponent } from "./discs-list.component";

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([
      {
        path: 'discs',
        children: [
          {
            path: '',
            component: DiscsListComponent,
            data: { title: 'My Discs'}
          }
        ]
      }
    ]),
  ],
  declarations: [
    DiscsListComponent
  ]
})
export class DiscsModule { } 