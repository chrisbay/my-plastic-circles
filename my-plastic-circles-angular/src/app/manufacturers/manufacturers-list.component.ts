import { Component, Injector, OnInit } from '@angular/core';
import { AbstractBaseComponent } from '../abstract-base.component';
import { Manufacturer } from '../model/manufacturer';

@Component({
  templateUrl: './manufacturers-list.component.html'
})
export class ManufacturersListComponent extends AbstractBaseComponent implements OnInit {

  private manufacturers: Manufacturer[];

  constructor(injector: Injector) { 
    super(injector);
  }

  ngOnInit() {
    this.manufacturerService.getAll().subscribe({
      next: data => this.manufacturers = data,
      error: err => this.handleError(err)
    });
  }

}
