import { Component, OnInit } from '@angular/core';
import { Message } from './Message';
import { MessagesService } from './messages.service';

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

    this.messagesService.addMessage({ type: "info", text: "Informational message"});
    this.messagesService.addMessage({ type: "success", text: "It worked!"});
    this.messagesService.addMessage({ type: "warning", text: "You should be aware of this"});
    this.messagesService.addMessage({ type: "error", text: "Something very bad happened!"});
  }

  getIconClass(type: string) {
    return 'fa-circle-info';
  }

  clearMessage(idx: number): void {
    this.messagesService.clearMessage(idx);
  }

}
