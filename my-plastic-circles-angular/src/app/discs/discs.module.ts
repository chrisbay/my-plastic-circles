import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { DiscResolver } from "../service/disc-resolver.service";
import { ManufacturersResolver } from "../service/manufacturers-resolver.service";
import { DiscsFormComponent } from './discs-form.component';
import { DiscsListComponent } from "./discs-list.component";

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild([
      {
        path: 'discs',
        children: [
          {
            path: '',
            component: DiscsListComponent,
            data: { 
              title: 'My Discs'
            }
          },
          {
            path: '0/edit',
            component: DiscsFormComponent,
            resolve: { 
              disc: DiscResolver,
              manufacturers: ManufacturersResolver 
            },
            data: {
              title: 'New Disc',
              isNew: true
            }
          },
          {
            path: ':id/edit',
            component: DiscsFormComponent,
            resolve: { 
              disc: DiscResolver,
              manufacturers: ManufacturersResolver 
            },
            data: {
              title: 'Edit Disc',
              isNew: false
            }
          }
        ]
      }
    ]),
  ],
  declarations: [
    DiscsListComponent,
    DiscsFormComponent
  ]
})
export class DiscsModule { } 