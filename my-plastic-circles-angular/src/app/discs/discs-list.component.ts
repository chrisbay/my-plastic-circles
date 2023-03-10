import { Component, Injector, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AbstractBaseComponent } from '../abstract-base.component';
import { Disc } from '../model/disc';
import { MessageType } from '../model/message';

@Component({
  templateUrl: './discs-list.component.html'
})
export class DiscsListComponent extends AbstractBaseComponent implements OnInit {

  discs: Disc[];

  constructor(injector: Injector) { 
    super(injector);
  }

  ngOnInit() {
    this.discService.getAll().subscribe({
      next: discs => this.discs = discs,
      error: err => this.handleError(err)
    });
  }

  toggleFavoriteStatus(disc: Disc): Observable<Disc> {
    disc.favorite = !disc.favorite;
    let successMsg, errorMsg;
    if (disc.favorite) {
      successMsg = `${disc.model} was added to your favorites`;
      errorMsg = `An error occurred while attempting to add ${disc.model} to your favorites`;
    } else {
      successMsg = `${disc.model} was removed from your favorites`;
      errorMsg = `An error occurred while attempting to remove ${disc.model} from your favorites`;
    }
    
    const obs = this.discService.update(disc);
    obs.subscribe({
      next: () => this.messageService.addMessage({type: MessageType.Info, message: successMsg}),
      error: () => this.messageService.addMessage({type: MessageType.Error, message: errorMsg})
    });
    return obs;
  }

}
