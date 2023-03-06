import { Component, OnInit } from '@angular/core';
import { MessagesService } from '../message-center/messages.service';
import { Disc } from './disc';
import { DiscsService } from './discs.service';

@Component({
  templateUrl: './discs-list.component.html'
})
export class DiscsListComponent implements OnInit {

  discs: Disc[];
  errorMessage: string = '';

  constructor(private discService: DiscsService,
              private messagesService: MessagesService) {}

  ngOnInit() {
    this.discService.getDiscs().subscribe({
      next: discs => this.discs = discs,
      error: err => this.errorMessage = err
    });
  }

  toggleFavoriteStatus(disc: Disc): void {
    disc.favorite = !disc.favorite;
    let successMsg, errorMsg;
    const service = this.messagesService;
    if (disc.favorite) {
      successMsg = `${disc.model} was added to your favorites`;
      errorMsg = `An error occurred when attempting to add ${disc.model} to your favorites`;
    } else {
      successMsg = `${disc.model} was removed from your favorites`;
      errorMsg = `An error occurred when attempting to remove ${disc.model} from your favorites`;
    }
    this.discService.toggleFavoriteStatus(disc).subscribe({
      next: () => service.addMessage({type: 'info', text: successMsg}),
      error: () => service.addMessage({type: 'error', text: errorMsg})
    });
  }

}
