import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { ManufacturersListComponent } from "./manufacturers-list.component";
import { ManufacturersFormComponent } from './manufacturers-form.component';
import { ReactiveFormsModule } from "@angular/forms";


@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild([
      {
        path: 'manufacturers',
        children: [
          {
            path: '',
            component: ManufacturersListComponent,
            data: { 
              title: 'Manufacturers'
            }
          },
          {
            path: 'new',
            component: ManufacturersFormComponent,
            data: {
              title: 'New Manufacturer'
            }
          }
        ]
      }
    ]),
  ],
  declarations: [
    ManufacturersListComponent,
    ManufacturersFormComponent
  ]
})
export class ManufacturersModule { } 