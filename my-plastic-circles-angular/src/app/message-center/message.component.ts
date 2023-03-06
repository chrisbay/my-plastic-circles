import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';

import { Toast } from 'bootstrap';
import { fromEvent } from 'rxjs';
import { take } from 'rxjs/operators';

@Component({
  selector: 'mpc-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  @Output()
  clearEvent = new EventEmitter();
  
  @ViewChild('messageElement', {static: true})
  messageElement: ElementRef;

  @Input()
  type: string;

  @Input()
  text: string;

  toast: Toast;

  iconClasses = {
    'info': 'fa-circle-info',
    'success': 'fa-check',
    'warning': 'fa-triangle-exclamation',
    'error': 'fa-circle-xmark'
  };

  constructor() { }

  ngOnInit() {
    this.show();
  }

  show(): void {
    this.toast = new Toast(
      this.messageElement.nativeElement,
      this.type === 'error'
        ? {
            autohide: false,
          }
        : {
            delay: 5000,
          }
    );

    fromEvent(this.messageElement.nativeElement, 'hidden.bs.toast')
      .pipe(take(1))
      .subscribe(() => this.clear());

    this.toast.show();
  }

  clear(): void{
    this.toast.dispose();
    this.clearEvent.emit(null);
  }

  onClose(): void {
    this.clear();
  }

}
