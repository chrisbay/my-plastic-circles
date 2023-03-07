import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from '../base.component';
import { Manufacturer } from '../model/manufacturer';
import { MessageType } from '../model/message';

@Component({
  templateUrl: './manufacturers-list.component.html'
})
export class ManufacturersListComponent extends BaseComponent implements OnInit {

  private manufacturers: Manufacturer[];

  constructor(injector: Injector) { 
    super(injector);
  }

  ngOnInit() {
    this.manufacturersService.getManufacturers().subscribe({
      next: data => this.manufacturers = data,
      error: err => this.handleError(err)
    });
  }

}
