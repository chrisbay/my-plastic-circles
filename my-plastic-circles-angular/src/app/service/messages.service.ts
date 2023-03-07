import { Injectable } from "@angular/core";
import { Observable, Subscriber } from "rxjs";
import { Message } from "../model/message";

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  private messages: Message[] = [];
  private subscriber: Subscriber<Message[]>;

  getMessages(): Observable<Message[]> {
    return new Observable((subscriber) => {
      this.subscriber = subscriber;
    });
  }

  addMessage(message: Message): void {
    this.messages.unshift(message);
    this.subscriber.next(this.messages);
  }

  clearMessage(idx: number): void {
    this.messages.splice(idx, 1);
    this.subscriber.next(this.messages)
  }

}