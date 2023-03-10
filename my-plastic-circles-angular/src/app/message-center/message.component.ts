import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';

import { Toast } from 'bootstrap';
import { fromEvent } from 'rxjs';
import { MessageType } from '../model/message';

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
  type: MessageType;

  @Input()
  message: string;

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
      this.type === MessageType.Error ? {autohide: false} : {delay: 3000}
    );

    fromEvent(this.messageElement.nativeElement, 'hidden.bs.toast')
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
