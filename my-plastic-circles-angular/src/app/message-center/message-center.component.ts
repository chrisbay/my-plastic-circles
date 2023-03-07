import { Component, OnInit } from '@angular/core';
import { Message } from '../model/message';
import { MessagesService } from '../service/messages.service';

@Component({
  selector: 'mpc-message-center',
  templateUrl: './message-center.component.html',
  styleUrls: ['./message-center.component.css']
})
export class MessageCenterComponent implements OnInit {

  messages: Message[];

  constructor(private messagesService: MessagesService) {}

  ngOnInit() {
    this.messagesService.getMessages().subscribe({
      next: messages => this.messages = messages
    });
  }

  clearMessage(idx: number): void {
    this.messagesService.clearMessage(idx);
  }

}
