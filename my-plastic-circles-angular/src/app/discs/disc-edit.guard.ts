import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { MessageType } from '../model/message';
import { MessagesService } from '../service/messages.service';

@Injectable({
  providedIn: 'root'
})
export class DiscEditGuard implements CanActivate {

  constructor(private router: Router,
              private messagesService: MessagesService) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      const idParam = next.paramMap.get('id');
      const id = Number(idParam);
    if (isNaN(id) || id < 1) {
      this.messagesService.addMessage({
        type: MessageType.Error, 
        message: `Invalid disc id: ${idParam}`
      });
      this.router.navigate(['/discs']);
      return false;
    }
    
    return true;
  }
  
}
