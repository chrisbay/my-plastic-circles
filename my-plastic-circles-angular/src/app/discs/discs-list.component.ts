import { Component, OnInit } from '@angular/core';
import { Disc } from './disc';
import { DiscsService } from './discs.service';

@Component({
  templateUrl: './discs-list.component.html'
})
export class DiscsListComponent implements OnInit {

  discs: Disc[];
  errorMessage: string = '';

  constructor(private discService: DiscsService) {}

  ngOnInit() {
    this.discService.getDiscs().subscribe({
      next: discs => this.discs = discs,
      error: err => this.errorMessage = err
    });
  }

  toggleFavoriteStatus(disc: Disc): void {
    disc.favorite = !disc.favorite;
    this.discService.toggleFavoriteStatus(disc).subscribe({
      error: () => alert("Error updating disc")
    });
  }

}
