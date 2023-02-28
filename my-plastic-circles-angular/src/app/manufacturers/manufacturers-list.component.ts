import { Component, OnInit } from '@angular/core';
import { Manufacturer } from './manufacturer';
import { ManufacturersService } from './manufacturers.service';

@Component({
  templateUrl: './manufacturers-list.component.html'
})
export class ManufacturersListComponent implements OnInit {

  private manufacturers: Manufacturer[];
  errorMessage: string = '';

  constructor(private manufacturersService: ManufacturersService) {}

  ngOnInit() {
    this.manufacturersService.getManufacturers().subscribe({
      next: discs => this.manufacturers = discs,
      error: err => this.errorMessage = err
    });
  }

}
