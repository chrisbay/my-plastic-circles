import { Injectable } from "@angular/core";
import { Observable, Subscriber } from "rxjs";
import { Message } from "../model/message";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private messages: Message[] = [];
  private _publisher: Observable<Message[]>;
  private subscribers: Subscriber<Message[]>[] = [];

  constructor() {
    this._publisher = new Observable((subscriber) => {
      this.subscribers.push(subscriber);
    });
  }

  get publisher(): Observable<Message[]> {
    return this._publisher;
  }

  addMessage(message: Message): void {
    this.messages.unshift(message);
    this.notifyAll();
  }

  clearMessage(idx: number): void {
    this.messages.splice(idx, 1);
    this.notifyAll();
  }

  private notifyAll(): void {
    for (let s of this.subscribers) {
      s.next(this.messages);
    }
  }

}