import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { ManufacturersListComponent } from "./manufacturers-list.component";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([
      {
        path: 'manufacturers',
        children: [
          {
            path: '',
            component: ManufacturersListComponent,
            data: { title: 'Manufacturers'}
          }
        ]
      }
    ]),
  ],
  declarations: [
    ManufacturersListComponent
  ]
})
export class ManufacturersModule { } 